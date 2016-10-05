package com.fcode.ui;

import android.accounts.AccountsException;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.fcode.util.Toaster;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    private ServiceOrder serviceOrder;


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


        serviceOrder = (ServiceOrder) getArguments().getSerializable(SERVICE_ORDER);

            /*Picasso.with(this).load(user.getAvatarUrl())
                .placeholder(R.drawable.gravatar_icon)
                .into(avatar);*/

        etCustName.setText(serviceOrder.getCustomer().getName());
        etCustLastName.setText(serviceOrder.getCustomer().getLast_name());
        etCustPhone.setText(serviceOrder.getCustomer().getPhone_number());
        etCustCellPhone.setText(serviceOrder.getCustomer().getCellphone());
        etCustEmail.setText(serviceOrder.getCustomer().getEmail());
        etModel.setText(serviceOrder.getModel());
        if (serviceOrder.getYear() == 0){
            etYear.setText("");
        }else {
            etYear.setText(serviceOrder.getYear().toString());
        }

        etPlate.setText(serviceOrder.getPlate());
        etSymptom.setText(serviceOrder.getService_request());



        win_one.setChecked(serviceOrder.getWin_one());
        win_two.setChecked(serviceOrder.getWin_two());
        win_three.setChecked(serviceOrder.getWin_three());
        win_four.setChecked(serviceOrder.getWin_four());
        dl_one.setChecked(serviceOrder.getDl_one());
        dl_two.setChecked(serviceOrder.getDl_two());
        dl_three.setChecked(serviceOrder.getDl_three());
        dl_four.setChecked(serviceOrder.getDl_four());
        //ft,ab,hag,li,avna,hn,sj,abs,sa,gmg,fe,air_bag,sayda,lff,casc,ses,sm,na,ac,dx,sc,bpa,af,cc
        ft.setChecked(serviceOrder.getFt());
        ab.setChecked(serviceOrder.getAb());
        hag.setChecked(serviceOrder.getHag());
        li.setChecked(serviceOrder.getLi());
        avna.setChecked(serviceOrder.getAvna());
        hn.setChecked(serviceOrder.getHn());
        sj.setChecked(serviceOrder.getSj());
        abs.setChecked(serviceOrder.getAbs());
        sa.setChecked(serviceOrder.getSa());
        gmg.setChecked(serviceOrder.getGmg());
        fe.setChecked(serviceOrder.getFe());
        air_bag.setChecked(serviceOrder.getAir_bag());
        sayda.setChecked(serviceOrder.getSayda());
        lff.setChecked(serviceOrder.getLff());
        casc.setChecked(serviceOrder.getCasc());
        ses.setChecked(serviceOrder.getSes());
        sm.setChecked(serviceOrder.getSm());
        na.setChecked(serviceOrder.getNa());
        ac.setChecked(serviceOrder.getAc());
        dx.setChecked(serviceOrder.getDx());
        sc.setChecked(serviceOrder.getSc());
        bpa.setChecked(serviceOrder.getBpa());
        af.setChecked(serviceOrder.getAf());
        cc.setChecked(serviceOrder.getCc());




        saveServiceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(serviceOrder != null){

                    serviceOrder.setWin_one(win_one.isChecked());
                    serviceOrder.setWin_two(win_two.isChecked());
                    serviceOrder.setWin_three(win_three.isChecked());
                    serviceOrder.setWin_four(win_four.isChecked());
                    serviceOrder.setDl_one(dl_one.isChecked());
                    serviceOrder.setDl_two(dl_two.isChecked());
                    serviceOrder.setDl_three(dl_three.isChecked());
                    serviceOrder.setDl_four(dl_four.isChecked());
                    serviceOrder.setFt(ft.isChecked());
                    serviceOrder.setAb(ab.isChecked());
                    serviceOrder.setHag(hag.isChecked());
                    serviceOrder.setLi(li.isChecked());
                    serviceOrder.setAvna(avna.isChecked());
                    serviceOrder.setHn(hn.isChecked());
                    serviceOrder.setSj(sj.isChecked());
                    serviceOrder.setAbs(abs.isChecked());
                    serviceOrder.setSa(sa.isChecked());
                    serviceOrder.setGmg(gmg.isChecked());
                    serviceOrder.setFe(fe.isChecked());
                    serviceOrder.setAir_bag(air_bag.isChecked());
                    serviceOrder.setSayda(sayda.isChecked());
                    serviceOrder.setLff(lff.isChecked());
                    serviceOrder.setCasc(casc.isChecked());
                    serviceOrder.setSes(ses.isChecked());
                    serviceOrder.setSm(sm.isChecked());
                    serviceOrder.setNa(na.isChecked());
                    serviceOrder.setAc(ac.isChecked());
                    serviceOrder.setDx(dx.isChecked());
                    serviceOrder.setSc(sc.isChecked());
                    serviceOrder.setBpa(bpa.isChecked());
                    serviceOrder.setAf(af.isChecked());
                    serviceOrder.setCc(cc.isChecked());

                    serviceOrder.getCustomer().setName(etCustName.getText().toString());
                    serviceOrder.getCustomer().setLast_name(etCustLastName.getText().toString());
                    serviceOrder.getCustomer().setPhone_number(etCustPhone.getText().toString());
                    serviceOrder.getCustomer().setCellphone(etCustCellPhone.getText().toString());
                    serviceOrder.getCustomer().setEmail(etCustEmail.getText().toString());
                    serviceOrder.setModel(etModel.getText().toString());
                    serviceOrder.setYear(Integer.valueOf(etYear.getText().toString()));
                    serviceOrder.setPlate(etPlate.getText().toString());
                    serviceOrder.setService_request(etSymptom.getText().toString());
                    serviceOrder.setMake(spinnerMakes.getSelectedItem().toString());

                    Customer newCustomer = new Customer();




                SaveServiceOrder saveSO = new SaveServiceOrder();
                    Boolean create = false;
                    if(serviceOrder.getId() == 0 ){
                        create = true;
                    }
                    saveSO.execute(serviceOrder, create);
                }else {
                    returnToFragmentList(null);
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToFragmentList(null);
            }
        });

        Set<String> set = sharedPreferences.getStringSet(MAKES_SET, null);
        makes = new ArrayList<>(set);
        if (makes == null) {
            VehicleData getvehiclesData = new VehicleData();
            getvehiclesData.execute("");
        }else
        {
            setMakesAdapter();
        }

        spinnerMakes.setSelection(getIndex(spinnerMakes, serviceOrder.getMake()));


    }
    //private method of your class
    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

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


    private void setMakesAdapter(){
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

            serviceOrder = (ServiceOrder)o;

            Toaster.showLong(getActivity(), R.string.successfull_so_created);

            returnToFragmentList(serviceOrder);

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

            setMakesAdapter();

            SharedPreferences.Editor editor = sharedPreferences.edit();

            Set<String> makesSet = new HashSet<String>();
            makesSet.addAll(makes);

            editor.putStringSet(MAKES_SET, makesSet);
            editor.commit();

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
