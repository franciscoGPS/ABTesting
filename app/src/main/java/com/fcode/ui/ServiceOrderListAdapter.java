package com.fcode.ui;

import android.view.LayoutInflater;

import com.fcode.R;
import com.fcode.core.ServiceOrder;
import com.fcode.util.SingleTypeAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by fcorde on 12/09/16.
 */
public class ServiceOrderListAdapter extends SingleTypeAdapter<ServiceOrder> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMMM dd");

    /**
     * @param inflater
     * @param items
     */
    public ServiceOrderListAdapter(final LayoutInflater inflater, final List<ServiceOrder> items) {
        super(inflater, R.layout.user_list_item);

        setItems(items);
    }

    /**
     * @param inflater
     */
    public ServiceOrderListAdapter(final LayoutInflater inflater) {
        this(inflater, null);

    }

    /*@Override
    public long getItemId(final int position) {
        /*final String id = getItem(position).getObjectId();/
        return !TextUtils.isEmpty(id) ? id.hashCode() : super
                .getItemId(position);
    }*/

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.iv_avatar, R.id.tv_name};
    }

    @Override
    protected void update(final int position, final ServiceOrder serviceOrder) {

        /*Picasso.with(BootstrapApplication.getInstance())
                .load(serviceOrder.getAvatarUrl())
                .placeholder(R.drawable.gravatar_icon)
                .into(imageView(0));*/

        setText(1, String.format("%1$s %2$s", serviceOrder.getService_request(), serviceOrder.getMake()+
                " " + serviceOrder.getModel() + " " + serviceOrder.getYear().toString()));

    }

}
