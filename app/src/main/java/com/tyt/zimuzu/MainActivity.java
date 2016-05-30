package com.tyt.zimuzu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyt.data.constant.Constance;
import com.tyt.data.data.Category;
import com.tyt.data.data.Info;
import com.tyt.data.html.InfoParser;
import com.tyt.data.data.User;
import com.tyt.data.http.OkHttpUtil;
import com.tyt.data.json.UserLoader;
import com.tyt.zimuzu.RecyclerViewAdapter.InfoRecyclerAdapter;

import java.util.ArrayList;
import java.util.concurrent.RunnableFuture;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.main_recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.prepage)
    Button prePage;
    @Bind(R.id.nextpage)
    Button nextPage;
    @Bind(R.id.page_number)
    EditText pageNumber;
    @Bind(R.id.page_control)
    RelativeLayout pageControl;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.drawer)
    DrawerLayout drawer;
    @Bind(R.id.navigation)
    NavigationView navigation;


    private TextView name;
    private SimpleDraweeView head;
    private InfoRecyclerAdapter mInfoRecyclerAdapter;
    private int channel = 0;
    private int area = 0;
    private int category = 0;
    private int year = 0;
    private int sort = 0;
    private int page = 1;
    private InfoParser mInfoParser;
    private PopupWindow mPopupWindow;
    private NumberPicker channelPicker;
    private NumberPicker areaPicker;
    private NumberPicker categoryPicker;
    private NumberPicker yearPicker;
    private NumberPicker sortPicker;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private User user;
    private boolean login = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            /*switch (msg.what){
                case InfoParser.PARSE_CURRENT:
                   *//* textview1.setText(((ArrayList<Info>)msg.obj).get(0).getName());
                    Uri uri = Uri.parse(((ArrayList<Info>)msg.obj).get(0).getImgURL());
                    img.setImageURI(uri);*//*
                    if (page==1) {
                        prePage.setEnabled(false);
                    }else{
                        prePage.setEnabled(true);
                    }
                    pageNumber.setText("第"+page+"页");
                    mInfoRecyclerAdapter.setDataList((ArrayList<Info>)msg.obj);
                    break;
                case InfoParser.PARSE_NEXTPAGE:
                    break;
                case InfoParser.PARSE_PREVIOUS:
                    break;
                default:
                    break;
            }*/
            page = Integer.parseInt(pageNumber.getText().toString()) + msg.what;
            if (page == 1) {
                prePage.setEnabled(false);
            } else {
                prePage.setEnabled(true);
            }
            if (((ArrayList<Info>) msg.obj).size() < 20) {
                nextPage.setEnabled(false);
            } else {
                nextPage.setEnabled(true);
            }
            pageNumber.setText("" + page);
            mInfoRecyclerAdapter.setDataList((ArrayList<Info>) msg.obj);
        }
    };


    //TODO 搜索，新闻，时间表，下载页
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getHandler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpUtil.getYunsuo();
                }catch (Exception e){
                    Log.w("yunsuo",e.getMessage());
                }
            }
        });

        mToolbar.setTitle("字幕组");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        drawer.setDrawerListener(mActionBarDrawerToggle);

        head = ButterKnife.findById(navigation.getHeaderView(0), R.id.head);
        name = ButterKnife.findById(navigation.getHeaderView(0), R.id.name);
        mInfoRecyclerAdapter = new InfoRecyclerAdapter(getApplicationContext());
        mRecyclerView.setAdapter(mInfoRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mInfoParser = new InfoParser(mHandler);
        ((MyApplication) getApplication()).getHandler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    mInfoParser.parse(channel, area, category, year, sort, page, InfoParser.PARSE_CURRENT);
                }catch (Exception e){
                    Log.w("infoparser",e.getMessage());
                }
            }
        });

        pageNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(final TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    ((MyApplication) getApplication()).getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mInfoParser.parse(channel, area, category, year, sort, Integer.parseInt(v.getText().toString()), InfoParser.PARSE_CURRENT);
                            }catch (Exception e){
                                Log.w("infoparser",e.getMessage());
                            }
                        }
                    });

                    InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        if (((MyApplication) getApplication()).getSettingHelper().getAutoLogin()) {
            Toast toast = Toast.makeText(this, "自动登录中", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            OkHttpUtil.addCookies(((MyApplication) getApplication()).getCookieHelper().readCookies());
            loadUser();
        }
        setLoginAction(login);
    }

    private void setLoginAction(boolean login) {
        mInfoRecyclerAdapter.setLogin(login);
        if (login) {
            head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                    startActivityForResult(intent, Constance.USER_CODE);
                }
            });
        } else {
            head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivityForResult(intent, Constance.LOGIN_CODE);
                }
            });
        }
    }

    @OnClick({R.id.prepage, R.id.nextpage, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.prepage:
                ((MyApplication) getApplication()).getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mInfoParser.parse(channel, area, category, year, sort, page, InfoParser.PARSE_PREVIOUS);
                        }catch (Exception e){
                            Log.w("infoparser",e.getMessage());
                        }
                    }
                });

                break;
            case R.id.nextpage:
                ((MyApplication) getApplication()).getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mInfoParser.parse(channel, area, category, year, sort, page, InfoParser.PARSE_NEXTPAGE);
                        }catch (Exception e){
                            Log.w("infoparser",e.getMessage());
                        }
                    }
                });
                break;
            case R.id.fab:
                if (mPopupWindow == null) {
                    mPopupWindow = new PopupWindow(((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.setting_popup, null), getWindowManager().getDefaultDisplay().getWidth(), (int) this.getWindowManager().getDefaultDisplay().getHeight() / 3, false);
                    mPopupWindow.setOutsideTouchable(false);
                    channelPicker = ButterKnife.findById(mPopupWindow.getContentView(), R.id.channelPicker);
                    areaPicker = ButterKnife.findById(mPopupWindow.getContentView(), R.id.areaPicker);
                    categoryPicker = ButterKnife.findById(mPopupWindow.getContentView(), R.id.categoryPicker);
                    yearPicker = ButterKnife.findById(mPopupWindow.getContentView(), R.id.yearPicker);
                    sortPicker = ButterKnife.findById(mPopupWindow.getContentView(), R.id.sortPicker);


                    channelPicker.setDisplayedValues(new String[]{"全部", "电影", "电视剧", "纪录片", "公开课"});
                    channelPicker.setMinValue(0);
                    channelPicker.setMaxValue(4);
                    String[] areaList = Category.getInstance().areaList.clone();
                    areaList[0] = "全部";
                    areaPicker.setDisplayedValues(areaList);
                    areaPicker.setMinValue(0);
                    areaPicker.setMaxValue(areaList.length - 1);
                    String[] categoryList = Category.getInstance().categoryList.clone();
                    categoryList[0] = "全部";
                    categoryPicker.setDisplayedValues(categoryList);
                    categoryPicker.setMinValue(0);
                    categoryPicker.setMaxValue(categoryList.length - 1);
                    String[] yearList = Category.getInstance().yearList.clone();
                    yearList[0] = "全部";
                    yearPicker.setDisplayedValues(yearList);
                    yearPicker.setMinValue(0);
                    yearPicker.setMaxValue(yearList.length - 1);
                    sortPicker.setDisplayedValues(new String[]{"按更新日期", "按发布日期", "按排名", "按评分", "按点击率"});
                    sortPicker.setMinValue(0);
                    sortPicker.setMaxValue(4);

                }
                if (mPopupWindow.isShowing()) {
                    fab.setImageResource(android.R.drawable.ic_menu_info_details);
                    mPopupWindow.dismiss();
                    channel = channelPicker.getValue();
                    area = areaPicker.getValue();
                    category = categoryPicker.getValue();
                    year = yearPicker.getValue();
                    sort = sortPicker.getValue();
                    ((MyApplication) getApplication()).getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mInfoParser.parse(channel, area, category, year, sort, 1, InfoParser.PARSE_CURRENT);
                            }catch (Exception e){
                                Log.w("infoparser",e.getMessage());
                            }
                        }
                    });

                    pageNumber.setText("1");
                } else {
                    fab.setImageResource(android.R.drawable.ic_menu_save);
                    mPopupWindow.showAsDropDown(pageControl);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constance.LOGIN_CODE:
                switch (resultCode) {
                    case Constance.LOGIN_SUCCESSFUL:
                        loadUser();
                        break;
                    case Constance.LOGIN_FAILED:
                        break;
                }
                break;
            case Constance.USER_CODE:
                switch (resultCode) {
                    case Constance.USER_NOTHING:
                        break;
                    case Constance.USER_QUIT:
                        ((MyApplication) getApplication()).getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    boolean success = OkHttpUtil.logout();
                                    if (success) {
                                        setLoginAction(false);
                                        setUserUI(null);
                                    }
                                } catch (Exception e) {
                                    Log.w("logout", e.getMessage());
                                }
                            }
                        });
                        break;
                }
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    private void loadUser() {
        ((MyApplication) getApplication()).getHandler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean success = UserLoader.load();
                    //TODO
                    if (success) {
                        setLoginAction(login = true);
                        setUserUI(UserLoader.getLoginStatus().getUserinfo().getUser());
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast toast = Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                            }
                        });
                    } else {
                        setLoginAction(login=false);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast toast = Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                            }
                        });
                    }
                } catch (Exception e) {
                    Log.w("autologin", e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
//                Log.w("search","test");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUserUI(@Nullable final User user) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (user==null){
                    head.setImageURI(null);
                    name.setText("点击头像登录");
                }else {
                    head.setImageURI(Uri.parse(user.getImgURL()));
                    name.setText(user.getNickname());
                }
            }
        });
    }
}
