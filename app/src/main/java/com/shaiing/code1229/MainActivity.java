package com.shaiing.code1229;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView tv_activity_main_icon_share;

    private List<TextView> menu_icons;
    private List<TextView> menu_tvs;

    private LinearLayout ll_activity_main_shouye;
    private LinearLayout ll_activity_main_shaishai;
    private LinearLayout ll_activity_main_haoyouquan;
    private LinearLayout ll_activity_main_nearby;
    private LinearLayout ll_activity_main_me;

    private Typeface font;

    private int current = 0;
    private int prev = 0;

    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initViews();
        initEvents();
    }

    private void initViews() {
        TextView tv_activity_main_icon_shouye;
        TextView tv_activity_main_icon_shaishai;
        TextView tv_activity_main_icon_haoyouquan;
        TextView tv_activity_main_icon_nearby;
        TextView tv_activity_main_icon_wo;

        TextView tv_activity_main_haoyouquan;
        TextView tv_activity_main_me;
        TextView tv_activity_main_nearby;
        TextView tv_activity_main_shaishai;
        TextView tv_activity_main_shouye;

        tv_activity_main_icon_share = (TextView) findViewById(R.id.tv_activity_main_icon_share);
        tv_activity_main_icon_share.setTypeface(font);
        tv_activity_main_icon_shouye = (TextView) findViewById(R.id.tv_activity_main_icon_shouye);
        tv_activity_main_icon_shouye.setTypeface(font);
        menu_icons.add(tv_activity_main_icon_shouye);
        tv_activity_main_icon_shaishai = (TextView) findViewById(R.id.tv_activity_main_icon_shaishai);
        tv_activity_main_icon_shaishai.setTypeface(font);
        menu_icons.add(tv_activity_main_icon_shaishai);
        tv_activity_main_icon_haoyouquan = (TextView) findViewById(R.id.tv_activity_main_icon_haoyouquan);
        tv_activity_main_icon_haoyouquan.setTypeface(font);
        menu_icons.add(tv_activity_main_icon_haoyouquan);
        tv_activity_main_icon_nearby = (TextView) findViewById(R.id.tv_activity_main_icon_nearby);
        tv_activity_main_icon_nearby.setTypeface(font);
        menu_icons.add(tv_activity_main_icon_nearby);
        tv_activity_main_icon_wo = (TextView) findViewById(R.id.tv_activity_main_icon_wo);
        tv_activity_main_icon_wo.setTypeface(font);
        menu_icons.add(tv_activity_main_icon_wo);

        tv_activity_main_shouye = (TextView) findViewById(R.id.tv_activity_main_shouye);
        menu_tvs.add(tv_activity_main_shouye);
        tv_activity_main_shaishai = (TextView) findViewById(R.id.tv_activity_main_shaishai);
        menu_tvs.add(tv_activity_main_shaishai);
        tv_activity_main_haoyouquan = (TextView) findViewById(R.id.tv_activity_main_haoyouquan);
        menu_tvs.add(tv_activity_main_haoyouquan);
        tv_activity_main_nearby = (TextView) findViewById(R.id.tv_activity_main_nearby);
        menu_tvs.add(tv_activity_main_nearby);
        tv_activity_main_me = (TextView) findViewById(R.id.tv_activity_main_wo);
        menu_tvs.add(tv_activity_main_me);

        ll_activity_main_shouye = (LinearLayout) findViewById(R.id.ll_activity_main_shouye);
        ll_activity_main_shaishai = (LinearLayout) findViewById(R.id.ll_activity_main_shaishai);
        ll_activity_main_haoyouquan = (LinearLayout) findViewById(R.id.ll_activity_main_haoyouquan);
        ll_activity_main_nearby = (LinearLayout) findViewById(R.id.ll_activity_main_nearby);
        ll_activity_main_me = (LinearLayout) findViewById(R.id.ll_activity_main_me);
    }

    private void initData() {
        font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        menu_icons = new ArrayList<>();
        menu_tvs = new ArrayList<>();

        animation = AnimationUtils.loadAnimation(this,R.anim.btn_alpha_0_point_5_to_1);
    }

    private void initEvents() {
        ll_activity_main_shouye.setOnClickListener(this);
        ll_activity_main_shaishai.setOnClickListener(this);
        ll_activity_main_haoyouquan.setOnClickListener(this);
        ll_activity_main_nearby.setOnClickListener(this);
        ll_activity_main_me.setOnClickListener(this);
        tv_activity_main_icon_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_activity_main_shouye: {
                current = 0;
                break;
            }

            case R.id.ll_activity_main_shaishai: {
                current = 1;
                break;
            }

            case R.id.ll_activity_main_haoyouquan: {
                current = 2;
                break;
            }

            case R.id.ll_activity_main_nearby: {
                current = 3;
                break;
            }

            case R.id.ll_activity_main_me: {
                current = 4;
                break;
            }

        }

        if (current != prev) {
            menu_icons.get(current).setTextColor(getResources().getColor(R.color.ningmenghuang));
            menu_icons.get(current).startAnimation(animation);
            menu_tvs.get(current).setTextColor(getResources().getColor(R.color.ningmenghuang));
            menu_tvs.get(current).startAnimation(animation);

            menu_icons.get(prev).setTextColor(getResources().getColor(R.color.xuese));
            menu_tvs.get(prev).setTextColor(getResources().getColor(R.color.xuese));
            prev = current;
        }
    }

}
