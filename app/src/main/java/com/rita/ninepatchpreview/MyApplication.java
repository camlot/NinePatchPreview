package com.rita.ninepatchpreview;

import android.app.Application;

/**
 * Created by rita on 2019/3/20.
 */

public class MyApplication extends Application {

    private static MyApplication mMyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mMyApplication = this;
    }

    public static MyApplication getInstance() {
        return mMyApplication;
    }
}
