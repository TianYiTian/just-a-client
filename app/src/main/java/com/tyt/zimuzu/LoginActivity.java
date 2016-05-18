package com.tyt.zimuzu;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tyt.data.constant.Constance;
import com.tyt.data.http.OkHttpUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Cookie;
import okhttp3.OkHttpClient;

/**
 * Created by TYT on 2016/5/17.
 */
public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)Toolbar mToolbar;
    @Bind(R.id.username)AppCompatEditText username;
    @Bind(R.id.password)AppCompatEditText password;
    @Bind(R.id.remember)AppCompatCheckBox remember;

    private boolean isLogining = false;
    private Handler mHandler;
    private boolean success=false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mToolbar.setTitle("登录");
        /*mToolbar.setNavigationIcon(R.drawable.ic_pre);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("login","goback");
            }
        });*/

        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription("login");
        mHandler = new Handler(getMainLooper());
    }

    @OnClick({R.id.login})
    public void login(View view) {
        if (!isLogining) {
            isLogining=true;
            ((MyApplication) getApplication()).getHandler().post(new Runnable() {
                @Override
                public void run() {
                    String rememberstring;
                    if (remember.isChecked()){
                        rememberstring="1";
                    }else {
                        rememberstring="0";
                    }
                    try {
                        success = OkHttpUtil.login(username.getText().toString(), password.getText().toString(), rememberstring);
                        showLoginMessage(success);
                        if (success) {
                            if (remember.isChecked()){
                                ((MyApplication)getApplication()).getCookieHelper().writeCookies(OkHttpUtil.getCookies());
                                ((MyApplication)getApplication()).getSettingHelper().setAutoLogin(true);
                            }else{
                                ((MyApplication)getApplication()).getCookieHelper().clearCookies();
                                ((MyApplication)getApplication()).getSettingHelper().setAutoLogin(false);
                            }
                            setResult(Constance.LOGIN_SUCCESSFUL);
                            LoginActivity.this.finish();
                        }
                    }catch (Exception e){
                        Log.w("login",e.toString());
                    }finally {
                        isLogining=false;
                    }
                }
            });
        }
    }

    private void showLoginMessage(final boolean success){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                String s;
                if (success) {
                    s="登录成功";
                }else{
                    s="登录失败";
                }
                Toast toast = Toast.makeText(LoginActivity.this.getApplicationContext(), s, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Log.w("test","return");
                setResult(Constance.LOGIN_FAILED);
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        setResult(Constance.LOGIN_FAILED);
        this.finish();
    }


}
