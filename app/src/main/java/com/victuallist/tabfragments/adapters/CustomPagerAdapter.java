package com.victuallist.tabfragments.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by john on 4/15/15.
 */
public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> contentFragments;

    public CustomPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments){
        super(fm);
        this.contentFragments = fragments;

    }

    @Override
    public Fragment getItem(int i) {
        return this.contentFragments.get(i);
    }

    @Override
    public int getCount() {
        return this.contentFragments.size();
    }
}
