package com.tyt.data.html;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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

    public DetailParser(Handler handler){
        mainHandler=handler;
        HandlerThread handlerThread = new HandlerThread("detail");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mOkHttpClient = new OkHttpClient();
            }
        });
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
                    Element name = document.getElementsByClass("middle-box").first();


                    Message message = new Message();
                    message.what = PARSE_DONE;
                    mainHandler.sendMessage(message);
                    isLoading=false;

                }catch (Exception e){
                    Log.w("detail_parser",e.toString());
                }
            }
        };
        mHandler.post(mRunnable);
    }
}
