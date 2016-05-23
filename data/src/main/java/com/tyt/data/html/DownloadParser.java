package com.tyt.data.html;

import android.os.Handler;

import com.tyt.data.data.Download;
import com.tyt.data.http.OkHttpUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by TYT on 2016/5/23.
 */

public class DownloadParser {
    public static ArrayList<Download> parse(String s)throws IOException{
        Request request = new Request.Builder().url(s).build();
        Response response = OkHttpUtil.getOkHttpClient().newCall(request).execute();
        Document document = Jsoup.parse(response.body().string());
        int count = document.getElementsByClass("media-tab").get(0).childNodeSize();
        HashMap<String,String> titles = new HashMap<String, String>(count);
        for (int i =0;i<count;i++){
            titles.put(document.getElementsByClass("media-tab").get(0).children().get(i).attr("season"),document.getElementsByClass("media-tab").get(0).children().get(i).ownText());
        }//TODO
        return null;
    }
}
