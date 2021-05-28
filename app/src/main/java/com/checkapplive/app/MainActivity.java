package com.checkapplive.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.checkapplive.mylibrary.LiveAppActivity;


/*Created by Grow Solution 2021

     1.Data.remoteConfig.getBoolean(Data.is_app_live) == is from firbase app is live or not
     2.Data.remoteConfig.getBoolean(Data.is_app_redirect_immediate) == is from firbase app update is immediate or not
     3.Data.remoteConfig.getBoolean(Data.app_redirect_package) == is from firbase app redirect link (play store link)
     4. getDrawable(R.drawable.shape_g_data)  use for custom view of button*/


public class MainActivity extends AppCompatActivity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LiveAppActivity liveAppActivity = new LiveAppActivity();
        liveAppActivity.checkAppisLiveOrNot(MainActivity.this, MainActivity.this, /*Data.remoteConfig.getBoolean(Data.is_app_live)*/false, /*Data.remoteConfig.getBoolean(Data.is_app_redirect_immediate)*/true,
                /* Data.remoteConfig.getString(Data.app_redirect_package)*/"https://play.google.com/store/apps/details?id=com.voicetyping.keyboard.newnepali", getDrawable(R.drawable.shape_g_data), getDrawable(R.drawable.shape_g_border));
    }


}