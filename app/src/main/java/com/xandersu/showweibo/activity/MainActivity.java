package com.xandersu.showweibo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.xandersu.showweibo.R;
import com.xandersu.showweibo.fragment.FragmentController;
import com.xandersu.showweibo.utils.MyUIUtils;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private android.widget.ImageView iv_add;
    private android.widget.RadioGroup rg_tab;
    private FragmentController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        this.rg_tab = (RadioGroup) findViewById(R.id.rg_tab);
        this.iv_add = (ImageView) findViewById(R.id.iv_add);

        mController = FragmentController.getInstance(this, R.id.fl_content);
        mController.showFragment(0);

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUIUtils.showToast("图片被点击了...");
            }
        });

        rg_tab.setOnCheckedChangeListener(this);


    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.rb_home:
                mController.showFragment(0);
            break;
            case R.id.rb_message:
                mController.showFragment(1);
            break;
            case R.id.rb_search:
                mController.showFragment(2);
            break;
            case R.id.rb_user:
                mController.showFragment(3);
            break;



            default:
            break;
        }
    }
}
