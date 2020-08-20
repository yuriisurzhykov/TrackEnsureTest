package com.yuriysurzhikov.trackensuretest.view.adapters;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yuriysurzhikov.trackensuretest.view.fragments.ProjectFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "MainPagerAdapter";

    private List<String> pageTitles = new ArrayList<>(Arrays.asList("Stations", "Statistics"));
    private List<ProjectFragment> mainActivityFragments;

    public MainPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    private MainPagerAdapter(@NonNull FragmentManager fm, List<ProjectFragment> list) {
        this(fm);
        mainActivityFragments = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mainActivityFragments.get(position);
    }

    @Override
    public int getCount() {
        return pageTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles.get(position);
    }

    public static class Builder {
        private MainPagerAdapter result;

        public Builder(FragmentManager fm, Context context) {
            result = new MainPagerAdapter(fm);
            result.pageTitles.clear();
            result.mainActivityFragments = new ArrayList<>();
        }

        public Builder addFragment(String title, ProjectFragment fragment) {
            result.pageTitles.add(title);
            result.mainActivityFragments.add(fragment);
            return this;
        }

        public MainPagerAdapter build() {
            if(result.pageTitles.size() == 0 || result.mainActivityFragments.size() == 0)
                throw new IllegalStateException("Cannot create MainPagerAdapter without a single fragment!");
            return result;
        }
    }
}
