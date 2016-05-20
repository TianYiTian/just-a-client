package com.tyt.zimuzu;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyt.data.constant.Constance;
import com.tyt.data.json.UserLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by TYT on 2016/5/20.
 */

public class UserActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)Toolbar mToolbar;
    @Bind(R.id.head)SimpleDraweeView head;
    @Bind(R.id.uid)AppCompatTextView uid;
    @Bind(R.id.name)AppCompatTextView name;
    @Bind(R.id.group_name)AppCompatTextView group_name;
    @Bind(R.id.follow_unread_num)AppCompatTextView follw_unread_num;
    @Bind(R.id.message_unread_num)AppCompatTextView message_unread_num;
    @Bind(R.id.reply_unread_num)AppCompatTextView reply_unread_num;
    @Bind(R.id.quit)AppCompatButton quit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        mToolbar.setTitle("个人信息");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription("login");
        head.setImageURI(Uri.parse(UserLoader.getLoginStatus().getUserinfo().getUser().getImgURL()));
        //uid.setText(UserLoader.getLoginStatus().getUserinfo().getUser().getUid());
        name.setText(UserLoader.getLoginStatus().getUserinfo().getUser().getNickname());
        group_name.setText(UserLoader.getLoginStatus().getUserinfo().getUser().getGroup_name());
        //follw_unread_num.setText(UserLoader.getLoginStatus().getUserinfo().getFollow_unread_num());
        //message_unread_num.setText(UserLoader.getLoginStatus().getUserinfo().getMessage_unread_num());
        //reply_unread_num.setText(UserLoader.getLoginStatus().getUserinfo().getReply_unread_num());
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Constance.USER_QUIT);
                UserActivity.this.finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                setResult(Constance.USER_NOTHING);
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
