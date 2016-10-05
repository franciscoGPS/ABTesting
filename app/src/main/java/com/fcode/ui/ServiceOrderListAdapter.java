package com.fcode.ui;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.fcode.R;
import com.fcode.core.ServiceOrder;
import com.fcode.util.SingleTypeAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by fcorde on 12/09/16.
 */
public class ServiceOrderListAdapter extends SingleTypeAdapter<ServiceOrder> {

    private static final SimpleDateFormat MMMM_DD_DATE_FORMAT = new SimpleDateFormat("MMMM dd");

    /**
     * @param inflater
     * @param items
     */
    public ServiceOrderListAdapter(final LayoutInflater inflater, final List<ServiceOrder> items) {
        super(inflater, R.layout.so_list_item);

        setItems(items);
    }

    /**
     * @param inflater
     */
    public ServiceOrderListAdapter(final LayoutInflater inflater) {
        this(inflater, null);

    }

    @Override
    public long getItemId(final int position) {
        final String id = getItem(position).getId().toString();
        return !TextUtils.isEmpty(id) ? id.hashCode() : super
                .getItemId(position);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.iv_avatar,R.id.serviceOrderDetail, R.id.tv_cust_name, R.id.tv_vehicle, R.id.tv_date};
    }

    /**
     * This method is called from its super class, inside the getView() method.
     *
     * @param position
     * @param serviceOrder
     */
    @Override
    protected void update(final int position, final ServiceOrder serviceOrder) {

        /*Picasso.with(BootstrapApplication.getInstance())
                .load(serviceOrder.getAvatarUrl())
                .placeholder(R.drawable.gravatar_icon)
                .into(imageView(0));*/

        //setText(1, String.format("%1$s %2$s", serviceOrder.getService_request(), serviceOrder.getMake()+
          //      " " + serviceOrder.getModel() + " " + serviceOrder.getYear().toString()));

        //setText(R.id.tv_cust_name, serviceOrder.getMake() + serviceOrder.getModel());
        //setText(R.id.tv_vehicle, serviceOrder.getYear() + serviceOrder.getColor());
        //setText(R.id.tv_date, serviceOrder.getCreated_at().toString());


    }

    @Override
    protected void update(int position, View view, ServiceOrder serviceOrder) {
        super.update(position, view, serviceOrder);

        //Following id's are the id's in the order of the elements placed in the fragment.
        setText(3, serviceOrder.getMake() +" "+ serviceOrder.getModel());
        setText(2, serviceOrder.getYear() +" "+ serviceOrder.getColor());
        setText(4, serviceOrder.getDate_in().toString());

    }
}
