
package com.fcode;

import android.accounts.AccountsException;
import android.app.Activity;

import com.fcode.authenticator.ApiKeyProvider;
import com.fcode.core.BootstrapService;

import java.io.IOException;

import retrofit2.Retrofit;

/**
 * Provider for a {@link com.fcode.core.BootstrapService} instance
 */
public class BootstrapServiceProviderImpl implements BootstrapServiceProvider {

    private Retrofit restAdapter;
    private ApiKeyProvider keyProvider;

    public BootstrapServiceProviderImpl(Retrofit restAdapter, ApiKeyProvider keyProvider) {
        this.restAdapter = restAdapter;
        this.keyProvider = keyProvider;
    }

    /**
     * Get service for configured key provider
     * <p/>
     * This method gets an auth key and so it blocks and shouldn't be called on the main thread.
     *
     * @return bootstrap service
     * @throws IOException
     * @throws AccountsException
     */
    @Override
    public BootstrapService getService(final Activity activity)
            throws IOException, AccountsException {
        // The call to keyProvider.getAuthKey(...) is what initiates the login screen. Call that now.
        keyProvider.getAuthKey(activity);

        return new BootstrapService(restAdapter);
    }
}
