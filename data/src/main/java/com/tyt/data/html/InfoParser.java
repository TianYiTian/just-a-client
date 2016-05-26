package com.tyt.data.html;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.tyt.data.data.Category;
import com.tyt.data.data.Info;
import com.tyt.data.http.OkHttpUtil;

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
        mOkHttpClient= OkHttpUtil.getOkHttpClient();
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
                    Request request1 = new Request.Builder().url("http://www.zimuzu.tv/").build();
                    Response response1 = mOkHttpClient.newCall(request1).execute();
                    String s = response1.body().string();

                    Request request2 = new Request.Builder().url("http://www.zimuzu.tv/?security_verify_data=313730362c393630").build();
                    Response response2 = mOkHttpClient.newCall(request2).execute();
                    ///Request request = new Request.Builder().url("http://www.zimuzu.tv/eresourcelist?page=" + findpage + "&channel=" + channel + "&area=" + area + "&category=" + category + "&format=&year=" + year + "&sort=" + sort).header("Referer","http://www.zimuzu.tv/?security_verify_data=313730362c393630").build();
                    Request request = new Request.Builder().url("http://www.zimuzu.tv/eresourcelist?security_verify_data=313730362c393630").build();
                    Response response = mOkHttpClient.newCall(request).execute();
                    Document document = Jsoup.parse(response.body().string());
                    Elements data = document.getElementsByClass("resource-showlist").get(0).getElementsByTag("li");
                    Element element;
                    ArrayList<Info> infoList = new ArrayList<Info>(data.size());
                    for (int i = 0; i < data.size(); i++) {
                        element = data.get(i);
                        infoList.add(new Info(getName(element), getImgURL(element),getInfoURL(element)));
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

    private String getName(Element element){
        return element.getElementsByClass("fl-info").get(0).getElementsByTag("a").get(0).ownText();
    }
    private String getImgURL(Element element){
        return element.getElementsByClass("fl-img").get(0).getElementsByTag("img").get(0).attr("src");
    }
    private String getInfoURL(Element element){
        return element.getElementsByTag("a").get(0).attr("href");
    }

}
