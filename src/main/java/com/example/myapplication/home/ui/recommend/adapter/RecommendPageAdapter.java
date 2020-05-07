package com.example.myapplication.home.ui.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class RecommendPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private ArrayList<String> tabs=new ArrayList<>();
    public RecommendPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public RecommendPageAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> tabs) {
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

}
