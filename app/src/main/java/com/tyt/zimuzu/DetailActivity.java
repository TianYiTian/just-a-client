package com.tyt.zimuzu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tyt.data.html.DetailParser;

/**
 * Created by TYT on 2016/5/12.
 */
public class DetailActivity extends AppCompatActivity {
    private DetailParser mDetailParser;



    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case DetailParser.PARSE_DONE:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String URL = getIntent().getStringExtra("URL");
        mDetailParser = new DetailParser(mHandler);
        mDetailParser.parse(URL);

    }
}
