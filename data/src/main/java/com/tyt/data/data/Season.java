package com.tyt.data.data;

/**
 * Created by TYT on 2016/5/27.
 */
public class Season {
    private int id;
    private String name;
    public Season(int id,String name){
        this.id=id;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
