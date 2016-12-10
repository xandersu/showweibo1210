package com.xandersu.showweibo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xandersu.showweibo.R;
import com.xandersu.showweibo.utils.MyUIUtils;

/**
 * Created by lenovo on 2016/12/5.
 */

public class MessageFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(MyUIUtils.getContext(), R.layout.fragment_message,null);
        return view;
    }
}
