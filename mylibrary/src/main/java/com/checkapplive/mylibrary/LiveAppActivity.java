package com.checkapplive.mylibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LiveAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_live);
    }

    @SuppressLint("NewApi")
    public void checkAppisLiveOrNot(Context context, Activity activity, Boolean is_app_live, Boolean isImmediate, String app_redirect_package, Drawable shape_g, Drawable shape_g_border) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Log.w("msg", "isAppLive checkAppisLiveOrNot  ");
        Log.w("msg", "isAppLive is_app_live  " + is_app_live);
        Log.w("msg", "isAppLive isImmediate  " + isImmediate);
        Log.w("msg", "isAppLive app_redirect_package  " + app_redirect_package);

        if (!is_app_live) {
            if (isImmediate) {
                ImmediateDialog gameDialog = new ImmediateDialog(activity);
                gameDialog.setImagePath(R.drawable.icon_immediate);
                gameDialog.setDrawblePath(shape_g);
                gameDialog.setApplyListener(new ImmediateDialog.OnApplyListener() {
                    @Override
                    public void onClick(ImmediateDialog dialog, int randomNumber) {
                        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(app_redirect_package)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        System.exit(1);
                        dialog.dismiss();
                        Log.w("msg", "isAppLive gameDialog click  ");
                    }
                });
                gameDialog.show();
                gameDialog.setCancelable(false);
                gameDialog.setCanceledOnTouchOutside(false);

            } else {
                FlexibleDialog gameDialog = new FlexibleDialog(activity);
                gameDialog.setImagePath(R.drawable.icon_immediate);
                gameDialog.setDrawblePath(shape_g);
                gameDialog.setnotNoewDrawblePath(shape_g_border);
                gameDialog.setApplyListener(new FlexibleDialog.OnApplyListener() {
                    @Override
                    public void onClick(FlexibleDialog dialog, int randomNumber) {
                        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(app_redirect_package)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        dialog.dismiss();
                        Log.w("msg", "isAppLive gameDialog click  ");

                    }
                });
                gameDialog.setnotNowListener(new FlexibleDialog.OnNotnowListener() {
                    @Override
                    public void onClick(FlexibleDialog dialog, int randomNumber) {
                        dialog.dismiss();
                    }
                });

                gameDialog.show();
                gameDialog.setCancelable(false);
                gameDialog.setCanceledOnTouchOutside(false);
            }
        }
    }


//    check App is live or not use this method


//        isAppLiveOrNot = isAppLiveOnPlayStore(package_name);
//        Log.w("msg", "isAppLive isAppLiveOrNot  " + isAppLiveOrNot);


    //                PackageManager pm = activity.getPackageManager();
//                Log.w("msg", "isAppLive isPackageInstalled  " + isPackageInstalled(package_name, pm));
//                if (!isPackageInstalled(package_name, pm)) {

//    public static boolean isPackageInstalled(String packageName, PackageManager packageManager) {
//        try {
//            packageManager.getPackageInfo(packageName, 0);
//            return true;
//        } catch (PackageManager.NameNotFoundException e) {
//            return false;
//        }
//    }
//
//    public static boolean isAppLiveOnPlayStore(String appid) {
//        try {
//            HttpURLConnection conn = (HttpURLConnection) (new URL("https://play.google.com/store/apps/details?id=" + appid)).openConnection();
//            conn.setUseCaches(false);
//            conn.connect();
//            int status = conn.getResponseCode();
//            conn.disconnect();
//            return status == 200;
//        } catch (Exception e) {
//            Log.w("msg log ", e.toString());
//        }
//        return false;
//    }

}