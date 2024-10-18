package com.example.easedine;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new ProductListFragment();
            case 1: return new ProductListFragment1();
            case 2: return new ProductListFragment2();
            case 3: return new ProductListFragment3();
            default: return new ProductListFragment();

        }
         // Return the same fragment for all tabs, modify as per need
    }

    @Override
    public int getItemCount() {
        return 4; // We have four tabs
    }
}
