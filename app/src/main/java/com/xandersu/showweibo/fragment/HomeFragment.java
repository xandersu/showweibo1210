package com.xandersu.showweibo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xandersu.showweibo.R;
import com.xandersu.showweibo.utils.MyUIUtils;
import com.xandersu.showweibo.utils.TitleBuilder;

/**
 * Created by lenovo on 2016/12/5.
 */

public class HomeFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(com.xandersu.showweibo.utils.MyUIUtils.getContext(), R.layout.fragment_home,null);

        new TitleBuilder(view)
                .setTitleText("首页")
                .setLeftText("Left")
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyUIUtils.showToast("left onclick");
                    }
                });

        return view;
    }
}
