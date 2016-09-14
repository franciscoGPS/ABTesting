package com.fcode.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.fcode.R;
import com.fcode.core.ServiceOrder;

import butterknife.Bind;

import static com.fcode.core.Constants.Extra.SERVICE_ORDER;

/**
 * Created by fcorde on 12/09/16.
 */
public class ServiceOrderActivity extends BootstrapActivity {
    @Bind(R.id.iv_avatar) protected ImageView avatar;
    @Bind(R.id.tv_name) protected TextView name;

    private ServiceOrder serviceOrder;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_view);

        if (getIntent() != null && getIntent().getExtras() != null) {
            serviceOrder = (ServiceOrder) getIntent().getExtras().getSerializable(SERVICE_ORDER);
        }

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*Picasso.with(this).load(user.getAvatarUrl())
                .placeholder(R.drawable.gravatar_icon)
                .into(avatar);*/

        name.setText(String.format("%s %s", serviceOrder.getService_request(), serviceOrder.getMake()+
                " " + serviceOrder.getModel() + " " + serviceOrder.getYear().toString()));

    }
}
