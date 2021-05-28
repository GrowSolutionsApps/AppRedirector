package com.checkapplive.app;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class mApp extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
