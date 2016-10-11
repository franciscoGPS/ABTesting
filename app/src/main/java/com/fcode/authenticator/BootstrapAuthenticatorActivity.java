package com.fcode.authenticator;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.fcode.BootstrapApplication;
import com.fcode.R;
import com.fcode.R.id;
import com.fcode.R.layout;
import com.fcode.R.string;
import com.fcode.core.BootstrapService;
import com.fcode.core.Constants;
import com.fcode.core.User;
import com.fcode.ui.TextWatcherAdapter;
import com.fcode.util.SafeAsyncTask;
import com.fcode.util.Toaster;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

import static android.R.layout.simple_dropdown_item_1line;
import static android.accounts.AccountManager.KEY_ACCOUNT_NAME;
import static android.accounts.AccountManager.KEY_ACCOUNT_TYPE;
import static android.accounts.AccountManager.KEY_BOOLEAN_RESULT;
import static android.view.KeyEvent.ACTION_DOWN;
import static android.view.KeyEvent.KEYCODE_ENTER;
import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;
import static com.fcode.core.Constants.Auth.USER;
import static com.fcode.core.Constants.Auth.USER_EMAIL;
import static com.fcode.core.Constants.Auth.USER_ID;
import static com.fcode.core.Constants.Auth.USER_NAME;
import static com.fcode.core.Constants.Auth.USER_ROLE;
import static java.util.Locale.US;


/**
 * Activity to authenticate the user against an API (example API on Parse.com)
 */
public class BootstrapAuthenticatorActivity extends ActionBarAccountAuthenticatorActivity {



    /**
     * PARAM_CONFIRM_CREDENTIALS
     */
    public static final String PARAM_CONFIRM_CREDENTIALS = "confirmCredentials";

    /**
     * PARAM_PASSWORD
     */
    public static final String PARAM_PASSWORD = "password";

    /**
     * PARAM_USERNAME
     */
    public static final String PARAM_USERNAME = "username";

    /**
     * PARAM_AUTHTOKEN_TYPE
     */
    public static final String PARAM_AUTHTOKEN_TYPE = "authtokenType";


    private AccountManager accountManager;

    @Inject BootstrapService bootstrapService;
    @Inject Bus bus;
    @Inject SharedPreferences sharedPreferences;
    @Inject @Named("current_user")
    User user;

    @Bind(id.et_email) protected AutoCompleteTextView emailText;
    @Bind(id.et_password) protected EditText passwordText;
    @Bind(id.b_signin) protected Button signInButton;

    private final TextWatcher watcher = validationTextWatcher();

    private SafeAsyncTask<User> authenticationTask;
    private String authToken;
    private String authTokenType;

    /**
     * If set we are just checking that the user knows their credentials; this
     * doesn't cause the user's password to be changed on the device.
     */
    private Boolean confirmCredentials = false;

    private String email;

    private String password;

    private User currentUser;
    /**
     * Was the original caller asking for an entirely new account?
     */
    protected boolean requestNewAccount = false;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        BootstrapApplication.component().inject(this);

        accountManager = AccountManager.get(this);

        final Intent intent = getIntent();
        email = intent.getStringExtra(PARAM_USERNAME);
        authTokenType = intent.getStringExtra(PARAM_AUTHTOKEN_TYPE);
        confirmCredentials = intent.getBooleanExtra(PARAM_CONFIRM_CREDENTIALS, false);

        requestNewAccount = email == null;

        setContentView(layout.login_activity);

        ButterKnife.bind(this);

        emailText.setAdapter(new ArrayAdapter<String>(this,
                simple_dropdown_item_1line, userEmailAccounts()));

        passwordText.setOnKeyListener(new OnKeyListener() {

            public boolean onKey(final View v, final int keyCode, final KeyEvent event) {
                if (event != null && ACTION_DOWN == event.getAction()
                        && keyCode == KEYCODE_ENTER && signInButton.isEnabled()) {
                    handleLogin(signInButton);
                    return true;
                }
                return false;
            }
        });

        passwordText.setOnEditorActionListener(new OnEditorActionListener() {

            public boolean onEditorAction(final TextView v, final int actionId,
                                          final KeyEvent event) {
                if (actionId == IME_ACTION_DONE && signInButton.isEnabled()) {
                    handleLogin(signInButton);
                    return true;
                }
                return false;
            }
        });

        emailText.addTextChangedListener(watcher);
        passwordText.addTextChangedListener(watcher);

