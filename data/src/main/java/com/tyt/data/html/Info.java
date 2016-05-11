package com.tyt.data.html;

/**
 * Created by TYT on 2016/5/10.
 */
public class Info {
    private String name=null;
    private String imgURL=null;
    public Info(String name,String imgURL){
        this.name=name;
        this.imgURL=imgURL;
    }
    public String getName(){
        return name;
    }
    public String getImgURL(){
        return imgURL;
    }
}
