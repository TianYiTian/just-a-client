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
        //TODO
    }
}
