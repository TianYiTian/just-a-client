package com.tyt.zimuzu;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by TYT on 2016/5/10.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
