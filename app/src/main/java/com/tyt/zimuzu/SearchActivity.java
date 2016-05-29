package com.tyt.zimuzu;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.tyt.data.html.SearchParser;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2016/5/29.
 */
public class SearchActivity extends AppCompatActivity {
    @Bind(R.id.search_recycler)
    RecyclerView searchRecycler;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.search)
    AppCompatImageButton search;
    @Bind(R.id.search_text)
    AppCompatEditText search_text;


    private Handler mHandler;
    private Runnable mRunnable = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mHandler = new Handler(getMainLooper());
        mToolbar.setTitle("搜索");
        setSupportActionBar(mToolbar);
        if (getIntent().getStringExtra("url") != null) {
            mRunnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        SearchParser.parse(getIntent().getStringExtra("url"));
                    } catch (Exception e) {
                        Log.w("search", e.getMessage());
                    }
                }
            };
            ((MyApplication) getApplication()).getHandler().post(mRunnable);
        }
    }

    @OnClick({R.id.search})
    public void search(View view){
        if (mRunnable!=null){
            ((MyApplication)getApplication()).getHandler().removeCallbacks(mRunnable);
        }
        mRunnable = new Runnable() {
            @Override
            public void run() {
                //TODO
            }
        };
    }
}
