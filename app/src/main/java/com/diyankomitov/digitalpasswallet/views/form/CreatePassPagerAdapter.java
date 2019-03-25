package com.diyankomitov.digitalpasswallet.views.form;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CreatePassPagerAdapter extends FragmentPagerAdapter {
    
    private final List<Fragment> fragments;
    private final List<String> fragmentTitles;
    
    public CreatePassPagerAdapter(FragmentManager fm) {
        
        super(fm);
        this.fragments = new ArrayList<>();
        this.fragmentTitles = new ArrayList<>();
    }
    
    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        fragmentTitles.add(title);
    }
    
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        
        return fragmentTitles.get(position);
    }
    
    @Override
    public Fragment getItem(int position) {
        
        return fragments.get(position);
    }
    
    @Override
    public int getCount() {
        
        return fragments.size();
    }
}
