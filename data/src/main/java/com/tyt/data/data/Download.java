package com.tyt.data.data;

import org.jsoup.nodes.Element;

/**
 * Created by TYT on 2016/5/23.
 */

public class Download {
    private String dianlv=null;
    private String cili=null;
    private String xunlei=null;
    private String baidu=null;
    private String xiaomi=null;
    private int episode=-1;
    private String format=null;
    private String size=null;
    private String name =null;

    public Download(Element element){
        if (element.getElementsByAttributeValue("type","ed2k").size()!=0){
            dianlv = element.getElementsByAttributeValue("type","ed2k").first().attr("href");
        }
        if (element.getElementsByAttributeValue("type","magnet").size()!=0){
            cili=element.getElementsByAttributeValue("type","magnet").first().attr("href");
        }
        if (element.getElementsByAttribute("thunderhref").size()!=0){
            xunlei=element.getElementsByAttribute("thunderhref").first().attr("thunderhref");
        }
        if (element.getElementsByAttributeValue("type","disk").size()!=0){
            baidu=element.getElementsByAttributeValue("type","disk").first().attr("href");
        }
        if (element.getElementsByAttribute("xmhref").size()!=0){
            xiaomi=element.getElementsByAttribute("xmhref").first().attr("xmhref");
        }
        if (element.getElementsByAttribute("format").size()!=0){
            format= element.getElementsByAttribute("format").first().attr("format");
        }
        if (element.getElementsByAttribute("episode").size()!=0){
            episode = Integer.parseInt(element.getElementsByAttribute("episode").first().attr("episode"));
        }
        if (element.getElementsByTag("font").size()!=0){
            size = element.getElementsByTag("font").first().ownText();
        }
        name = element.getElementsByAttribute("title").first().ownText();
    }

    public String getDianlv() {
        return dianlv;
    }

    public String getCili() {
        return cili;
    }

    public String getXunlei() {
        return xunlei;
    }

    public String getBaidu() {
        return baidu;
    }

    public String getXiaomi() {
        return xiaomi;
    }

    public int getEpisode() {
        return episode;
    }

    public String getFormat() {
        return format;
    }

    public String getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
