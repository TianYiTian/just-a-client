package com.tyt.data.json;

import com.tyt.data.data.UserInfo;

/**
 * Created by TYT on 2016/5/18.
 */
public class UserLoader {
    private static UserInfo sUserInfo=null;


    public static UserInfo getUserInfo() {
        return sUserInfo;
    }

    public static UserInfo loadUserInfo() {
        return null;
    }
}
