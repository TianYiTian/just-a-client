package com.tyt.data.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TYT on 2016/5/18.
 */
public class UserInfo {
    private int follow_unread_num;
    private int message_unread_num;
    @Expose
    private Object navbar=null;
    private String post=null;
    private int reply_unread_num;
    @SerializedName("userinfo")
    private User user=null;

    public int getFollow_unread_num() {
        return follow_unread_num;
    }

    public void setFollow_unread_num(int follow_unread_num) {
        this.follow_unread_num = follow_unread_num;
    }

    public int getMessage_unread_num() {
        return message_unread_num;
    }

    public void setMessage_unread_num(int message_unread_num) {
        this.message_unread_num = message_unread_num;
    }

    public Object getNavbar() {
        return navbar;
    }

    public void setNavbar(Object navbar) {
        this.navbar = navbar;
    }

    public int getReply_unread_num() {
        return reply_unread_num;
    }

    public void setReply_unread_num(int reply_unread_num) {
        this.reply_unread_num = reply_unread_num;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
