package com.tyt.data.html;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by TYT on 2016/5/10.
 */
public class InfoParser {
   /* private static Infoparser instance;
    private static Object mLock = new Object();*/
    private OkHttpClient mOkHttpClient;
//    private static Handler sHandler;
    private Handler mHandler;
    private Handler mainHandler;
    public static final int PARSE_NEXTPAGE = 1;
    public static final int PARSE_PREVIOUS =-1;
    public static final int PARSE_CURRENT =0;
    volatile private boolean isLoading= false;
    private Runnable mRunnable;

    public InfoParser(Handler handler) {
        mainHandler=handler;
        HandlerThread handlerThread = new HandlerThread("info");
        handlerThread.start();
        mHandler= new Handler(handlerThread.getLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mOkHttpClient = new OkHttpClient();
            }
        });
    }

    /*public static Infoparser getInstance() {
        if (instance == null) {
            synchronized (mLock) {
                if (instance == null) {
                    HandlerThread handlerThread = new HandlerThread("network");
                    handlerThread.start();
                    sHandler = new Handler(handlerThread.getLooper());
                    sHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            instance = new Infoparser();
                        }
                    });

                }
            }
        }
        return instance;
    }*/

    public void parse(int channel,int area,int category,int year,int sort,int page,int whichpage){
        Category cate = Category.getInstance();
        parse(cate.channelList[channel],cate.areaList[area],cate.categoryList[category],cate.yearList[year],cate.sortList[sort],page,whichpage);
    }

    public void parse(final String channel, final String area, final String category, final String year, final String sort, final int page,final int whichpage) {
        if (isLoading){
            mHandler.removeCallbacks(mRunnable);
        }
        mRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    isLoading=true;
                    int findpage = page+whichpage;
                    Request request = new Request.Builder().url("http://www.zimuzu.tv/eresourcelist?page=" + findpage + "&channel=" + channel + "&area=" + area + "&category=" + category + "&format=&year=" + year + "&sort=" + sort).build();
                    Response response = mOkHttpClient.newCall(request).execute();
                    Document document = Jsoup.parse(response.body().string());
                    Elements data = document.getElementsByClass("resource-showlist").get(0).getElementsByTag("li");
                    Element element;
                    ArrayList<Info> infoList = new ArrayList<Info>(data.size());
                    for (int i = 0; i < data.size(); i++) {
                        element = data.get(i);
                        infoList.add(new Info(element.getElementsByClass("fl-info").get(0).getElementsByTag("a").get(0).ownText(), element.getElementsByClass("fl-img").get(0).getElementsByTag("img").get(0).attr("src"),element.getElementsByTag("a").get(0).attr("href")));
                    }
                    Message message = new Message();
                    message.obj=infoList;
                    message.what=whichpage;
                    mainHandler.sendMessage(message);
                    isLoading = false;
                } catch (Exception e) {
                    Log.w("info_parser", e.toString());
                }
            }
        };
        mHandler.post(mRunnable);
    }
}
