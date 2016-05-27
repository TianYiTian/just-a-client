package com.tyt.data.html;

import android.os.Handler;
import android.util.Log;

import com.tyt.data.data.Download;
import com.tyt.data.data.Season;
import com.tyt.data.data.SeasonDownload;
import com.tyt.data.http.OkHttpUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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
        ArrayList<Season> titles = new ArrayList<Season>(count);
        for (int i =0;i<count;i++){
            titles.add(new Season(Integer.parseInt(document.getElementsByClass("media-tab").get(0).children().get(i).attr("season")),document.getElementsByClass("media-tab").get(0).children().get(i).ownText()));
        }//TODO titles.entrySet.iterator,根据键名存
        String name;
        ArrayList<SeasonDownload> seasons = new ArrayList<SeasonDownload>();
        for (int i =0;i<titles.size();i++){
            LinkedList<Download> download = new LinkedList<Download>();
            for (int j =1;j<document.getElementsByAttributeValue("season",""+titles.get(i).getId()).size();j++){
                download.add(new Download(document.getElementsByAttributeValue("season",""+titles.get(i).getId()).get(j)));
            }
            seasons.add(new SeasonDownload(titles.get(i).getName(),download));
        }
        Log.w("seasons",""+seasons.size());
        return null;
    }
}
