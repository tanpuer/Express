package com.example.templechen.express.ui.splash;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by templechen on 2017/3/31.
 */

public class SplashViewPagerAdapter extends FragmentPagerAdapter {

    private final int count = 3;

    public SplashViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return SplashFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return count;
    }
}
