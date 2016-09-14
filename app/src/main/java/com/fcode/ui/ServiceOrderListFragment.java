package com.fcode.ui;

import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;

import com.fcode.BootstrapApplication;
import com.fcode.BootstrapServiceProvider;
import com.fcode.R;
import com.fcode.authenticator.LogoutService;
import com.fcode.core.ServiceOrder;
import com.fcode.util.SingleTypeAdapter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import static com.fcode.core.Constants.Extra.SERVICE_ORDER;

/**
 * Created by fcorde on 11/09/16.
 */
public class ServiceOrderListFragment extends ItemListFragment<ServiceOrder> {

@Inject protected BootstrapServiceProvider serviceProvider;
@Inject protected LogoutService logoutService;


@Override
public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BootstrapApplication.component().inject(this);
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
        .inflate(R.layout.user_list_item_labels, null));
        }

@Override
protected LogoutService getLogoutService() {
        return logoutService;
        }

@Override
public Loader<List<ServiceOrder>> onCreateLoader(final int id, final Bundle args) {
final List<ServiceOrder> initialItems = items;
        return new ThrowableLoader<List<ServiceOrder>>(getActivity(), items) {
@Override
public List<ServiceOrder> loadData() throws Exception {

        try {
        List<ServiceOrder> latest = null;

        if (getActivity() != null) {
        latest = serviceProvider.getService(getActivity()).getServiceOrders();
        }

        if (latest != null) {
        return latest;
        } else {
        return Collections.emptyList();
        }
        } catch (final OperationCanceledException e) {
final Activity activity = getActivity();
        if (activity != null) {
        activity.finish();
        }
        return initialItems;
        }
        }
        };

        }

public void onListItemClick(final ListView l, final View v, final int position, final long id) {
final ServiceOrder serviceOrder = ((ServiceOrder) l.getItemAtPosition(position));

        startActivity(new Intent(getActivity(), ServiceOrderActivity.class).putExtra(SERVICE_ORDER, serviceOrder));
        }

@Override
public void onLoadFinished(final Loader<List<ServiceOrder>> loader, final List<ServiceOrder> items) {
        super.onLoadFinished(loader, items);

        }

@Override
protected int getErrorMessage(final Exception exception) {
        return R.string.error_loading_users;
        }

@Override
protected SingleTypeAdapter<ServiceOrder> createAdapter(final List<ServiceOrder> items) {
        return new ServiceOrderListAdapter(getActivity().getLayoutInflater(), items);
        }

}
