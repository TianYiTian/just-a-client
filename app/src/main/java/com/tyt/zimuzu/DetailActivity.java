package com.tyt.zimuzu;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyt.data.html.Detail;
import com.tyt.data.html.DetailParser;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by TYT on 2016/5/12.
 */
public class DetailActivity extends AppCompatActivity {
    @Bind(R.id.collapsing_toolbar_layout)CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.toolbar)Toolbar mToolbar;
    @Bind(R.id.detail_pic)SimpleDraweeView mSimpleDraweeView;


    private DetailParser mDetailParser;



    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case DetailParser.PARSE_DONE:
                    Detail detail = (Detail)msg.obj;
                    mSimpleDraweeView.setImageURI(Uri.parse(((Detail)msg.obj).getImgURL()));
                    mCollapsingToolbarLayout.setTitle(detail.getName());
                    break;
            }
        }
    };

    @OnClick({R.id.download})
    public void downLoad(View view){
        //TODO
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mCollapsingToolbarLayout.setTitle("读取中");

        String URL = getIntent().getStringExtra("URL");
        mDetailParser = new DetailParser(mHandler);
        mDetailParser.parse(URL);

    }
}
