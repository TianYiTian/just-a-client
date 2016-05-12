package com.tyt.data.html;

import java.util.ArrayList;

/**
 * Created by TYT on 2016/5/12.
 */
public class Detail {
    private String downloadURL;
    private String name;
    private String note;
    private String year;
    private String category;
    private String station;
    private String language;
    private String firstShow;
    private String enName;
    private ArrayList<Person> screenwriter;
    private ArrayList<Person> director;
    private ArrayList<Person> actor;
    private String website;
    private String summary;

    public Detail(String downloadURL,String name,String note,String year,String category,String station,String language,String firstShow,String enName,ArrayList<Person> screenwriter,ArrayList<Person> director,ArrayList<Person> actor,String website,String summary){
        this.downloadURL=downloadURL;
        this.name=name;
        this.note=note;
        this.year=year;
        this.category=category;
        this.station=station;
        this.language=language;
        this.firstShow=firstShow;
        this.enName=enName;
        this.screenwriter=screenwriter;
        this.director=director;
        this.actor=actor;
        this.website=website;
        this.summary=summary;
    }

    public String getDownloadURL(){
        return downloadURL;
    }
    public String getName(){
        return name;
    }
    public String getNote(){
        return note;
    }
    public String getYear(){
        return year;
    }
    public String getCategory(){
        return category;
    }
    public String getStation(){
        return station;
    }
    public String getLanguage(){
        return language;
    }
    public String getFirstShow(){
        return firstShow;
    }
    public String getEnName(){
        return enName;
    }
    public ArrayList<Person> getScreenwriter(){
        return screenwriter;
    }

    public ArrayList<Person> getActor() {
        return actor;
    }

    public ArrayList<Person> getDirector() {
        return director;
    }

    public String getWebsite() {
        return website;
    }

    public String getSummary() {
        return summary;
    }
}
