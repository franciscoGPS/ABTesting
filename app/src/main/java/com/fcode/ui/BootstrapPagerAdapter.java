

package com.fcode.ui;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fcode.R;

/**
 * Pager adapter
 */
public class BootstrapPagerAdapter extends FragmentPagerAdapter {

    private final Resources resources;

    /**
     * Create pager adapter
     *
     * @param resources
     * @param fragmentManager
     */
    public BootstrapPagerAdapter(final Resources resources, final FragmentManager fragmentManager) {
        super(fragmentManager);
        this.resources = resources;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Fragment getItem(final int position) {
        final Fragment result;
        switch (position) {
            case 0:
                result = new ServiceOrderListFragment();

                break;
            /*case 1:
                result = new NewsListFragment();
                break;
            case 2:
                result = new CheckInsListFragment();
                break;*/
            default:
                result = null;
                break;
        }
        if (result != null) {
            result.setArguments(new Bundle()); //TODO do we need this?
        }
        return result;
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        switch (position) {
            case 0:
                return resources.getString(R.string.page_service_orders);

            /*case 1:
                return resources.getString(R.string.page_news);
            case 2:
                return resources.getString(R.string.page_checkins);*/
            default:
                return null;
        }
    }


}
