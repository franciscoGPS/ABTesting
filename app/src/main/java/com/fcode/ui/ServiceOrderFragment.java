package com.fcode.ui;

import android.accounts.AccountsException;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.fcode.BootstrapApplication;
import com.fcode.BootstrapServiceProvider;
import com.fcode.R;
import com.fcode.core.Customer;
import com.fcode.core.ServiceOrder;
import com.fcode.core.User;
import com.fcode.util.Toaster;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.fcode.core.Constants.Auth.USER_ID;
import static com.fcode.core.Constants.Extra.SERVICE_ORDER;
import static com.fcode.core.Constants.Http.MAKES_SET;


/**
 * Created by fcorde on 12/09/16.
 */
public class ServiceOrderFragment extends Fragment {

    @Inject
    protected BootstrapServiceProvider serviceProvider;

    @Inject
    protected Gson gson;

    @Inject
    protected MainActivity permittedActivity;

    @Inject
    protected SharedPreferences sharedPreferences;

    @Bind(R.id.et_cust_name) protected EditText etCustName;
    @Bind(R.id.et_cust_last_name) protected EditText etCustLastName;
    @Bind(R.id.et_cust_email) protected EditText etCustEmail;
    @Bind(R.id.et_cust_cellphone) protected EditText etCustCellPhone;
    @Bind(R.id.et_cust_phone) protected EditText etCustPhone;
    @Bind(R.id.et_model) protected EditText etModel;
    @Bind(R.id.et_color) protected EditText etColor;
    @Bind(R.id.et_year) protected EditText etYear;
    @Bind(R.id.et_plate) protected EditText etPlate;
    @Bind(R.id.et_symptom) protected EditText etSymptom;
    @Bind(R.id.btn_save_order) protected Button saveServiceOrder;
    @Bind(R.id.btn_cancel) protected Button cancelBtn;
    @Bind(R.id.spinner_makes) protected Spinner spinnerMakes;

    @Bind(R.id.win_one) protected CheckBox win_one;
    @Bind(R.id.win_two) protected CheckBox win_two;
    @Bind(R.id.win_three) protected CheckBox win_three;
    @Bind(R.id.win_four) protected CheckBox win_four;
    @Bind(R.id.dl_one) protected CheckBox dl_one;
    @Bind(R.id.dl_two) protected CheckBox dl_two;
    @Bind(R.id.dl_three) protected CheckBox dl_three;
    @Bind(R.id.dl_four) protected CheckBox dl_four;

    @Bind(R.id.ft) protected CheckBox ft;
    @Bind(R.id.ab) protected CheckBox ab;
    @Bind(R.id.hag) protected CheckBox hag;
    @Bind(R.id.li) protected CheckBox li;
    @Bind(R.id.avna) protected CheckBox avna;
    @Bind(R.id.hn) protected CheckBox hn;
    @Bind(R.id.sj) protected CheckBox sj;
    @Bind(R.id.abs) protected CheckBox abs;
    @Bind(R.id.sa) protected CheckBox sa;
    @Bind(R.id.gmg) protected CheckBox gmg;
    @Bind(R.id.air_bag) protected CheckBox air_bag;
    @Bind(R.id.fe) protected CheckBox fe;
    @Bind(R.id.sayda) protected CheckBox sayda;
    @Bind(R.id.lff) protected CheckBox lff;
    @Bind(R.id.casc) protected CheckBox casc;
    @Bind(R.id.ses) protected CheckBox ses;
    @Bind(R.id.sm) protected CheckBox sm;
    @Bind(R.id.na) protected CheckBox na;
    @Bind(R.id.ac) protected CheckBox ac;
    @Bind(R.id.dx) protected CheckBox dx;
    @Bind(R.id.sc) protected CheckBox sc;
    @Bind(R.id.bpa) protected CheckBox bpa;
    @Bind(R.id.af) protected CheckBox af;
    @Bind(R.id.cc) protected CheckBox cc;

    private ArrayList<String> makes = null;

    private ArrayAdapter<String> makesAdapter;

    private ServiceOrder mServiceOrder;

    private User user;

    private final TextWatcher watcher = validationTextWatcher();

