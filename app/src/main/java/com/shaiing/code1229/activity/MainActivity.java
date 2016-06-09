package com.shaiing.code1229.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shaiing.code1229.R;
import com.shaiing.code1229.fragment.HaoYouQuanFragment;
import com.shaiing.code1229.fragment.HomePageFragment;
import com.shaiing.code1229.fragment.NearbyFragment;
import com.shaiing.code1229.fragment.WoFragment;
import com.shaiing.code1229.fragment.hashtagFragment;

public class MainActivity extends Activity implements View.OnClickListener {
    private RelativeLayout rl_homepage;
    private RelativeLayout rl_find;
    private RelativeLayout rl_hashtag;
    private RelativeLayout rl_camrea_retro;
    private RelativeLayout rl_haoyouquan;
    private RelativeLayout rl_nearby;
    private RelativeLayout rl_me;

    private TextView tv_ic_homepage;
    private TextView tv_ic_find;
    private TextView tv_ic_hashtag;
    private TextView tv_ic_haoyouquan;
    private TextView tv_ic_nearby;
    private TextView tv_ic_me;

    private ImageButton ib_picture;

    private Fragment homePageFragment;
    private Fragment hashtagFragment;
    private Fragment haoYouQuanFragment;
    private Fragment nearbyFragment;
    private Fragment woFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initEvent();
        setSelect(0);
    }

    private void initView() {
        rl_homepage = (RelativeLayout) findViewById(R.id.rl_homepage);
        rl_find = (RelativeLayout) findViewById(R.id.rl_find);
        rl_hashtag = (RelativeLayout) findViewById(R.id.rl_hashtag);
        rl_camrea_retro = (RelativeLayout) findViewById(R.id.rl_camera_retro);
        rl_haoyouquan = (RelativeLayout) findViewById(R.id.rl_haoyouquan);
        rl_nearby = (RelativeLayout) findViewById(R.id.rl_nearby);
        rl_me = (RelativeLayout) findViewById(R.id.rl_me);

        tv_ic_homepage = (TextView) findViewById(R.id.tv_ic_homepage);
        tv_ic_find = (TextView) findViewById(R.id.tv_ic_find);
        tv_ic_hashtag = (TextView) findViewById(R.id.tv_ic_hashtag);
        tv_ic_haoyouquan = (TextView) findViewById(R.id.tv_ic_haoyouquan);
        tv_ic_nearby = (TextView) findViewById(R.id.tv_ic_nearby);
        tv_ic_me = (TextView) findViewById(R.id.tv_ic_me);

        ib_picture = (ImageButton) findViewById(R.id.ib_picture);
    }

    private void initData() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        tv_ic_homepage.setTypeface(font);
        tv_ic_find.setTypeface(font);
        tv_ic_hashtag.setTypeface(font);
        tv_ic_haoyouquan.setTypeface(font);
        tv_ic_nearby.setTypeface(font);
        tv_ic_me.setTypeface(font);
    }

    private void initEvent() {
        rl_homepage.setOnClickListener(this);
        rl_find.setOnClickListener(this);
        rl_hashtag.setOnClickListener(this);
        rl_haoyouquan.setOnClickListener(this);
        rl_nearby.setOnClickListener(this);
        rl_me.setOnClickListener(this);

        rl_camrea_retro.setOnClickListener(this);

        ib_picture.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_homepage: {
                setSelect(0);
                break;
            }

            case R.id.rl_find: {
                setSelect(1);
                break;
            }

            case R.id.rl_hashtag: {
                setSelect(2);
                break;
            }

            case R.id.rl_haoyouquan: {
                setSelect(4);
                break;
            }

            case R.id.rl_nearby: {
                setSelect(5);
                break;
            }

            case R.id.rl_me: {
                setSelect(6);
                break;
            }

            case R.id.ib_picture: {
                Intent intent = new Intent(MainActivity.this, PictureActivity.class);
                startActivity(intent);
            }
        }
    }

    private void setSelect(int i) {
        resetNavBar();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0: {
                if (homePageFragment == null) {
                    homePageFragment = new HomePageFragment();
                    transaction.add(R.id.fl_container, homePageFragment);
                } else {
                    transaction.show(homePageFragment);
                }

//                tv_ic_homepage.setTextColor(getResources().getColor(R.color.fanqiehong));
                rl_homepage.setBackgroundColor(getResources().getColor(R.color.fanqiehong));
                break;
            }

            case 1: {
                if (homePageFragment == null) {
                    homePageFragment = new HomePageFragment();
                    transaction.add(R.id.fl_container, homePageFragment);
                } else {
                    transaction.show(homePageFragment);
                }

//                tv_ic_find.setTextColor(getResources().getColor(R.color.fanqiehong));
                rl_find.setBackgroundColor(getResources().getColor(R.color.fanqiehong));
                break;
            }

            case 2: {
                if (hashtagFragment == null) {
                    hashtagFragment = new hashtagFragment();
                    transaction.add(R.id.fl_container, hashtagFragment);
                } else {
                    transaction.show(hashtagFragment);
                }

//                tv_ic_hashtag.setTextColor(getResources().getColor(R.color.fanqiehong));
                rl_hashtag.setBackgroundColor(getResources().getColor(R.color.fanqiehong));
                break;
            }

            case 4: {
                if (haoYouQuanFragment == null) {
                    haoYouQuanFragment = new HaoYouQuanFragment();
                    transaction.add(R.id.fl_container, haoYouQuanFragment);
                } else {
                    transaction.show(haoYouQuanFragment);
                }

//                tv_ic_haoyouquan.setTextColor(getResources().getColor(R.color.fanqiehong));
                rl_haoyouquan.setBackgroundColor(getResources().getColor(R.color.fanqiehong));
                break;
            }

            case 5: {
                if (nearbyFragment == null) {
                    nearbyFragment = new NearbyFragment();
                    transaction.add(R.id.fl_container, nearbyFragment);
                } else {
                    transaction.show(nearbyFragment);
                }

//                tv_ic_nearby.setTextColor(getResources().getColor(R.color.fanqiehong));
                rl_nearby.setBackgroundColor(getResources().getColor(R.color.fanqiehong));
                break;
            }

            case 6: {
                if (woFragment == null) {
                    woFragment = new WoFragment();
                    transaction.add(R.id.fl_container, woFragment);
                } else {
                    transaction.show(woFragment);
                }

//                tv_ic_me.setTextColor(getResources().getColor(R.color.fanqiehong));
                rl_me.setBackgroundColor(getResources().getColor(R.color.fanqiehong));
                break;
            }
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (homePageFragment != null) {
            transaction.hide(homePageFragment);
        }

        if (hashtagFragment != null) {
            transaction.hide(hashtagFragment);
        }

        if (haoYouQuanFragment != null) {
            transaction.hide(haoYouQuanFragment);
        }

        if (nearbyFragment != null) {
            transaction.hide(nearbyFragment);
        }

        if (woFragment != null) {
            transaction.hide(woFragment);
        }
    }

    private void resetNavBar() {
//        tv_ic_homepage.setTextColor(getResources().getColor(R.color.xuese));
//        tv_ic_find.setTextColor(getResources().getColor(R.color.xuese));
//        tv_ic_hashtag.setTextColor(getResources().getColor(R.color.xuese));
//        tv_ic_haoyouquan.setTextColor(getResources().getColor(R.color.xuese));
//        tv_ic_nearby.setTextColor(getResources().getColor(R.color.xuese));
//        tv_ic_me.setTextColor(getResources().getColor(R.color.xuese));
        rl_homepage.setBackgroundColor(getResources().getColor(R.color.tianqing));
        rl_find.setBackgroundColor(getResources().getColor(R.color.tianqing));
        rl_hashtag.setBackgroundColor(getResources().getColor(R.color.tianqing));
        rl_haoyouquan.setBackgroundColor(getResources().getColor(R.color.tianqing));
        rl_nearby.setBackgroundColor(getResources().getColor(R.color.tianqing));
        rl_me.setBackgroundColor(getResources().getColor(R.color.tianqing));
    }
}