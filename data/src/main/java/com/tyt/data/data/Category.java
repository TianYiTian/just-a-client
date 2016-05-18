package com.tyt.data.data;

import android.util.Log;

import com.tyt.data.BuildConfig;

/**
 * Created by TYT on 2016/5/10.
 */
public class Category {
    private static Category instance;
    private static Object mLock = new Object();
    private static final String channel_0 = "";
    private static final String channel_1 = "movie";
    private static final String channel_2 = "tv";
    private static final String channel_3 = "documentary";
    private static final String channel_4 = "openclass";
    private static final String area_0 = "";
    private static final String area_1 = "美国";
    private static final String area_2 = "大陆";
    private static final String area_3 = "日本";
    private static final String area_4 = "韩国";
    private static final String area_5 = "英国";
    private static final String area_6 = "香港";
    private static final String area_7 = "台湾";
    private static final String area_8 = "印度";
    private static final String area_9 = "法国";
    private static final String area_10 = "加拿大";
    private static final String area_11 = "西班牙";
    private static final String area_12 = "新加坡";
    private static final String area_13 = "泰国";
    private static final String area_14 = "意大利";
    private static final String area_15 = "德国";
    private static final String area_16 = "俄罗斯";
    private static final String area_17 = "越南";
    private static final String area_18 = "澳大利亚";
    private static final String area_19 = "墨西哥";
    private static final String area_20 = "巴西";
    private static final String area_21 = "其他";
    private static final String category_0 = "";
    private static final String category_1 = "动作";
    private static final String category_2 = "战争";
    private static final String category_3 = "剧情";
    private static final String category_4 = "喜剧";
    private static final String category_5 = "生活";
    private static final String category_6 = "偶像";
    private static final String category_7 = "青春";
    private static final String category_8 = "魔幻";
    private static final String category_9 = "科幻";
    private static final String category_10 = "历史";
    private static final String category_11 = "纪录";
    private static final String category_12 = "暴力";
    private static final String category_13 = "血腥";
    private static final String category_14 = "歌舞";
    private static final String category_15 = "恐怖";
    private static final String category_16 = "惊悚";
    private static final String category_17 = "悬疑";
    private static final String category_18 = "古装";
    private static final String category_19 = "史诗";
    private static final String category_20 = "丧尸";
    private static final String category_21 = "爱情";
    private static final String category_22 = "医务";
    private static final String category_23 = "律政";
    private static final String category_24 = "真人秀";
    private static final String category_25 = "励志";
    private static final String category_26 = "谍战";
    private static final String category_27 = "罪案";
    private static final String category_28 = "冒险";
    private static final String category_29 = "动画";
    private static final String category_30 = "科教";
    private static final String category_31 = "西部";
    private static final String category_32 = "枪战";
    private static final String category_33 = "灾难";
    private static final String category_34 = "传记";
    private static final String category_35 = "幽默";
    private static final String category_36 = "讽刺";
    private static final String category_37 = "童话";
    private static final String category_38 = "幻想";
    private static final String category_39 = "综艺";
    private static final String year_0 = "";
    private static final String year_1 = "2016";
    private static final String year_2 = "2015";
    private static final String year_3 = "2014";
    private static final String year_4 = "2013";
    private static final String year_5 = "2012";
    private static final String year_6 = "2011";
    private static final String year_7 = "2010";
    private static final String year_8 = "2009";
    private static final String year_9 = "2008";
    private static final String year_10 = "2007";
    private static final String year_11 = "2006";
    private static final String year_12 = "2005";
    private static final String year_13 = "2004";
    private static final String year_14 = "2003";
    private static final String year_15 = "2002";
    private static final String year_16 = "2001";
    private static final String year_17 = "2000";
    private static final String year_18 = "1999";
    private static final String year_19 = "1998";
    private static final String year_20 = "1997";
    private static final String year_21 = "1996";
    private static final String year_22 = "1995";
    private static final String year_23 = "1994";
    private static final String year_24 = "1993";
    private static final String year_25 = "1992";
    private static final String year_26 = "1991";
    private static final String year_27 = "1990";
    private static final String sort_0 = "update";
    private static final String sort_1 = "pubdate";
    private static final String sort_2 = "rank";
    private static final String sort_3 = "score";
    private static final String sort_4 = "views";
    public   String[] channelList;
    public   String[] areaList;
    public   String[] categoryList;
    public   String[] yearList;
    public   String[] sortList;


    private Category(){
        channelList = new String[]{channel_0,channel_1,channel_2,channel_3,channel_4};
        areaList = new String[]{area_0,area_1,area_2,area_3,area_4,area_5,area_6,area_7,area_8,area_9,area_10,area_11,area_12,area_13,area_14,area_15,area_16,area_17,area_18,area_19,area_20,area_21};
        categoryList = new String[]{category_0,category_1,category_2,category_3,category_4,category_5,category_6,category_7,category_8,category_9,category_10,category_11,category_12,category_13,category_14,category_15,category_16,category_17,category_18,category_19,category_20,category_21,category_22,category_23,category_24,category_25,category_26,category_27,category_28,category_29,category_30,category_31,category_32,category_33,category_34,category_35,category_36,category_37,category_38,category_39};
        yearList = new String[]{year_0,year_1,year_2,year_3,year_4,year_5,year_6,year_7,year_8,year_9,year_10,year_11,year_12,year_13,year_14,year_15,year_16,year_17,year_18,year_19,year_20,year_21,year_22,year_23,year_24,year_25,year_26,year_27};
        sortList = new String[]{sort_0,sort_1,sort_2,sort_3,sort_4};
        if (BuildConfig.DEBUG){
            Log.w("channelList",""+channelList.length);
            Log.w("arealList",""+areaList.length);
            Log.w("categoryList",""+categoryList.length);
            Log.w("yearList",""+yearList.length);
            Log.w("sortList",""+sortList.length);
        }




    }

    public static Category getInstance(){
        if (instance==null){
            synchronized (mLock){
                if (instance==null){
                    instance = new Category();
                }
            }
        }
        return instance;
    }
}
