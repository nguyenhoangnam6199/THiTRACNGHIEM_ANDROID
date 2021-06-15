package com.example.retrofitdemo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.retrofitdemo.FavoriteFragment;
import com.example.retrofitdemo.HistoryFragment;
import com.example.retrofitdemo.KetQua;
import com.example.retrofitdemo.StudyFragment;

public class ViewPager1Adapter extends FragmentStatePagerAdapter {


    public ViewPager1Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new StudyFragment();
            case 1:
                return new StudyFragment();
            case 2:
                return new FavoriteFragment();
            case 3:
                return new HistoryFragment();
            default:
                return new StudyFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
