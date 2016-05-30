package com.tyt.zimuzu;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.tyt.data.data.SeasonDownload;
import com.tyt.data.html.DownloadParser;
import com.tyt.data.http.OkHttpUtil;
import com.tyt.zimuzu.RecyclerViewAdapter.DownloadRecyclerAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

/**
 * Created by admin on 2016/5/28.
 */
public class DownLoadActivity extends AppCompatActivity{
    @Bind(R.id.download_recycler)RecyclerView list;
    @Bind(R.id.toolbar)Toolbar mToolbar;
    @Bind(R.id.season)LinearLayout seasons;
    private String url=null;
    private DownloadRecyclerAdapter mDownloadRecyclerAdapter;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);

        mToolbar.setTitle("下载页");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription("download");

        mDownloadRecyclerAdapter = new DownloadRecyclerAdapter(getApplicationContext());
        list.setAdapter(mDownloadRecyclerAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setItemAnimator(new DefaultItemAnimator());
        mHandler =new Handler(getMainLooper());



        url=getIntent().getStringExtra("url");
        ((MyApplication) getApplication()).getHandler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    final ArrayList<SeasonDownload> downloads=DownloadParser.parse(url);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mDownloadRecyclerAdapter.setData(downloads);
                            seasons.removeAllViews();
                            for (int i =0;i<downloads.size();i++){
                                AppCompatButton appCompatButton = new AppCompatButton(DownLoadActivity.this);
                                appCompatButton.setText(downloads.get(i).getName());
                                final int season = i;
                                appCompatButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mDownloadRecyclerAdapter.setSeason(season);
                                    }
                                });
                                seasons.addView(appCompatButton);
                            }
                        }
                    });
                } catch (Exception e) {
                    Log.w("download", e.toString());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
