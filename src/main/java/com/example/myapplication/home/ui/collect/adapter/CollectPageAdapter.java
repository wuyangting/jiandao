package com.example.myapplication.home.ui.collect.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class CollectPageAdapter extends FragmentPagerAdapter {


    private final ArrayList<Fragment> fragments;
    private final ArrayList<String> tabs;

    public CollectPageAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> tabs) {
        super(fm);
        this.fragments = fragments;
        this.tabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }
}
