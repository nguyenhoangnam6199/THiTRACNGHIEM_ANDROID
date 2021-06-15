package com.example.retrofitdemo.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.retrofitdemo.ExamFragment;
import com.example.retrofitdemo.HistoryDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends FragmentPagerAdapter {
    private final List<HistoryDetailFragment> fragments = new ArrayList<>();
    private final List<String> fragmentTitle = new ArrayList<>();

    public HistoryAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }
    public void add(HistoryDetailFragment fragment, String title) {
        fragments.add(fragment);
        fragmentTitle.add(title);
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
        return fragmentTitle.get(position);
    }
}
