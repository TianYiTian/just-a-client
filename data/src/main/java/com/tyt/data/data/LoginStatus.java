package com.tyt.data.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TYT on 2016/5/19.
 */
public class LoginStatus {

    @SerializedName("data")
    private UserInfo userinfo=null;

    private String info=null;
    private int status=0;

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
