package com.tyt.data.data;

import java.util.ArrayList;

/**
 * Created by TYT on 2016/5/24.
 */
public class SeasonDownload {
    private String name=null;
    private ArrayList<Download> downloads=null;

    public SeasonDownload(String name,ArrayList<Download> downloads){
        this.name = name;
        this.downloads=downloads;
    }
}
