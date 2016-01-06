package com.shaiing.code1229.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shaiing.code1229.R;
import com.shaiing.code1229.fragment.HaoYouQuanFragment;
import com.shaiing.code1229.fragment.NearbyFragment;
import com.shaiing.code1229.fragment.ShaiShaiFragment;
import com.shaiing.code1229.fragment.ShouYeFragment;
import com.shaiing.code1229.fragment.WoFragment;

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

    private byte current = 0;
    private byte prev = 0;

    private Fragment[] fragments;

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

        fragments = new Fragment[5];
        FragmentManager fragmentManager = getFragmentManager();
        fragments[0] = fragmentManager.findFragmentById(R.id.shou_ye_fragment);

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
            menu_tvs.get(current).setTextColor(getResources().getColor(R.color.ningmenghuang));

            menu_icons.get(prev).setTextColor(getResources().getColor(R.color.xuese));
            menu_tvs.get(prev).setTextColor(getResources().getColor(R.color.xuese));

            //更新fragment
            switch (current) {
                case 0: {
                    switchFragment(prev,current);
                    break;
                }
                case 1: {
                    switchFragment(prev,current);
                    break;
                }
                case 2: {
                    switchFragment(prev,current);
                    break;
                }
                case 3: {
                    switchFragment(prev,current);
                    break;
                }
                case 4: {
                    switchFragment(prev,current);
                    break;
                }
            }
            prev = current;
        }
    }

    private void switchFragment(byte prev, byte current) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(fragments[prev]);
        if (fragments[current] == null) {
            switch (current) {
                case 1: {
                    fragments[current] = new ShaiShaiFragment();
                    break;
                }
                case 2: {
                    fragments[current] = new HaoYouQuanFragment();
                    break;
                }
                case 3: {
                    fragments[current] = new NearbyFragment();
                    break;
                }
                case 4: {
                    fragments[current] = new WoFragment();
                    break;
                }
            }
            fragmentTransaction.add(R.id.fragment_container, fragments[current]);
        } else {
            fragmentTransaction.show(fragments[current]);
        }
        fragmentTransaction.commit();
    }

}
