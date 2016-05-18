package com.tyt.data.html;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.tyt.data.data.Detail;
import com.tyt.data.data.Person;
import com.tyt.data.http.OkHttpUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by TYT on 2016/5/12.
 */
public class DetailParser {
    private Handler mainHandler;
    private Handler mHandler;
    private OkHttpClient mOkHttpClient;
    private Runnable mRunnable;
    private boolean isLoading = false;
    public static final int PARSE_DONE = 1;
    volatile private List<Cookie> cookies =null;

    public DetailParser(Handler handler){
        mainHandler=handler;
        HandlerThread handlerThread = new HandlerThread("detail");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());
       /* mHandler.post(new Runnable() {
            @Override
            public void run() {
                mOkHttpClient = new OkHttpClient.Builder().cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        if (url.toString().equals("http://www.zimuzu.tv/User/Login/ajaxLogin")) {
                            List<Cookie> list = new ArrayList<Cookie>();
                            list.add(cookies.get(0));
                            list.add(cookies.get(3));
                            list.add(cookies.get(4));
                            DetailParser.this.cookies = list;
                        }
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        if (cookies==null){
                        return Collections.emptyList();
                        }else{
                            return DetailParser.this.cookies;
                        }
                    }
                }).build();
            }
        });*/
        mOkHttpClient = OkHttpUtil.getOkHttpClient();
    }
    public void parse(final String URL){
        if (isLoading){
            mHandler.removeCallbacks(mRunnable);
        }
        mRunnable = new Runnable() {
            @Override
            public void run() {
                try{
                    isLoading =true;
                    Request request = new Request.Builder().url(URL).build();
                    Response response = mOkHttpClient.newCall(request).execute();
                    Document document = Jsoup.parse(response.body().string());
                    Element info = document.getElementsByClass("fl-info").first();
                    Element download = document.getElementsByClass("download-tab").first();
                    Element resource = document.getElementsByClass("resource-con").first();
                    Detail detail = new Detail(getDownloadURL(download),getName(resource),getNote(resource),getYear(info),getCategory(info),
                            getArea(info),getStation(info),getLanguage(info),getFirstShow(info),getEnName(info),getScreenWriter(info),
                            getDirector(info),getActor(info),getIMDB(info),getWebSite(info),getSummary(info),getImgURL(document));
                    Message message = new Message();
                    message.what = PARSE_DONE;
                    message.obj = detail;
                    mainHandler.sendMessage(message);
                    isLoading=false;
                }catch (Exception e){
                    Log.w("detail_parser",e.toString());
                }
            }
        };
        mHandler.post(mRunnable);
    }

    private String getDownloadURL(Element download){
        return "http://www.zimuzu.tv"+download.getElementsByTag("a").attr("href");
    }
    private String getName(Element resource){
        return resource.getElementsByTag("h2").first().text();
    }
    private String getNote(Element resource){
        String[] strings = resource.getElementsByTag("p").first().text().split("：",2);
        if (strings.length!=0){
            return strings[1];
        }else{
            return null;
        }
    }
    private String getYear(Element info){
        return info.getElementsByTag("li").get(0).getElementsByTag("strong").get(0).ownText();
    }
    private String getCategory(Element info){
        return  info.getElementsByTag("li").get(0).getElementsByTag("strong").get(1).ownText();
    }
    private String getArea(Element info){
        return info.getElementsByTag("li").get(1).getElementsByTag("strong").get(0).ownText();
    }
    private String getStation(Element info){
        return info.getElementsByTag("li").get(1).getElementsByTag("strong").get(1).ownText();
    }
    private String getLanguage(Element info){
        return info.getElementsByTag("li").get(2).getElementsByTag("strong").get(0).ownText();
    }
    private String getFirstShow(Element info){
        return info.getElementsByTag("li").get(2).getElementsByTag("strong").get(1).ownText();
    }
    private String getEnName(Element info){
        return info.getElementsByTag("li").get(3).getElementsByTag("strong").size()!=0?info.getElementsByTag("li").get(3).getElementsByTag("strong").first().ownText():info.getElementsByTag("li").get(3).ownText();
    }
    private String getSummary(Element info){
        return info.getElementsByClass("rel").last().getElementsByTag("div").first().ownText();
    }
    private ArrayList<Person> getActor(Element info){
        if (info.getElementsByClass("rel").size()==2){
            int size = info.getElementsByClass("rel").first().getElementsByTag("a").size();
            ArrayList<Person> arrayList = new ArrayList<Person>(size);
            for (int i=0;i<size;i++){
                arrayList.add(new Person(info.getElementsByClass("rel").first().getElementsByTag("a").get(i).ownText(),
                        "http://www.zimuzu.tv"+info.getElementsByClass("rel").first().getElementsByTag("a").get(i).attr("href")));
            }
            return arrayList;
        }else{
            return null;
        }
    }
    private ArrayList<Person> getScreenWriter(Element info){
        if (info.getElementsByClass("rel").first().getElementsByTag("a").size()!=info.getElementsByTag("a").size()-2) {
            for (int i = 4; i < info.getElementsByTag("li").size(); i++) {
                if (info.getElementsByTag("li").get(i).getElementsByAttribute("class").size() != 0&&info.getElementsByTag("li").get(i).getElementsByAttribute("rel").size()==0&&info.getElementsByTag("li").get(i).getElementsByClass("rel").size()==0) {
                    int size = info.getElementsByTag("li").get(i).getElementsByTag("a").size();
                    ArrayList<Person> arrayList = new ArrayList<Person>(size);
                    for (int j = 0; j < size; j++) {
                        arrayList.add(new Person(info.getElementsByTag("li").get(i).getElementsByTag("a").get(j).ownText(),
                                "http://www.zimuzu.tv"+info.getElementsByTag("li").get(i).getElementsByTag("a").get(j).attr("href")));
                    }
                    return arrayList;
                }
            }
        }
        return null;
    }

    private ArrayList<Person> getDirector(Element info) {
        if (info.getElementsByClass("rel").first().getElementsByTag("a").size()!=info.getElementsByTag("a").size()-2) {
            int count=0;
            for (int i = 4; i < info.getElementsByTag("li").size(); i++) {
                if (info.getElementsByTag("li").get(i).getElementsByAttribute("class").size() != 0&&info.getElementsByTag("li").get(i).getElementsByAttribute("rel").size()==0&&info.getElementsByTag("li").get(i).getElementsByClass("rel").size()==0) {
                    count += 1;
                    if (count == 2) {
                        int size = info.getElementsByTag("li").get(i).getElementsByTag("a").size();
                        ArrayList<Person> arrayList = new ArrayList<Person>(size);
                        for (int j = 0; j < size; j++) {
                            arrayList.add(new Person(info.getElementsByTag("li").get(i).getElementsByTag("a").get(j).ownText(),
                                    "http://www.zimuzu.tv"+info.getElementsByTag("li").get(i).getElementsByTag("a").get(j).attr("href")));
                        }
                        return arrayList;
                    }
                }
            }
        }
        return null;
    }

    private String getWebSite(Element info){
        String string;
        for (int i=0;i<info.getElementsByAttribute("rel").size();i++){
            string = info.getElementsByAttribute("rel").get(i).attr("href");
            if (!string.contains("imdb")){
                return string;
            }
        }
        return null;
    }

    private String getIMDB(Element info){
        String string;
        for (int i=0;i<info.getElementsByAttribute("rel").size();i++){
            string = info.getElementsByAttribute("rel").get(i).attr("href");
            if (string.contains("imdb")){
                return string;
            }
        }
        return null;
    }
    private String getImgURL(Document document){
        return document.getElementsByClass("fl-img").first().getElementsByTag("a").first().attr("href");
    }
}