        final TextView signUpText = (TextView) findViewById(id.tv_help);
        signUpText.setMovementMethod(LinkMovementMethod.getInstance());
        signUpText.setText(Html.fromHtml(getString(string.help_link)));
    }

    private List<String> userEmailAccounts() {
        final Account[] accounts = accountManager.getAccountsByType("com.google");
        final List<String> emailAddresses = new ArrayList<String>(accounts.length);
        for (final Account account : accounts) {
            emailAddresses.add(account.name);
        }
        return emailAddresses;
    }

    private TextWatcher validationTextWatcher() {
        return new TextWatcherAdapter() {
            public void afterTextChanged(final Editable gitDirEditText) {
                updateUIWithValidation();
            }

        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
        updateUIWithValidation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    private void updateUIWithValidation() {
        final boolean populated = populated(emailText) && populated(passwordText);
        signInButton.setEnabled(populated);
    }

    private boolean populated(final EditText editText) {
        return editText.length() > 0;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getText(string.message_signing_in));
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(final DialogInterface dialog) {
                if (authenticationTask != null) {
                    authenticationTask.cancel(true);
                }
            }
        });
        return dialog;
    }

    @Subscribe
    public void onUnAuthorizedErrorEvent(Exception unAuthorizedErrorEvent) {
        // Could not authorize for some reason.
        Toaster.showLong(BootstrapAuthenticatorActivity.this, R.string.message_bad_credentials);
    }

    /**
     * Handles onClick event on the Submit button. Sends username/password to
     * the server for authentication.
     * <p/>
     * Specified by android:onClick="handleLogin" in the layout xml
     *
     * @param view
     */
    public void handleLogin(final View view) {
        if (authenticationTask != null) {
            return;
        }

        if (requestNewAccount) {
            email = emailText.getText().toString();
        }

        password = passwordText.getText().toString();

        email = "fco.coma@gmail.com";
        password = "Admin1000.";
        showProgress();

        authenticationTask = new SafeAsyncTask<User>() {

            public User call() throws Exception {


                user = bootstrapService.authenticate(email, password);


                return user;
            }

            @Override
            protected void onException(final Exception e) throws RuntimeException {
                // Retrofit Errors are handled inside of the {
                //TODO: Change the use of exception as a filter. Investigate type of exceptions raised from server
                if(!(e instanceof Exception)) {
                    final Throwable cause = e.getCause() != null ? e.getCause() : e;
                    if(cause != null) {
                        Toaster.showLong(BootstrapAuthenticatorActivity.this, cause.getMessage());
                    }
                }
            }

            @Override
            public void onSuccess(User registered) {
                user = registered;
            }

            @Override
            protected void onFinally() throws RuntimeException {
                hideProgress();

                authenticationTask = null;
                onAuthenticationResult(user);

            }
        };

        authenticationTask.execute();
    }


    @Subscribe
    public void onUserAuthenticated(User user) {
        Boolean userReceived = false;
        if (user != null){
            this.user = user;
            userReceived = true;
        }
        onAuthenticationResult(user);
    }

    /**
     * Called when response is received from the server for confirm credentials
     * request. See onAuthenticationResult(). Sets the
     * AccountAuthenticatorResult which is sent back to the caller.
     *
     * @param user
     */
    protected void finishConfirmCredentials(final User user) {
        final Account account = new Account(email, Constants.Auth.BOOTSTRAP_ACCOUNT_TYPE);

        accountManager.setPassword(account, password);


        final Intent intent = new Intent();
        intent.putExtra(KEY_BOOLEAN_RESULT, false);
        if(user != null){
            intent.putExtra(USER_EMAIL, email);
            intent.putExtra(USER, user);
            intent.putExtra(KEY_BOOLEAN_RESULT, true);
            setAccountAuthenticatorResult(intent.getExtras());
            setResult(RESULT_OK, intent);
        }
        finish();
    }

    /**
     * Called when response is received from the server for authentication
     * request. See onAuthenticationResult(). Sets the
     * AccountAuthenticatorResult which is sent back to the caller. Also sets
     * the authToken in AccountManager for this account.
     */

    protected void finishLogin(final User user) {

        final Account account = new Account(email, Constants.Auth.BOOTSTRAP_ACCOUNT_TYPE);

        if (requestNewAccount) {
            Bundle userBundle = new Bundle();
            userBundle.putSerializable(USER, user);
            accountManager.addAccountExplicitly(account, password, null);

        } else {
            accountManager.setPassword(account, password);
        }

        final Intent intent = new Intent();
        intent.putExtra(KEY_ACCOUNT_NAME, email);
        //intent.putExtra(USER, user);
        intent.putExtra(KEY_ACCOUNT_TYPE, Constants.Auth.BOOTSTRAP_ACCOUNT_TYPE);


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_EMAIL, user.getEmail());
        editor.putInt(USER_ROLE, user.getRole_id());
        editor.putString(USER_NAME, user.getName());
        editor.putInt(USER_ID, user.getId());
        editor.commit();



        if (authTokenType != null
                && authTokenType == Constants.Auth.AUTHTOKEN_TYPE) {


        }

        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Hide progress dialog
     */
    @SuppressWarnings("deprecation")
    protected void hideProgress() {
        dismissDialog(0);
    }

    /**
     * Show progress dialog
     */
    @SuppressWarnings("deprecation")
    protected void showProgress() {
        showDialog(0);
    }

    /**
     * Called when the authentication process completes (see attemptLogin()).
     *
     * @param user
     */
    public void onAuthenticationResult(final User user) {
        if (user != null) {
            if (!confirmCredentials) {
                finishLogin(user);//This is the most used option, for debugging purposes.
            } else {
                finishConfirmCredentials(user);
            }
        } else {
            Timber.d("onAuthenticationResult: failed to authenticate");
            if (requestNewAccount) {
                Toaster.showLong(BootstrapAuthenticatorActivity.this,
                        string.message_auth_failed_new_account);
            } else {
                Toaster.showLong(BootstrapAuthenticatorActivity.this,
                        string.message_auth_failed);
            }
        }
    }
}
