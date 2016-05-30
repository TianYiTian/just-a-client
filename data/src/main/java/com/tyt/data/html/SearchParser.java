package com.tyt.data.html;

import com.tyt.data.data.SearchResult;
import com.tyt.data.http.OkHttpUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2016/5/12.
 */
public class SearchParser {


    public static ArrayList<SearchResult> parse(String url)throws IOException{
        //TODO
        Request request = new Request.Builder().url(url).build();
        Response response = OkHttpUtil.getOkHttpClient().newCall(request).execute();
        Document document = Jsoup.parse(response.body().string());
        response.body().close();
        int size = document.getElementsByClass("search-item").size();
        ArrayList<SearchResult> results = new ArrayList<SearchResult>(size);
        for (int i =0;i<size;i++){
            Element element = document.getElementsByClass("search-item").get(i);
            results.add(new SearchResult(
                    element.getElementsByTag("em").first().ownText(),
                    element.getElementsByClass("list_title").first().ownText(),
                    "http://www.zimuzu.tv"+element.getElementsByAttribute("href").first().attr("href"),
                    element.getElementsByAttribute("src").first().attr("src")
            ));
        }
        return results;
    }
}