    private TextWatcher validationTextWatcher() {
        return new TextWatcherAdapter() {
            public void afterTextChanged(final Editable gitDirEditText) {
                updateUIWithValidation();
            }

        };
    }

    private void updateUIWithValidation() {
        final boolean populated = isEmailValid(etCustEmail.getText().toString());
        saveServiceOrder.setEnabled(populated);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.so_view, container, false);
        ButterKnife.bind(this, view);
        BootstrapApplication.component().inject(this);
        return view;


    }


    public static ServiceOrderFragment newInstance(ServiceOrder serviceOrder) {
        ServiceOrderFragment f = new ServiceOrderFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putSerializable(SERVICE_ORDER, serviceOrder);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mServiceOrder = (ServiceOrder) getArguments().getSerializable(SERVICE_ORDER );





        etCustName.setText(mServiceOrder.getCustomer().getName());
        etCustLastName.setText(mServiceOrder.getCustomer().getLast_name());
        etCustPhone.setText(mServiceOrder.getCustomer().getPhone_number());
        etCustCellPhone.setText(mServiceOrder.getCustomer().getCellphone());
        etCustEmail.setText(mServiceOrder.getCustomer().getEmail());
        etModel.setText(mServiceOrder.getModel());
        etColor.setText(mServiceOrder.getColor());
        if (mServiceOrder.getYear() == 0){
            etYear.setText("");
        }else {
            etYear.setText(mServiceOrder.getYear().toString());
        }

        etPlate.setText(mServiceOrder.getPlate());
        etSymptom.setText(mServiceOrder.getService_request());



        win_one.setChecked(mServiceOrder.getWin_one());
        win_two.setChecked(mServiceOrder.getWin_two());
        win_three.setChecked(mServiceOrder.getWin_three());
        win_four.setChecked(mServiceOrder.getWin_four());
        dl_one.setChecked(mServiceOrder.getDl_one());
        dl_two.setChecked(mServiceOrder.getDl_two());
        dl_three.setChecked(mServiceOrder.getDl_three());
        dl_four.setChecked(mServiceOrder.getDl_four());
        //ft,ab,hag,li,avna,hn,sj,abs,sa,gmg,fe,air_bag,sayda,lff,casc,ses,sm,na,ac,dx,sc,bpa,af,cc
        ft.setChecked(mServiceOrder.getFt());
        ab.setChecked(mServiceOrder.getAb());
        hag.setChecked(mServiceOrder.getHag());
        li.setChecked(mServiceOrder.getLi());
        avna.setChecked(mServiceOrder.getAvna());
        hn.setChecked(mServiceOrder.getHn());
        sj.setChecked(mServiceOrder.getSj());
        abs.setChecked(mServiceOrder.getAbs());
        sa.setChecked(mServiceOrder.getSa());
        gmg.setChecked(mServiceOrder.getGmg());
        fe.setChecked(mServiceOrder.getFe());
        air_bag.setChecked(mServiceOrder.getAir_bag());
        sayda.setChecked(mServiceOrder.getSayda());
        lff.setChecked(mServiceOrder.getLff());
        casc.setChecked(mServiceOrder.getCasc());
        ses.setChecked(mServiceOrder.getSes());
        sm.setChecked(mServiceOrder.getSm());
        na.setChecked(mServiceOrder.getNa());
        ac.setChecked(mServiceOrder.getAc());
        dx.setChecked(mServiceOrder.getDx());
        sc.setChecked(mServiceOrder.getSc());
        bpa.setChecked(mServiceOrder.getBpa());
        af.setChecked(mServiceOrder.getAf());
        cc.setChecked(mServiceOrder.getCc());




        saveServiceOrder.setOnClickListener(saveClickListener);

        cancelBtn.setOnClickListener(cancelClickListener);

        user = new User();
               user.setId(sharedPreferences.getInt(USER_ID, 0));

        Set<String> set = sharedPreferences.getStringSet(MAKES_SET, null);

        if (set !=  null) { //TODO: Add new criteria to eventually reload new models.
            makes = new ArrayList<>(set);
            Collections.sort(makes);
            setMakesAdapter(makes);
        }else
        {
            VehicleData getvehiclesData = new VehicleData();
            getvehiclesData.execute("");
        }

        spinnerMakes.setSelection(getIndex(spinnerMakes, mServiceOrder.getMake()));

        if(etCustName.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }

        etCustEmail.addTextChangedListener(watcher);

    }
    //private method of your class
    private int getIndex(Spinner spinner, String itemName)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(itemName)){
                index = i;
                break;
            }
        }
        return index;
    }

    private View.OnClickListener cancelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            returnToFragmentList(null);
        }
    };

    private View.OnClickListener saveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if(etCustName.getText().toString().trim().length() > 0 &&
                    etCustLastName.getText().toString().trim().length() > 0 &&
            //etCustEmail.getText().toString().trim().length() > 0 &&
            etCustCellPhone.getText().toString().trim().length() > 0 &&
                    etColor.getText().toString().trim().length() > 0 &&
                    etYear.getText().toString().trim().length() > 0 &&
                    etModel.getText().toString().trim().length() > 0 ) {

                if((win_one.isChecked() == true         || win_two.isChecked() == true  ||
                        win_three.isChecked() == true   || win_four.isChecked() == true ||
                        dl_one.isChecked() == true      || dl_two.isChecked() == true   ||
                        dl_three.isChecked() == true    || dl_four.isChecked() == true  ||
                        ft.isChecked() == true          || ab.isChecked() == true       ||
                        hag.isChecked() == true         || li.isChecked() == true       ||
                        avna.isChecked() == true        || hn.isChecked() == true       ||
                        sj.isChecked() == true          || abs.isChecked() == true      ||
                        sa.isChecked() == true          || gmg.isChecked() == true      ||
                        fe.isChecked() == true          || air_bag.isChecked() == true  ||
                        sayda.isChecked() == true       || lff.isChecked() == true      ||
                        casc.isChecked() == true        || ses.isChecked() == true      ||
                        sm.isChecked() == true          || na.isChecked() == true       ||
                        ac.isChecked() == true          || dx.isChecked() == true       ||
                        sc.isChecked() == true          || bpa.isChecked() == true      ||
                        af.isChecked() == true          || cc.isChecked() == true)
                        || etSymptom.getText().toString().trim().length() > 0) {

                    if (mServiceOrder != null) {


                        Customer customer = mServiceOrder.getCustomer();

                        mServiceOrder.setWin_one(win_one.isChecked());
                        mServiceOrder.setWin_two(win_two.isChecked());
                        mServiceOrder.setWin_three(win_three.isChecked());
                        mServiceOrder.setWin_four(win_four.isChecked());
                        mServiceOrder.setDl_one(dl_one.isChecked());
                        mServiceOrder.setDl_two(dl_two.isChecked());
                        mServiceOrder.setDl_three(dl_three.isChecked());
                        mServiceOrder.setDl_four(dl_four.isChecked());
                        mServiceOrder.setFt(ft.isChecked());
                        mServiceOrder.setAb(ab.isChecked());
                        mServiceOrder.setHag(hag.isChecked());
                        mServiceOrder.setLi(li.isChecked());
                        mServiceOrder.setAvna(avna.isChecked());
                        mServiceOrder.setHn(hn.isChecked());
                        mServiceOrder.setSj(sj.isChecked());
                        mServiceOrder.setAbs(abs.isChecked());
                        mServiceOrder.setSa(sa.isChecked());
                        mServiceOrder.setGmg(gmg.isChecked());
                        mServiceOrder.setFe(fe.isChecked());
                        mServiceOrder.setAir_bag(air_bag.isChecked());
                        mServiceOrder.setSayda(sayda.isChecked());
                        mServiceOrder.setLff(lff.isChecked());
                        mServiceOrder.setCasc(casc.isChecked());
                        mServiceOrder.setSes(ses.isChecked());
                        mServiceOrder.setSm(sm.isChecked());
                        mServiceOrder.setNa(na.isChecked());
                        mServiceOrder.setAc(ac.isChecked());
                        mServiceOrder.setDx(dx.isChecked());
                        mServiceOrder.setSc(sc.isChecked());
                        mServiceOrder.setBpa(bpa.isChecked());
                        mServiceOrder.setAf(af.isChecked());
                        mServiceOrder.setCc(cc.isChecked());

                        customer.setName(etCustName.getText().toString().trim());
                        customer.setLast_name(etCustLastName.getText().toString().trim());
                        customer.setPhone_number(etCustPhone.getText().toString().trim());
                        customer.setCellphone(etCustCellPhone.getText().toString().trim());
                        customer.setEmail(etCustEmail.getText().toString().trim());
                        customer.setUser_id(user.getId());


                        mServiceOrder.setModel(etModel.getText().toString().trim());
                        mServiceOrder.setYear(Integer.valueOf(etYear.getText().toString().trim()));
                        mServiceOrder.setPlate(etPlate.getText().toString().trim());
                        mServiceOrder.setService_request(etSymptom.getText().toString().trim());
                        mServiceOrder.setMake(spinnerMakes.getSelectedItem().toString().trim());
                        mServiceOrder.setColor(etColor.getText().toString().trim());
                        mServiceOrder.setCustomer_id(customer.getId());
                        mServiceOrder.setUser_id(user.getId());



                        mServiceOrder.setCustomer(customer);


                        SaveServiceOrder saveSO = new SaveServiceOrder();
                        Boolean create = false;
                        if (customer.getId() == 0) {
                            create = true;

                        }
                        saveSO.execute(mServiceOrder, create);
                    } else {
                        returnToFragmentList(null);
                    }
                }else {
                    Toaster.showLong(getActivity(), "Debe seleccionar algúna descripción de falla o casilla de falla.");
                }
            }else{
                Toaster.showLong(getActivity(), "Campos obligatorios: Nombre, Apellido, Celular, Email, Model, Año");
            }
        }
    };

    private void returnToFragmentList(ServiceOrder serviceOrder){


        if (serviceOrder != null){
            final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new CarouselFragment())
                    .commit();
        }else {
            getFragmentManager().popBackStack();
        }
    }

    public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }


    private void setMakesAdapter(ArrayList<String> makes){
        makesAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, makes);
        // Specify the layout to use when the list of choices appears
        makesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerMakes.setAdapter(makesAdapter);
    }

    private class SaveServiceOrder extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {

            ServiceOrder serviceOrder = (ServiceOrder) params[0];
            Boolean create = false;
            try {
                create = (Boolean)params[1];
            }catch (ClassCastException e){
                e.printStackTrace();
            }



            try {
                if (create) {
                    return serviceProvider.getService(permittedActivity).createServiceOrder(serviceOrder);
                }else {
                    return serviceProvider.getService(permittedActivity).updateServiceOrder(serviceOrder);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (AccountsException e) {
                e.printStackTrace();
            }


            return null;
        }




        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            mServiceOrder = (ServiceOrder) o;

            Toaster.showLong(getActivity(), R.string.successfull_operation);

            returnToFragmentList(mServiceOrder);

        }
    }

    private class VehicleData extends AsyncTask{
        protected ArrayList<String> makes, models;

        @Override
        protected ArrayList<String> doInBackground(Object[] params) {
            return getMakes((String) params[0]);
        }

        @Override
        protected void onPostExecute(Object o) {

            makes = (ArrayList<String>)o;

            Collections.sort(makes);


            SharedPreferences.Editor editor = sharedPreferences.edit();
            Set<String> makesSet = new HashSet<String>();
            makesSet.addAll(makes);

            editor.remove(MAKES_SET);
            editor.putStringSet(MAKES_SET, makesSet);
            editor.commit();
            setMakesAdapter(makes);

        }

        /**
         *
         * @param query
         * @throws IOException
         * @throws AccountsException if user us not signed, the login screen will appear
         */

        private ArrayList<String> getMakes(String query)  {
            ArrayList<String> makes = null;
            try {
                makes = serviceProvider.getService(permittedActivity).getMakes(query);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (AccountsException e) {
                e.printStackTrace();
            }

            return makes;
            //makes = (ArrayList<String>) sharedPreferences.getStringSet("makes",null);
        }
    }




}
