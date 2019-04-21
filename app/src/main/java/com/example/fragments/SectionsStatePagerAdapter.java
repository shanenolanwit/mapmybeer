package com.example.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsStatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public SectionsStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String fragmentTitle) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(fragmentTitle);
    }

    @Override
    public Fragment getItem(int index) {
        return mFragmentList.get(index);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }


}
