package com.xandersu.showweibo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/12/5.
 */

public class FragmentController {

    private int containerId;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private ArrayList<Fragment> fragmentLists = new ArrayList<>();

    private FragmentController(){

    }

    private static FragmentController controller ;

    private FragmentController(Activity activity, int containerId) {

        this.containerId = containerId;
        mFragmentManager = activity.getFragmentManager();
        initFragment();
    }

    public static FragmentController getInstance(Activity activity , int containerId){
        if( controller == null){
            controller = new FragmentController(activity,containerId);
        }
        return controller;
    }

    private void initFragment() {

        fragmentLists.add(new HomeFragment());
        fragmentLists.add(new MessageFragment());
        fragmentLists.add(new SearchFragment());
        fragmentLists.add(new UserFragment());

        mFragmentTransaction = mFragmentManager.beginTransaction();
        for (Fragment fragment:fragmentLists) {
            mFragmentTransaction.add(containerId,fragment);
        }
        mFragmentTransaction.commit();
    }

    public void showFragment(int position){
        hideFragments();
        Fragment fragment = fragmentLists.get(position);
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.show(fragment);
        ft.commit();
    }

    private void hideFragments() {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        for (Fragment fragment:fragmentLists) {
            if(fragment != null ){
                ft.hide(fragment);
            }
        }
        ft.commit();
    }

    public Fragment getFragment(int position){
        return fragmentLists.get(position);
    }

}
