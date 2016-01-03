package com.shaiing.code1229;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView tv_activity_main_icon_haoyouquan;
    private TextView tv_activity_main_icon_me;
    private TextView tv_activity_main_icon_nearby;
    private TextView tv_activity_main_icon_shaishai;
    private TextView tv_activity_main_icon_share;
    private TextView tv_activity_main_icon_shouye;
    private List<TextView> icons;

    private TextView tv_activity_main_haoyouquan;
    private TextView tv_activity_main_me;
    private TextView tv_activity_main_nearby;
    private TextView tv_activity_main_shaishai;
    private TextView tv_activity_main_share;
    private TextView tv_activity_main_shouye;
    private List<TextView> tvs;

    private LinearLayout ll_activity_main_shouye;
    private LinearLayout ll_activity_main_shaishai;
    private LinearLayout ll_activity_main_haoyouquan;
    private LinearLayout ll_activity_main_nearby;
    private LinearLayout ll_activity_main_me;

    private Typeface font;

    private int ningmenghuang;
    private int baicaoshuang;
    private int huaqing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initViews();
        initEvents();

    }

    private void initViews() {
        tv_activity_main_icon_share = (TextView) findViewById(R.id.tv_activity_main_icon_share);
        tv_activity_main_icon_share.setTypeface(font);

        tv_activity_main_icon_shouye = (TextView) findViewById(R.id.tv_activity_main_icon_shouye);
        tv_activity_main_icon_shouye.setTypeface(font);
        icons.add(tv_activity_main_icon_shouye);
        tv_activity_main_icon_shaishai = (TextView) findViewById(R.id.tv_activity_main_icon_shaishai);
        tv_activity_main_icon_shaishai.setTypeface(font);
        icons.add(tv_activity_main_icon_shaishai);
        tv_activity_main_icon_haoyouquan = (TextView) findViewById(R.id.tv_activity_main_icon_haoyouquan);
        tv_activity_main_icon_haoyouquan.setTypeface(font);
        icons.add(tv_activity_main_icon_haoyouquan);
        tv_activity_main_icon_nearby = (TextView) findViewById(R.id.tv_activity_main_icon_nearby);
        tv_activity_main_icon_nearby.setTypeface(font);
        icons.add(tv_activity_main_icon_nearby);
        tv_activity_main_icon_me = (TextView) findViewById(R.id.tv_activity_main_icon_me);
        tv_activity_main_icon_me.setTypeface(font);
        icons.add(tv_activity_main_icon_me);

        tv_activity_main_shouye = (TextView) findViewById(R.id.tv_activity_main_shouye);
        tvs.add(tv_activity_main_shouye);
        tv_activity_main_shaishai = (TextView) findViewById(R.id.tv_activity_main_shaishai);
        tvs.add(tv_activity_main_shaishai);
        tv_activity_main_haoyouquan = (TextView) findViewById(R.id.tv_activity_main_haoyouquan);
        tvs.add(tv_activity_main_haoyouquan);
        tv_activity_main_nearby = (TextView) findViewById(R.id.tv_activity_main_nearby);
        tvs.add(tv_activity_main_nearby);
        tv_activity_main_me = (TextView) findViewById(R.id.tv_activity_main_me);
        tvs.add(tv_activity_main_me);

        ll_activity_main_shouye = (LinearLayout) findViewById(R.id.ll_activity_main_shouye);
        ll_activity_main_shaishai = (LinearLayout) findViewById(R.id.ll_activity_main_shaishai);
        ll_activity_main_haoyouquan = (LinearLayout) findViewById(R.id.ll_activity_main_haoyouquan);
        ll_activity_main_nearby = (LinearLayout) findViewById(R.id.ll_activity_main_nearby);
        ll_activity_main_me = (LinearLayout) findViewById(R.id.ll_activity_main_me);
    }

    private void initData() {
        font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        icons = new ArrayList<>();
        tvs = new ArrayList<>();

        ningmenghuang = getResources().getColor(R.color.ningmenghuang);
        baicaoshuang = getResources().getColor(R.color.baicaoshuang);
        huaqing = getResources().getColor(R.color.huaqing);
    }

    private void initEvents() {
        ll_activity_main_shouye.setOnClickListener(this);
        ll_activity_main_shaishai.setOnClickListener(this);
        ll_activity_main_haoyouquan.setOnClickListener(this);
        ll_activity_main_nearby.setOnClickListener(this);
        ll_activity_main_me.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_activity_main_shouye: {
                unselectTheseMenuItems();
                icons.get(0).setTextColor(ningmenghuang);
                tvs.get(0).setTextColor(baicaoshuang);
                break;
            }

            case R.id.ll_activity_main_shaishai: {
                unselectTheseMenuItems();
                icons.get(1).setTextColor(ningmenghuang);
                tvs.get(1).setTextColor(baicaoshuang);
                break;
            }

            case R.id.ll_activity_main_haoyouquan: {
                unselectTheseMenuItems();
                icons.get(2).setTextColor(ningmenghuang);
                tvs.get(2).setTextColor(baicaoshuang);
                break;
            }

            case R.id.ll_activity_main_nearby: {
                unselectTheseMenuItems();
                icons.get(3).setTextColor(ningmenghuang);
                tvs.get(3).setTextColor(baicaoshuang);
                break;
            }

            case R.id.ll_activity_main_me: {
                unselectTheseMenuItems();
                icons.get(4).setTextColor(ningmenghuang);
                tvs.get(4).setTextColor(baicaoshuang);
                break;
            }
        }
    }

    private void unselectTheseMenuItems() {
        for (TextView tv : icons) {
            tv.setTextColor(huaqing);
        }
        for (TextView tv : tvs) {
            tv.setTextColor(huaqing);
        }
    }


}
