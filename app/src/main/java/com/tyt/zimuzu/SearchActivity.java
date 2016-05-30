package com.tyt.zimuzu;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.tyt.data.data.SearchResult;
import com.tyt.data.html.SearchParser;
import com.tyt.zimuzu.RecyclerViewAdapter.SearchRecyclerAdapter;

import java.util.ArrayList;

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
    private SearchRecyclerAdapter mSearchRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mHandler = new Handler(getMainLooper());
        mToolbar.setTitle("搜索");
        setSupportActionBar(mToolbar);
        if (getIntent().getStringExtra("URL") != null) {
            mRunnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        setData(SearchParser.parse(getIntent().getStringExtra("URL")+"&type=resource"));
                    } catch (Exception e) {
                        Log.w("search", e.getMessage());
                    }
                }
            };
            ((MyApplication) getApplication()).getHandler().post(mRunnable);
        }
        mSearchRecyclerAdapter = new SearchRecyclerAdapter();
        searchRecycler.setAdapter(mSearchRecyclerAdapter);
        searchRecycler.setLayoutManager(new LinearLayoutManager(this));
        searchRecycler.setItemAnimator(new DefaultItemAnimator());
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
                try {
//                    http://www.zimuzu.tv/search?keyword=sad&type=resource&security_verify_data=313932302c31303830
                    setData(SearchParser.parse("http://www.zimuzu.tv/search?keyword="+search_text.getText().toString()+"&type=resource"));
                } catch (Exception e) {
                    Log.w("search", e.getMessage());
                }
            }
        };
        ((MyApplication) getApplication()).getHandler().post(mRunnable);
    }

    public void setData(final ArrayList<SearchResult> results){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mSearchRecyclerAdapter.setData(results);
            }
        });
    }
}
