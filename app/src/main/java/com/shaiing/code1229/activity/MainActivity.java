package com.shaiing.code1229.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shaiing.code1229.R;
import com.shaiing.code1229.fragment.CommentFragment;
import com.shaiing.code1229.fragment.HomeFragment;
import com.shaiing.code1229.fragment.SearchFragment;
import com.shaiing.code1229.fragment.UserFragment;
import com.shaiing.code1229.util.CommonUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout rl_home;
    private RelativeLayout rl_search;
    private RelativeLayout rl_camrea_retro;
    private RelativeLayout rl_comment;
    private RelativeLayout rl_user;

    private TextView tv_home;
    private TextView tv_search;
    private TextView tv_comment;
    private TextView tv_user;
    private TextView tv_camera_retro;

    private Fragment homeFragment;
    private Fragment searchFragment;
    private Fragment commentFragment;
    private Fragment userFragment;

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
        rl_camrea_retro = (RelativeLayout) findViewById(R.id.rl_camera_retro);
        rl_comment = (RelativeLayout) findViewById(R.id.rl_comment);
        rl_user = (RelativeLayout) findViewById(R.id.rl_user);

        tv_home = (TextView) findViewById(R.id.tv_home);
        tv_search = (TextView) findViewById(R.id.tv_search);
        tv_comment = (TextView) findViewById(R.id.tv_comment);
        tv_user = (TextView) findViewById(R.id.tv_user);
        tv_camera_retro = (TextView) findViewById(R.id.tv_camera_retro);
    }

    private void initData() {
        CommonUtil.setFontAwesome(this, tv_home, tv_search, tv_comment, tv_user, tv_camera_retro);
//        tv_home.setTypeface(font);
//        tv_search.setTypeface(font);
//        tv_comment.setTypeface(font);
//        tv_user.setTypeface(font);
//        tv_camera_retro.setTypeface(font);
    }

    private void initEvent() {
        rl_home.setOnClickListener(this);
        rl_search.setOnClickListener(this);
        rl_comment.setOnClickListener(this);
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

            case R.id.rl_comment: {
                setSelect(2);
                break;
            }

            case R.id.rl_user: {
                setSelect(3);
                break;
            }

            case R.id.rl_camera_retro:
                Intent intent = new Intent(this, PictureActivity.class);
                startActivity(intent);
                break;

        }
    }

    private void setSelect(int i) {
        resetNavBar();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0: {
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fl_container, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }

                rl_home.setBackgroundColor(getResources().getColor(R.color.ningmenghuang));
                tv_home.setTextColor(getResources().getColor(android.R.color.white));
                break;
            }

            case 1: {
                if (searchFragment == null) {
                    searchFragment = new SearchFragment();
                    transaction.add(R.id.fl_container, searchFragment);
                } else {
                    transaction.show(searchFragment);
                }

                rl_search.setBackgroundColor(getResources().getColor(R.color.ningmenghuang));
                tv_search.setTextColor(getResources().getColor(android.R.color.white));
                break;
            }

            case 2: {
                if (commentFragment == null) {
                    commentFragment = new CommentFragment();
                    transaction.add(R.id.fl_container, commentFragment);
                } else {
                    transaction.show(commentFragment);
                }

                rl_comment.setBackgroundColor(getResources().getColor(R.color.ningmenghuang));
                tv_comment.setTextColor(getResources().getColor(android.R.color.white));
                break;
            }

            case 3: {
                if (userFragment == null) {
                    userFragment = new UserFragment();
                    transaction.add(R.id.fl_container, userFragment);
                } else {
                    transaction.show(userFragment);
                }

                rl_user.setBackgroundColor(getResources().getColor(R.color.ningmenghuang));
                tv_user.setTextColor(getResources().getColor(android.R.color.white));
                break;
            }
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }

        if (searchFragment != null) {
            transaction.hide(searchFragment);
        }

        if (commentFragment != null) {
            transaction.hide(commentFragment);
        }

        if (userFragment != null) {
            transaction.hide(userFragment);
        }
    }

    private void resetNavBar() {
        tv_home.setTextColor(getResources().getColor(R.color.ningmenghuang));
        tv_search.setTextColor(getResources().getColor(R.color.ningmenghuang));
        tv_user.setTextColor(getResources().getColor(R.color.ningmenghuang));
        tv_comment.setTextColor(getResources().getColor(R.color.ningmenghuang));

        rl_home.setBackgroundColor(getResources().getColor(android.R.color.white));
        rl_search.setBackgroundColor(getResources().getColor(android.R.color.white));
        rl_user.setBackgroundColor(getResources().getColor(android.R.color.white));
        rl_comment.setBackgroundColor(getResources().getColor(android.R.color.white));

    }
}