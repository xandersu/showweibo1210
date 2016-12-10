package com.xandersu.showweibo.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xandersu.showweibo.R;


/**
 * Created by lenovo on 2016/12/6.
 */

public class TitleBuilder {

    private View viewTitle;
    private TextView tv_Title;
    private TextView tv_Left;
    private TextView tv_Right;
    private ImageView iv_Left;
    private ImageView iv_Right;

    public  TitleBuilder(Activity activity){
        viewTitle = activity.findViewById(R.id.rl_titlebar);
        tv_Title = (TextView) viewTitle.findViewById(R.id.titlebar_tv);
        iv_Left = (ImageView) viewTitle.findViewById(R.id.titlebar_iv_left);
        iv_Right = (ImageView) viewTitle.findViewById(R.id.titlebar_iv_right);
        tv_Left = (TextView) viewTitle.findViewById(R.id.titlebar_tv_left);
        tv_Right = (TextView) viewTitle.findViewById(R.id.titlebar_tv_right);
    }

    public TitleBuilder(View view){
        viewTitle = view.findViewById(R.id.rl_titlebar);
        tv_Title = (TextView) viewTitle.findViewById(R.id.titlebar_tv);
        iv_Left = (ImageView) viewTitle.findViewById(R.id.titlebar_iv_left);
        iv_Right = (ImageView) viewTitle.findViewById(R.id.titlebar_iv_right);
        tv_Left = (TextView) viewTitle.findViewById(R.id.titlebar_tv_left);
        tv_Right = (TextView) viewTitle.findViewById(R.id.titlebar_tv_right);
    }

    public TitleBuilder setTitleBgRes(int resId){
        viewTitle.setBackgroundResource(resId);
        return this;
    }

    public TitleBuilder setTitleText(String str){
        tv_Title.setVisibility(TextUtils.isEmpty(str)?
                View.GONE:View.VISIBLE);
        tv_Title.setText(str);
        return this;
    }

    public TitleBuilder setLeftImage(int resId){
        iv_Left.setVisibility(resId>0?
        View.GONE:View.VISIBLE);
        iv_Left.setImageResource(resId);
        return this;
    }

    public TitleBuilder setLeftText(String str){
        tv_Left.setVisibility(TextUtils.isEmpty(str)?
                View.GONE:View.VISIBLE);
        tv_Left.setText(str);
        return this;
    }

    public TitleBuilder setLeftOnClickListener(View.OnClickListener listener) {
        if (iv_Left.getVisibility() == View.VISIBLE) {
            iv_Left.setOnClickListener(listener);
        } else if (tv_Left.getVisibility() == View.VISIBLE) {
            tv_Left.setOnClickListener(listener);
        }
        return this;
    }

    public TitleBuilder setRightImage(int resId) {
        iv_Right.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        iv_Right.setImageResource(resId);
        return this;
    }

    public TitleBuilder setRightText(String text) {
        tv_Right.setVisibility(TextUtils.isEmpty(text) ? View.GONE
                : View.VISIBLE);
        tv_Right.setText(text);
        return this;
    }

    public TitleBuilder setRightOnClickListener(View.OnClickListener listener) {
        if (iv_Right.getVisibility() == View.VISIBLE) {
            iv_Right.setOnClickListener(listener);
        } else if (tv_Right.getVisibility() == View.VISIBLE) {
            tv_Right.setOnClickListener(listener);
        }
        return this;
    }

    public View build() {
        return viewTitle;
    }
}
