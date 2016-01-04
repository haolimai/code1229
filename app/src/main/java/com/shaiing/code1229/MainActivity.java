package com.shaiing.code1229;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
                unselectTheseMenuItems(0);
                menu_icons.get(0).setTextColor(getResources().getColor(R.color.ningmenghuang));
                menu_tvs.get(0).setTextColor(getResources().getColor(R.color.ningmenghuang));
                break;
            }

            case R.id.ll_activity_main_shaishai: {
                unselectTheseMenuItems(1);
                menu_icons.get(1).setTextColor(getResources().getColor(R.color.ningmenghuang));
                menu_tvs.get(1).setTextColor(getResources().getColor(R.color.ningmenghuang));
                break;
            }

            case R.id.ll_activity_main_haoyouquan: {
                unselectTheseMenuItems(2);
                menu_icons.get(2).setTextColor(getResources().getColor(R.color.ningmenghuang));
                menu_tvs.get(2).setTextColor(getResources().getColor(R.color.ningmenghuang));
                break;
            }

            case R.id.ll_activity_main_nearby: {
                unselectTheseMenuItems(3);
                menu_icons.get(3).setTextColor(getResources().getColor(R.color.ningmenghuang));
                menu_tvs.get(3).setTextColor(getResources().getColor(R.color.ningmenghuang));
                break;
            }

            case R.id.ll_activity_main_me: {
                unselectTheseMenuItems(4);
                menu_icons.get(4).setTextColor(getResources().getColor(R.color.ningmenghuang));
                menu_tvs.get(4).setTextColor(getResources().getColor(R.color.ningmenghuang));
                break;
            }

            case R.id.tv_activity_main_icon_share: {
                Toast.makeText(MainActivity.this, "sssss", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    private void unselectTheseMenuItems(int skip) {
        for (int i = 0, len = menu_icons.size(); i < len; i++) {
            if (i == skip) {
                continue;
            }
            menu_icons.get(i).setTextColor(getResources().getColor(R.color.zaohong));
        }

        for (int i = 0, len = menu_tvs.size(); i < len; i++) {
            if (i == skip) {
                continue;
            }
            menu_tvs.get(i).setTextColor(getResources().getColor(R.color.zaohong));
        }
    }


}
