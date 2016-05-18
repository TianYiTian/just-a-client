package com.tyt.data.sharedPreferences;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import okhttp3.Cookie;

/**
 * Created by TYT on 2016/5/18.
 */
public class CookieHelper {
    private PreferHelper mPreferHelper;
    private static final String DIVIDER ="---";

    public CookieHelper(PreferHelper preferHelper){
        mPreferHelper = preferHelper;
    }

    public void writeCookies(ArrayList<Cookie> cookies){
        if (cookies!=null){
            SharedPreferences.Editor editor = mPreferHelper.getEditor();
            for (int i =0;i<cookies.size();i++){
                wirteCookie(editor,cookies.get(i));
            }
            editor.commit();
        }
    }
    private void wirteCookie(SharedPreferences.Editor editor,Cookie cookie){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cookie.domain()+DIVIDER).append(cookie.expiresAt()+DIVIDER).append(cookie.hostOnly()+DIVIDER).
                append(cookie.httpOnly()+DIVIDER).append(cookie.name()+DIVIDER).append(cookie.path()+DIVIDER).
                append(cookie.persistent()+DIVIDER).append(cookie.secure()+DIVIDER).append(cookie.value());
        editor.putString(cookie.name(),stringBuilder.toString());
    }

    public void clearCookies (){
        mPreferHelper.getEditor().clear();
    }

    public ArrayList<Cookie> readCookies(){
        HashMap hashMap =  (HashMap) mPreferHelper.getSharedPreferences().getAll();
        ArrayList<Cookie> cookies = new ArrayList<Cookie>();
        if (hashMap.size()!=0) {
            Iterator iterator = hashMap.keySet().iterator();
            while (iterator.hasNext()) {
                String s = iterator.next().toString();
                cookies.add(readCookie(hashMap.get(s).toString()));
            }
        }
        return cookies;
    }

    private Cookie readCookie(String s){
        String[] strings = s.split(DIVIDER);
        Cookie.Builder builder = new Cookie.Builder().hostOnlyDomain(strings[0]).expiresAt(Long.parseLong(strings[1])).name(strings[4]).path(strings[5]).value(strings[8]);
        return builder.build();
    }

}
