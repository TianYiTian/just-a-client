package com.tyt.zimuzu;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyt.data.data.Detail;
import com.tyt.data.html.DetailParser;
import com.tyt.data.html.DownloadParser;
import com.tyt.data.http.OkHttpUtil;
import com.tyt.zimuzu.RecyclerViewAdapter.DetailRecyclerAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by TYT on 2016/5/12.
 */
public class DetailActivity extends AppCompatActivity {
    @Bind(R.id.collapsing_toolbar_layout)CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.toolbar)Toolbar mToolbar;
    @Bind(R.id.detail_pic)SimpleDraweeView mSimpleDraweeView;
    @Bind(R.id.detail_recycler)RecyclerView mRecyclerView;
    @Bind(R.id.download)FloatingActionButton download;


    private DetailParser mDetailParser;
    private DetailRecyclerAdapter mDetailRecyclerAdapter;



    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case DetailParser.PARSE_DONE:
                    final Detail detail = (Detail)msg.obj;
                    mSimpleDraweeView.setImageURI(Uri.parse(((Detail)msg.obj).getImgURL()));
                    mCollapsingToolbarLayout.setTitle(detail.getName());
                    mDetailRecyclerAdapter.setData(detail);
                    download.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((MyApplication)getApplication()).getHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        /*Request request = new Request.Builder().url(detail.getDownloadURL()).build();
                                        Response response = OkHttpUtil.getOkHttpClient().newCall(request).execute();
                                        Document document = Jsoup.parse(response.body().string());
                                        Elements elements = document.getAllElements();*/
                                        DownloadParser.parse(detail.getDownloadURL());
                                    }catch (Exception e){
                                        Log.w("download",e.toString());
                                    }
                                }
                            });
                        }
                    });
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mCollapsingToolbarLayout.setTitle("读取中");
        mDetailRecyclerAdapter=new DetailRecyclerAdapter(this);
        mRecyclerView.setAdapter(mDetailRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(false);

        String URL = getIntent().getStringExtra("URL");
        mDetailParser = new DetailParser(mHandler);
        mDetailParser.parse(URL);

    }
}
