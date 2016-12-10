package com.xandersu.showweibo.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.xandersu.showweibo.BaseApplication;

/**
 * Created by lenovo on 2016/12/4.
 */

public class MyUIUtils {

    private static Toast mToast;

    public static Context getContext(){
        return BaseApplication.getContext();
    }

    public static  int dp2px(float dp){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int)(density*dp +0.5);
    }

    public  static float px2dp(float px){
        float density = getContext().getResources().getDisplayMetrics().density;
        return px/density;
    }

    /*
    加载布局文件
     */
    public  static View inflate(int layoutId){
        return View.inflate(getContext(),layoutId,null);
    }

    public static void showToast(String str){
        if( mToast == null){
            mToast = Toast.makeText(getContext(),str,Toast.LENGTH_SHORT);
        }else {
            mToast.setText(str);
        }
        mToast.show();
    }
}
