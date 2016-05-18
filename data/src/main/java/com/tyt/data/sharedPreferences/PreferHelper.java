package com.tyt.data.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by TYT on 2016/5/18.
 */
public class PreferHelper{
    private SharedPreferences mSharedPreferences;
    private String name;
    private SharedPreferences.Editor mEditor;

    public PreferHelper(Context context,String name){
        mSharedPreferences = context.getSharedPreferences(name,Context.MODE_PRIVATE);
        this.name=name;
        mEditor = mSharedPreferences.edit();
    }

    public SharedPreferences.Editor getEditor() {
        return mEditor;
    }

    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }
}
