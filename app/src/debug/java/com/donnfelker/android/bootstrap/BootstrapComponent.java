package com.fcode;

import com.fcode.authenticator.BootstrapAuthenticatorActivity;
import com.fcode.core.BootstrapService;
import com.fcode.core.TimerService;
import com.fcode.ui.BootstrapActivity;
import com.fcode.ui.BootstrapFragmentActivity;
import com.fcode.ui.BootstrapTimerActivity;
import com.fcode.ui.CarouselFragment;
import com.fcode.ui.CheckInsListFragment;
import com.fcode.ui.MainActivity;
import com.fcode.ui.NavigationDrawerFragment;
import com.fcode.ui.NewsActivity;
import com.fcode.ui.NewsListFragment;
import com.fcode.ui.ServiceOrderFragment;
import com.fcode.ui.ServiceOrderListFragment;
import com.fcode.ui.UserActivity;
import com.fcode.ui.UserListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AndroidModule.class,
                BootstrapModule.class
        }
)
public interface BootstrapComponent {

    void inject(BootstrapApplication target);



    void inject(BootstrapAuthenticatorActivity target);

    void inject(BootstrapTimerActivity target);

    void inject(MainActivity target);

    void inject(CheckInsListFragment target);

    void inject(NavigationDrawerFragment target);

    void inject(NewsActivity target);

    void inject(NewsListFragment target);

    void inject(UserActivity target);

    void inject(UserListFragment target);

    void inject(TimerService target);

    void inject(BootstrapFragmentActivity target);
    void inject(BootstrapActivity target);
    void inject(CarouselFragment target);
    void inject (ServiceOrderListFragment target);
    void inject (ServiceOrderFragment fragment);
    void inject(BootstrapService service);
    //void inject(RestAdapterRequestInterceptor interceptor);





}
