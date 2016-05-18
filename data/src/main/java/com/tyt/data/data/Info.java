package com.tyt.data.data;

/**
 * Created by TYT on 2016/5/10.
 */
public class Info {
    private String name = null;
    private String imgURL = null;
    private String infoURL = null;

    public Info(String name, String imgURL, String infoURL) {
        this.name = name;
        this.imgURL = imgURL;
        this.infoURL = infoURL;
    }

    public String getName() {
        return name;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getInfoURL() {
        return infoURL;
    }
}
