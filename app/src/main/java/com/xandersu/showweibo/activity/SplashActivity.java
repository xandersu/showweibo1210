package com.xandersu.showweibo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.xandersu.showweibo.R;
import com.xandersu.showweibo.constants.AccessTokenKeeper;
import com.xandersu.showweibo.constants.Constants;
import com.xandersu.showweibo.utils.MyUIUtils;

public class SplashActivity extends AppCompatActivity {

    /**
     * 显示认证后的信息，如 AccessToken
     */
//    private TextView mTokenText;

    private AuthInfo mAuthInfo;

    /**
     * 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能
     */
    private Oauth2AccessToken mAccessToken;

    /**
     * 注意：SsoHandler 仅当 SDK 支持 SSO 时有效
     */
    private SsoHandler mSsoHandler;

    private Button mBt_enter_main;
    private final int ENTER_MAIN = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            startActivity(new Intent(SplashActivity.this,MainActivity.class));
//            MyUIUtils.showToast("进入主界面");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        // 快速授权时，请不要传入 SCOPE，否则可能会授权不成功
        mAuthInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        mSsoHandler = new SsoHandler(SplashActivity.this, mAuthInfo);

        mBt_enter_main = (Button) findViewById(R.id.bt_enter_main);
        mBt_enter_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mHandler.sendEmptyMessage(ENTER_MAIN);
                MyUIUtils.showToast("正在授权......");

                mSsoHandler.authorize(new AuthListener());
//                finish();
            }
        });
    }

    /**
     * 当 SSO 授权 Activity 退出时，该函数被调用。
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }

    }

    /**
     * 微博认证授权回调类。
     * 1. SSO 授权时，需要在 {@link #onActivityResult} 中调用 {@link SsoHandler#authorizeCallBack} 后，
     * 该回调才会被执行。
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
     */
    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            //从这里获取用户输入的 电话号码信息 
            String phoneNum = mAccessToken.getPhoneNum();
            if (mAccessToken.isSessionValid()) {
                // 显示 Token
//                updateTokenView(false);

                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                com.xandersu.showweibo.utils.MyUIUtils.showToast("授权成功，进入主界面...");
                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(SplashActivity.this, mAccessToken);
//                Toast.makeText(SplashActivity.this,
//                        R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();
                MyUIUtils.showToast("授权成功");
                finish();
            } else {
                // 以下几种情况，您会收到 Code：
                // 1. 当您未在平台上注册的应用程序的包名与签名时；
                // 2. 当您注册的应用程序包名与签名不正确时；
                // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                String code = values.getString("code");
                String message = getString(R.string.weibosdk_demo_toast_auth_failed);
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
//                Toast.makeText(SplashActivity.this, message, Toast.LENGTH_LONG).show();
                MyUIUtils.showToast(message);
            }
        }

        @Override
        public void onCancel() {
//            Toast.makeText(SplashActivity.this,
//                    R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
            MyUIUtils.showToast("取消授权");
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(SplashActivity.this,
                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
//            MyUIUtils.showToast("Auth exception : " + e.getMessage());
        }
    }

//    /**
//     * 显示当前 Token 信息。
//     *
//     * @param hasExisted 配置文件中是否已存在 token 信息并且合法
//     */
//    private void updateTokenView(boolean hasExisted) {
//        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
//                new java.util.Date(mAccessToken.getExpiresTime()));
//        String format = getString(R.string.weibosdk_demo_token_to_string_format_1);
//        mTokenText.setText(String.format(format, mAccessToken.getToken(), date));
//
//        String message = String.format(format, mAccessToken.getToken(), date);
//        if (hasExisted) {
//            message = getString(R.string.weibosdk_demo_token_has_existed) + "\n" + message;
//        }
//        mTokenText.setText(message);
//    }
}
