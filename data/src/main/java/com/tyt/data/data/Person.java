package com.tyt.data.data;

/**
 * Created by admin on 2016/5/12.
 */
public class Person {
    private String name;
    private String URL;

    public Person(String name,String URL){
        this.name=name;
        this.URL=URL;
    }

    public String getName() {
        return name;
    }

    public String getURL() {
        return URL;
    }
}
