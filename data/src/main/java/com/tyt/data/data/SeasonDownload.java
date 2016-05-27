package com.tyt.data.data;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by TYT on 2016/5/24.
 */
public class SeasonDownload {
    private String name=null;
    private LinkedList<Download> downloads=null;

    public SeasonDownload(String name,LinkedList<Download> downloads){
        this.name = name;
        this.downloads=downloads;
    }
}
