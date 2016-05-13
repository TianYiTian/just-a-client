package com.tyt.data.html;

import java.util.ArrayList;

/**
 * Created by TYT on 2016/5/12.
 */
public class Detail {
    private String downloadURL=null;
    private String name=null;
    private String note=null;
    private String year=null;
    private String category=null;
    private String area=null;
    private String station=null;
    private String language=null;
    private String firstShow=null;
    private String enName=null;
    private ArrayList<Person> screenwriter=null;
    private ArrayList<Person> director=null;
    private ArrayList<Person> actor=null;
    private String imdb=null;
    private String website=null;
    private String summary=null;
    private String imgURL=null;


    public Detail(String downloadURL, String name, String note, String year, String category, String area, String station, String language, String firstShow, String enName, ArrayList<Person> screenwriter, ArrayList<Person> director, ArrayList<Person> actor, String imdb, String website, String summary, String imgURL) {
        this.downloadURL = downloadURL;
        this.name = name;
        this.note = note;
        this.year = year;
        this.category = category;
        this.area = area;
        this.station = station;
        this.language = language;
        this.firstShow = firstShow;
        this.enName = enName;
        this.screenwriter = screenwriter;
        this.director = director;
        this.actor = actor;
        this.imdb = imdb;
        this.website = website;
        this.summary = summary;
        this.imgURL = imgURL;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public String getYear() {
        return year;
    }

    public String getCategory() {
        return category;
    }

    public String getArea() {
        return area;
    }

    public String getStation() {
        return station;
    }

    public String getLanguage() {
        return language;
    }

    public String getFirstShow() {
        return firstShow;
    }

    public String getEnName() {
        return enName;
    }

    public ArrayList<Person> getScreenwriter() {
        return screenwriter;
    }

    public ArrayList<Person> getActor() {
        return actor;
    }

    public ArrayList<Person> getDirector() {
        return director;
    }

    public String getImdb() {
        return imdb;
    }

    public String getWebsite() {
        return website;
    }

    public String getSummary() {
        return summary;
    }

    public String getImgURL() {
        return imgURL;
    }

    public int getCount(){
        return 15;
    }
}
