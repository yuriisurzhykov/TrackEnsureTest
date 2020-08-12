package com.yuriysurzhikov.trackensuretest.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yuriysurzhikov.trackensuretest.R;
import com.yuriysurzhikov.trackensuretest.presenter.contracts.MainActivityContract;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
