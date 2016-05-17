package com.tyt.zimuzu;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by TYT on 2016/5/10.
 */
public class MyApplication extends Application{
    private HandlerThread mHandlerThread=null;
    private Handler mHandler=null;


    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mHandlerThread = new HandlerThread("network");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());
    }

    public Handler getHandler() {
        return mHandler;
    }
}
