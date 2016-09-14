package com.fcode;

import android.accounts.AccountsException;
import android.app.Activity;

import com.fcode.core.BootstrapService;

import java.io.IOException;

public interface BootstrapServiceProvider {
    BootstrapService getService(Activity activity) throws IOException, AccountsException;
}
