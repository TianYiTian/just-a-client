package com.tyt.data.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TYT on 2016/5/18.
 */
public class User {
    @SerializedName("avatar_t")
    private String imgURL=null;
    private int common_group_id;
    private String group_name;
    private String nickname;
    private int uid;

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getCommon_group_id() {
        return common_group_id;
    }

    public void setCommon_group_id(int common_group_id) {
        this.common_group_id = common_group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
