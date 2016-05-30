package com.tyt.zimuzu;

import android.content.Context;
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
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

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
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription("search");
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
        mSearchRecyclerAdapter = new SearchRecyclerAdapter(getApplicationContext());
        searchRecycler.setAdapter(mSearchRecyclerAdapter);
        searchRecycler.setLayoutManager(new LinearLayoutManager(this));
        searchRecycler.setItemAnimator(new DefaultItemAnimator());
        search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId){
                    case EditorInfo.IME_ACTION_DONE:
                        search.performClick();
                        return true;
                }
                return false;
            }
        });
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
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                        }
                    });
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
