package com.tyt.data.http;

import android.accounts.Account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by TYT on 2016/5/17.
 */
public class OkHttpUtil {
    private static OkHttpClient sOkHttpClient = null;
    private static ArrayList<Cookie> sCookies = new ArrayList<Cookie>(6);
    static {
        sCookies.add(new Cookie.Builder().domain("zimuzu.tv").path("/").httpOnly().name("srcurl").value("687474703a2f2f7777772e7a696d757a752e74762f").build());

        sOkHttpClient = new OkHttpClient.Builder().cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                /*if (cookies.size()==1){
                    *//*if (cookies.get(0).name().equals("yunsuo_session_verify")){
                        sCookies.add(cookies.get(0));
                    }
                    if (cookies.get(0).name().equals("security_session_mid_verify")){
                        sCookies.add(cookies.get(0));
                    }*//*
                    int count =-1;
                    for (int i =0;i<sCookies.size();i++){
                        if (sCookies.get(i).name().equals(cookies.get(0).name())){
                            count=i;
                        }
                    }
                    if (count==-1){
                        sCookies.add(cookies.get(0));
                    }else{
                        sCookies.set(count,cookies.get(0));
                    }
                }else {
                    if (url.toString().equals("http://www.zimuzu.tv/User/Login/ajaxLogin")) {
                        if (cookies.size() > 1) {
                            sCookies = new ArrayList<Cookie>();
                            sCookies.add(cookies.get(0));
                            sCookies.add(cookies.get(3));
                            sCookies.add(cookies.get(4));
                        }
                    }
                    if (url.toString().equals("http://www.zimuzu.tv/user/logout/ajaxLogout")) {
                        sCookies = new ArrayList<Cookie>(6);
                        sCookies.add(new Cookie.Builder().domain("zimuzu.tv").path("/").httpOnly().name("srcurl").value("687474703a2f2f7777772e7a696d757a752e74762f").build());
                    }*/

                for (int i = 0; i < cookies.size(); i++) {
                    int count=-1;
                    for (int j =0;j<sCookies.size();j++){
                        if (cookies.get(i).name().equals(sCookies.get(j).name())){
                            count=j;
                        }
                    }
                    if (count==-1){
                        sCookies.add(cookies.get(i));
                    }else{
                        sCookies.set(count,cookies.get(i));
                    }
                }
                for (int i=sCookies.size()-1;i>=0;i--){
                    if (sCookies.get(i).value().equals("deleted")){
                        sCookies.remove(i);
                    }
                }
            }


            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                if (sCookies==null||sCookies.size()==0){
                    return Collections.emptyList();
                }else{
                    return sCookies;
                }
            }
        }).build();
    }
    public static boolean login(String username, String password, String remember)throws IOException{
        RequestBody requestBody = new FormBody.Builder().add("account",username).add("password",password).add("remember",remember).build();
        Request request = new Request.Builder().url("http://www.zimuzu.tv/User/Login/ajaxLogin").post(requestBody).build();
        Response response = sOkHttpClient.newCall(request).execute();
        return sCookies!=null;
    }
    public static boolean logout()throws IOException{
        Request request = new Request.Builder().url("http://www.zimuzu.tv/user/logout/ajaxLogout").build();
        Response response = sOkHttpClient.newCall(request).execute();
        return response.isSuccessful();
    }

    public static void getYunsuo() throws IOException{
        Request request1 = new Request.Builder().url("http://www.zimuzu.tv/").build();
        Response response1 = sOkHttpClient.newCall(request1).execute();
        response1.body().close();
        Request request2 = new Request.Builder().url("http://www.zimuzu.tv/?security_verify_data=313730362c393630").build();
        Response response2 = sOkHttpClient.newCall(request2).execute();
        response2.body().close();
    }

    public static ArrayList<Cookie> getCookies() {
        return sCookies;
    }

    public static OkHttpClient getOkHttpClient() {
        return sOkHttpClient;
    }

    public static void setCookies(ArrayList<Cookie> cookies) {
        sCookies = cookies;
    }

    public static void addCookies(ArrayList<Cookie> cookies){
        sCookies.addAll(cookies);
    }

}
