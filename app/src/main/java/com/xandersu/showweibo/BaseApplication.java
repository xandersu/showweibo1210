package com.xandersu.showweibo;

import android.app.Application;
import android.content.Context;

/**
 * Created by lenovo on 2016/12/4.
 */

public class BaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
