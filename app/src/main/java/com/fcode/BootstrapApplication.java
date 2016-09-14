

package com.fcode;

import android.app.Application;

/**
 * Flash application
 */
public abstract class BootstrapApplication extends Application {

    private static BootstrapApplication instance;
    private com.fcode.BootstrapComponent component;


    /**
     * Create main application
     */
    public BootstrapApplication() {
    }


    @Override
    public void onCreate() {
        super.onCreate();

        init();

        instance = this;

        // Perform injection
        //Injector.init(this, )
        component = DaggerComponentInitializer.init();

        onAfterInjection();

    }
    public static com.fcode.BootstrapComponent component() {
        return instance.component;
    }

    protected abstract void onAfterInjection();

    protected abstract void init();

    public static BootstrapApplication getInstance() {
        return instance;
    }

    public com.fcode.BootstrapComponent getComponent() {
        return component;
    }

    public final static class DaggerComponentInitializer {

        public static com.fcode.BootstrapComponent init() {
            return DaggerBootstrapComponent.builder()
                    .androidModule(new AndroidModule())
                    .bootstrapModule(new BootstrapModule())
                    .build();
        }

    }

}
