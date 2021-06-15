package com.example.retrofitdemo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.retrofitdemo.ExamFragment;
import com.example.retrofitdemo.Statistic2Actitvity;
import com.example.retrofitdemo.Statistic2Fragment;
import com.example.retrofitdemo.Statistic3Activity;
import com.example.retrofitdemo.Statistic3Fragment;
import com.example.retrofitdemo.StatisticActivity;
import com.example.retrofitdemo.StatisticFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new StatisticFragment();
            case 1:
                return new Statistic2Fragment();
            case 2:
                return new Statistic3Fragment();
            default:
                return new StatisticFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
