package com.example.myapplication.home.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class HomeViewPagerAdapter extends FragmentPagerAdapter {
private ArrayList<String> tabs=new ArrayList<>();

private ArrayList<Fragment> fragments=new ArrayList<>();
    public HomeViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);

    }

    public HomeViewPagerAdapter(@NonNull FragmentManager fm, ArrayList<String> tabs, ArrayList<Fragment> fragments) {
        super(fm);
        this.tabs = tabs;
        this.fragments = fragments;
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
