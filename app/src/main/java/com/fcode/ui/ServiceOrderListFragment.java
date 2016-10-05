package com.fcode.ui;

import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;

import com.fcode.BootstrapApplication;
import com.fcode.BootstrapServiceProvider;
import com.fcode.R;
import com.fcode.authenticator.LogoutService;
import com.fcode.core.ServiceOrder;
import com.fcode.util.SingleTypeAdapter;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.fcode.core.Constants.Extra.SERVICE_ORDER;

/**
 * Created by fcorde on 11/09/16.
 */
    public class ServiceOrderListFragment extends ItemListFragment<ServiceOrder> {


    @Inject
    protected BootstrapServiceProvider serviceProvider;

    @Inject
    protected SharedPreferences sharedPreferences;

    @Inject
    protected LogoutService logoutService;

    @Inject
    protected Bus bus;

    ArrayList<ServiceOrder> mServiceOrderList = null;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BootstrapApplication.component().inject(this);

    }

    @Override
    protected void newServiceOrder() {
        redirectToNewServiceOrder(null);
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText(R.string.no_service_orders);
    }

    @Override
    protected void configureList(final Activity activity, final ListView listView) {
        super.configureList(activity, listView);

        listView.setFastScrollEnabled(true);
        listView.setDividerHeight(0);

        getListAdapter().addHeader(activity.getLayoutInflater()
                .inflate(R.layout.so_list_item_labels, null));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected LogoutService getLogoutService() {
        return logoutService;
    }


    public Loader<List<ServiceOrder>> onCreateLoader(final int id, final Bundle args) {
        //final ArrayList<ServiceOrder> initialItems = items;


        return new ThrowableLoader<List<ServiceOrder>>(getActivity(), items) {
            @Override
            public ArrayList<ServiceOrder> loadData() throws Exception {

                try {
                    List<ServiceOrder> latest = null;

                    if (getActivity() != null) {
                        //if(mServiceOrderList == null) {

                       mServiceOrderList = serviceProvider.getService(getActivity()).getServiceOrders();

                        /*SharedPreferences.Editor editor = sharedPreferences.edit();
                        ArrayList<String> makes =  serviceProvider.getService(getActivity()).getMakes("");
                        editor.putStringSet("makes", (Set<String>)makes);
                        editor.commit();*/

                        //}
                    }

                    if (mServiceOrderList != null) {
                        return mServiceOrderList;
                    } else {
                        return new ArrayList();
                    }



                } catch (final OperationCanceledException e) {
                    final Activity activity = getActivity();
                    if (activity != null) {
                        activity.finish();
                    }

                    return mServiceOrderList;
                }


            }


        };

    }

    private void redirectToNewServiceOrder(ServiceOrder serviceOrder){

        if (serviceOrder == null){
            serviceOrder = new ServiceOrder();
        }


        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, ServiceOrderFragment.newInstance(serviceOrder)).addToBackStack(SERVICE_ORDER)
                .commit();
    }

    public void onListItemClick(final ListView l, final View v, final int position, final long id) {
        final ServiceOrder serviceOrder = ((ServiceOrder) l.getItemAtPosition(position));



        redirectToNewServiceOrder(serviceOrder);
        //startActivity(new Intent(getActivity(), ServiceOrderFragment.class).putExtra(SERVICE_ORDER, serviceOrder));
    }

    @Override
    public void onLoadFinished(final Loader<List<ServiceOrder>> loader, final List<ServiceOrder> items) {
        super.onLoadFinished(loader, items);
    }

    @Override
    protected int getErrorMessage(final Exception exception) {
        return R.string.error_loading_sos;
    }

    @Override
    protected SingleTypeAdapter<ServiceOrder> createAdapter(final List<ServiceOrder> items) {
        return new ServiceOrderListAdapter(getActivity().getLayoutInflater(), items);
    }

}
