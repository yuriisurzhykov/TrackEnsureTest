package com.yuriysurzhikov.trackensuretest.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.yuriysurzhikov.trackensuretest.R;
import com.yuriysurzhikov.trackensuretest.model.MainRepository;
import com.yuriysurzhikov.trackensuretest.presenter.contracts.MainActivityContract;
import com.yuriysurzhikov.trackensuretest.view.adapters.MainPagerAdapter;
import com.yuriysurzhikov.trackensuretest.view.fragments.StationsFragment;
import com.yuriysurzhikov.trackensuretest.view.fragments.StatisticsFragment;


public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.main_viewpager);
        MainRepository.getInstance().getAllPlaces().observe(this, places -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                places.stream().forEach(place -> Log.d(TAG, "onCreate: " + place));
            }
        });
        MainPagerAdapter pagerAdapter = new MainPagerAdapter.Builder(getSupportFragmentManager(), this)
                .addFragment("Stations", StationsFragment.getInstance(this))
                .addFragment("Statistics", StatisticsFragment.getInstance(this))
                .build();
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.main_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
