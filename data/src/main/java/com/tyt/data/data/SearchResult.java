package com.tyt.data.data;

/**
 * Created by TYT on 2016/5/30.
 */

public class SearchResult {
    private String sort=null;
    private String name=null;
    private String url=null;
    private String imgUrl=null;

    public SearchResult(String sort,String name,String url,String imgUrl){
        this.sort =sort;
        this.name = name;
        this.url =url;
        this.imgUrl =imgUrl;
    }

    public String getSort() {
        return sort;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
