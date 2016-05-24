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
        //TODO
    }
}
