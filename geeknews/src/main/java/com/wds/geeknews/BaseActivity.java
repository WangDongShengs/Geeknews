package com.wds.geeknews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract int getLayout();

    protected abstract void initData();

    protected abstract void initView();

    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(  AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }


}
