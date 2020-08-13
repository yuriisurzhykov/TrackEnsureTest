package com.yuriysurzhikov.trackensuretest.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.yuriysurzhikov.trackensuretest.R;
import com.yuriysurzhikov.trackensuretest.presenter.contracts.MainActivityContract;
import com.yuriysurzhikov.trackensuretest.view.adapters.MainPagerAdapter;
import com.yuriysurzhikov.trackensuretest.view.fragments.StationsFragment;
import com.yuriysurzhikov.trackensuretest.view.fragments.StatisticsFragment;


public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.main_viewpager);
        MainPagerAdapter pagerAdapter = new MainPagerAdapter.Builder(getSupportFragmentManager(), this)
                .addFragment("Stations", StationsFragment.getInstance(this))
                .addFragment("Statistics", StatisticsFragment.getInstance(this))
                .build();
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.main_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
