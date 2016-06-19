package com.shaiing.code1229.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
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
    private RelativeLayout rl_home;
    private RelativeLayout rl_search;
    private RelativeLayout rl_hashtag;
    private RelativeLayout rl_camrea_retro;
    private RelativeLayout rl_circle_o;
    private RelativeLayout rl_map_marker;
    private RelativeLayout rl_user;

    private TextView tv_home;
    private TextView tv_search;
    private TextView tv_hashtag;
    private TextView tv_circle_o;
    private TextView tv_map_marker;
    private TextView tv_user;
    private TextView tv_camera_retro;

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
        rl_home = (RelativeLayout) findViewById(R.id.rl_home);
        rl_search = (RelativeLayout) findViewById(R.id.rl_search);
        rl_hashtag = (RelativeLayout) findViewById(R.id.rl_hashtag);
        rl_camrea_retro = (RelativeLayout) findViewById(R.id.rl_camera_retro);
        rl_circle_o = (RelativeLayout) findViewById(R.id.rl_circle_o);
        rl_map_marker = (RelativeLayout) findViewById(R.id.rl_map_marker);
        rl_user = (RelativeLayout) findViewById(R.id.rl_user);

        tv_home = (TextView) findViewById(R.id.tv_home);
        tv_search = (TextView) findViewById(R.id.tv_search);
        tv_hashtag = (TextView) findViewById(R.id.tv_hashtag);
        tv_circle_o = (TextView) findViewById(R.id.tv_circle_o);
        tv_map_marker = (TextView) findViewById(R.id.tv_map_marker);
        tv_user = (TextView) findViewById(R.id.tv_user);
        tv_camera_retro = (TextView) findViewById(R.id.tv_camera_retro);
    }

    private void initData() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        tv_home.setTypeface(font);
        tv_search.setTypeface(font);
        tv_hashtag.setTypeface(font);
        tv_circle_o.setTypeface(font);
        tv_map_marker.setTypeface(font);
        tv_user.setTypeface(font);
        tv_camera_retro.setTypeface(font);
    }

    private void initEvent() {
        rl_home.setOnClickListener(this);
        rl_search.setOnClickListener(this);
        rl_hashtag.setOnClickListener(this);
        rl_circle_o.setOnClickListener(this);
        rl_map_marker.setOnClickListener(this);
        rl_user.setOnClickListener(this);

        rl_camrea_retro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_home: {
                setSelect(0);
                break;
            }

            case R.id.rl_search: {
                setSelect(1);
                break;
            }

            case R.id.rl_hashtag: {
                setSelect(2);
                break;
            }

            case R.id.rl_circle_o: {
                setSelect(4);
                break;
            }

            case R.id.rl_map_marker: {
                setSelect(5);
                break;
            }

            case R.id.rl_user: {
                setSelect(6);
                break;
            }

            case R.id.rl_camera_retro:
                Intent intent = new Intent(this,PictureActivity.class);
                startActivity(intent);
                break;

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

                tv_home.setGravity(Gravity.CENTER_HORIZONTAL);
                break;
            }

            case 1: {
                if (homePageFragment == null) {
                    homePageFragment = new HomePageFragment();
                    transaction.add(R.id.fl_container, homePageFragment);
                } else {
                    transaction.show(homePageFragment);
                }

                tv_search.setGravity(Gravity.CENTER_HORIZONTAL);
                break;
            }

            case 2: {
                if (hashtagFragment == null) {
                    hashtagFragment = new hashtagFragment();
                    transaction.add(R.id.fl_container, hashtagFragment);
                } else {
                    transaction.show(hashtagFragment);
                }

                tv_hashtag.setGravity(Gravity.CENTER_HORIZONTAL);
                break;
            }

            case 4: {
                if (haoYouQuanFragment == null) {
                    haoYouQuanFragment = new HaoYouQuanFragment();
                    transaction.add(R.id.fl_container, haoYouQuanFragment);
                } else {
                    transaction.show(haoYouQuanFragment);
                }

                tv_circle_o.setGravity(Gravity.CENTER_HORIZONTAL);
                break;
            }

            case 5: {
                if (nearbyFragment == null) {
                    nearbyFragment = new NearbyFragment();
                    transaction.add(R.id.fl_container, nearbyFragment);
                } else {
                    transaction.show(nearbyFragment);
                }

                tv_map_marker.setGravity(Gravity.CENTER_HORIZONTAL);
                break;
            }

            case 6: {
                if (woFragment == null) {
                    woFragment = new WoFragment();
                    transaction.add(R.id.fl_container, woFragment);
                } else {
                    transaction.show(woFragment);
                }

                tv_user.setGravity(Gravity.CENTER_HORIZONTAL);
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
        tv_home.setGravity(Gravity.CENTER);
        tv_search.setGravity(Gravity.CENTER);
        tv_hashtag.setGravity(Gravity.CENTER);
        tv_circle_o.setGravity(Gravity.CENTER);
        tv_map_marker.setGravity(Gravity.CENTER);
        tv_user.setGravity(Gravity.CENTER);
//        rl_home.setBackgroundColor(getResources().getColor(R.color.rain));
//        rl_search.setBackgroundColor(getResources().getColor(R.color.rain));
//        rl_hashtag.setBackgroundColor(getResources().getColor(R.color.rain));
//        rl_circle_o.setBackgroundColor(getResources().getColor(R.color.rain));
//        rl_map_marker.setBackgroundColor(getResources().getColor(R.color.rain));
//        rl_user.setBackgroundColor(getResources().getColor(R.color.rain));
    }
}