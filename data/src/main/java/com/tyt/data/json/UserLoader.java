package com.tyt.data.json;

import android.util.Log;

import com.google.gson.Gson;
import com.tyt.data.data.LoginStatus;
import com.tyt.data.data.UserInfo;
import com.tyt.data.http.OkHttpUtil;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by TYT on 2016/5/18.
 */
public class UserLoader {
    private static LoginStatus sLoginStatus = null;


    public static boolean load() throws IOException{
        Request request = new Request.Builder().url("http://www.zimuzu.tv/user/login/getCurUserTopInfo").build();
        Response response = OkHttpUtil.getOkHttpClient().newCall(request).execute();
        Gson gson = new Gson();
        sLoginStatus = gson.fromJson(response.body().string(), LoginStatus.class);
        return sLoginStatus.getStatus() == 1;
    }

    public static LoginStatus getLoginStatus() {
        return sLoginStatus;
    }
}
