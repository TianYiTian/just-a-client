package com.tyt.data.html;

import android.os.Handler;
import android.util.Log;

import com.tyt.data.data.Download;
import com.tyt.data.data.SeasonDownload;
import com.tyt.data.http.OkHttpUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by TYT on 2016/5/23.
 */

public class DownloadParser {
    public static ArrayList<SeasonDownload> parse(String s)throws IOException{
        Request request = new Request.Builder().url(s).build();
        Response response = OkHttpUtil.getOkHttpClient().newCall(request).execute();
        Document document = Jsoup.parse(response.body().string());
        int count = document.getElementsByClass("media-tab").get(0).childNodeSize();
        HashMap<String,String> titles = new HashMap<String, String>(count);
        for (int i =0;i<count;i++){
            titles.put(document.getElementsByClass("media-tab").get(0).children().get(i).attr("season"),document.getElementsByClass("media-tab").get(0).children().get(i).ownText());
        }//TODO titles.entrySet.iterator,根据键名存
        String name;
        ArrayList<SeasonDownload> seasons = new ArrayList<SeasonDownload>();
        Iterator<Map.Entry<String,String>> iterator = titles.entrySet().iterator();
        Map.Entry<String,String> piece;
        while (iterator.hasNext()){
            piece = iterator.next();
            ArrayList<Download> download = new ArrayList<Download>();
            for (int i =1;i<document.getElementsByAttributeValue("season",piece.getKey()).size();i++){
                download.add(new Download(document.getElementsByAttributeValue("season",piece.getKey()).get(i)));
            }
            seasons.add(new SeasonDownload(piece.getValue(),download));
        }
        Log.w("seasons",""+seasons.size());
        return null;
    }
}
