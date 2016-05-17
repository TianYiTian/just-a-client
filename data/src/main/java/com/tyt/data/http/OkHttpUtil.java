package com.tyt.data.http;

import android.os.HandlerThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
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
    public static OkHttpClient sOkHttpClient = null;
    private static ArrayList<Cookie> sCookies = null;
    static {

        sOkHttpClient = new OkHttpClient.Builder().cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                if (url.toString().equals("http://www.zimuzu.tv/User/Login/ajaxLogin")) {
                    sCookies = null;
                    if (cookies.size()>1){
                        sCookies = new ArrayList<Cookie>();
                        sCookies.add(cookies.get(0));
                        sCookies.add(cookies.get(3));
                        sCookies.add(cookies.get(4));
                    }
                }
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                if (sCookies==null){
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
}
