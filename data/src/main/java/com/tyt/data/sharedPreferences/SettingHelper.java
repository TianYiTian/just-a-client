package com.tyt.data.sharedPreferences;

import android.content.SharedPreferences;

/**
 * Created by TYT on 2016/5/18.
 */
public class SettingHelper {
    private PreferHelper mPreferHelper;
    private static final String AUTOLOGIN ="autologin";

    public SettingHelper(PreferHelper preferHelper){
        mPreferHelper =preferHelper;
    }

    public void setAutoLogin(boolean autoLogin){
        SharedPreferences.Editor editor=mPreferHelper.getEditor();
        editor.putBoolean(AUTOLOGIN,autoLogin);
        editor.commit();
    }
    public boolean getAutoLogin(){
        return mPreferHelper.getSharedPreferences().getBoolean(AUTOLOGIN,false);
    }
}
