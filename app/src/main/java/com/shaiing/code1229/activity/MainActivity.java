package com.shaiing.code1229.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shaiing.code1229.R;
import com.shaiing.code1229.fragment.HaoYouQuanFragment;
import com.shaiing.code1229.fragment.NearbyFragment;
import com.shaiing.code1229.fragment.ShaiShaiFragment;
import com.shaiing.code1229.fragment.ShouYeFragment;
import com.shaiing.code1229.fragment.WoFragment;

import org.w3c.dom.Text;

public class MainActivity extends Activity implements View.OnClickListener {
    private LinearLayout ll_shouye;
    private LinearLayout ll_shaishai;
    private LinearLayout ll_haoyouquan;
    private LinearLayout ll_nearby;
    private LinearLayout ll_wo;

    private TextView tv_ic_shouye;
    private TextView tv_ic_shaishai;
    private TextView tv_ic_haoyouquan;
    private TextView tv_ic_nearby;
    private TextView tv_ic_wo;
    private TextView tv_ic_share;

    private TextView tv_shouye;
    private TextView tv_shaishai;
    private TextView tv_haoyouquan;
    private TextView tv_nearby;
    private TextView tv_wo;

    private Fragment shouYeFragment;
    private Fragment shaiShaiFragment;
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
        ll_shouye = (LinearLayout) findViewById(R.id.ll_shouye);
        ll_shaishai = (LinearLayout) findViewById(R.id.ll_shaishai);
        ll_haoyouquan = (LinearLayout) findViewById(R.id.ll_haoyouquan);
        ll_nearby = (LinearLayout) findViewById(R.id.ll_nearby);
        ll_wo = (LinearLayout) findViewById(R.id.ll_wo);

        tv_ic_shouye = (TextView) findViewById(R.id.tv_ic_shouye);
        tv_ic_shaishai = (TextView) findViewById(R.id.tv_ic_shaishai);
        tv_ic_haoyouquan = (TextView) findViewById(R.id.tv_ic_haoyouquan);
        tv_ic_nearby = (TextView) findViewById(R.id.tv_ic_nearby);
        tv_ic_wo = (TextView) findViewById(R.id.tv_ic_wo);
        tv_ic_share = (TextView) findViewById(R.id.tv_ic_share);

        tv_shouye = (TextView) findViewById(R.id.tv_shouye);
        tv_shaishai = (TextView) findViewById(R.id.tv_shaishai);
        tv_haoyouquan = (TextView) findViewById(R.id.tv_haoyouquan);
        tv_nearby = (TextView) findViewById(R.id.tv_nearby);
        tv_wo = (TextView) findViewById(R.id.tv_wo);
    }

    private void initData() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        tv_ic_shouye.setTypeface(font);
        tv_ic_shaishai.setTypeface(font);
        tv_ic_haoyouquan.setTypeface(font);
        tv_ic_nearby.setTypeface(font);
        tv_ic_wo.setTypeface(font);
        tv_ic_share.setTypeface(font);
    }

    private void initEvent() {
        ll_shouye.setOnClickListener(this);
        ll_shaishai.setOnClickListener(this);
        ll_haoyouquan.setOnClickListener(this);
        ll_nearby.setOnClickListener(this);
        ll_wo.setOnClickListener(this);

        tv_ic_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_shouye: {
                setSelect(0);
                break;
            }

            case R.id.ll_shaishai: {
                setSelect(1);
                break;
            }

            case R.id.ll_haoyouquan: {
                setSelect(2);
                break;
            }

            case R.id.ll_nearby: {
                setSelect(3);
                break;
            }

            case R.id.ll_wo: {
                setSelect(4);
                break;
            }

            case R.id.tv_ic_share: {
                Intent intent = new Intent(MainActivity.this, ShareActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    private void setSelect(int i) {
        resetNavBar();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                if (shouYeFragment == null) {
                    shouYeFragment = new ShouYeFragment();
                    transaction.add(R.id.fl_container, shouYeFragment);
                } else {
                    transaction.show(shouYeFragment);
                }

                tv_ic_shouye.setTextColor(getResources().getColor(R.color.ningmenghuang));
                tv_shouye.setTextColor(getResources().getColor(R.color.ningmenghuang));
                break;

            case 1:
                if (shaiShaiFragment == null) {
                    shaiShaiFragment = new ShaiShaiFragment();
                    transaction.add(R.id.fl_container, shaiShaiFragment);
                } else {
                    transaction.show(shaiShaiFragment);
                }

                tv_ic_shaishai.setTextColor(getResources().getColor(R.color.ningmenghuang));
                tv_shaishai.setTextColor(getResources().getColor(R.color.ningmenghuang));
                break;

            case 2:
                if (haoYouQuanFragment == null) {
                    haoYouQuanFragment = new HaoYouQuanFragment();
                    transaction.add(R.id.fl_container, haoYouQuanFragment);
                } else {
                    transaction.show(haoYouQuanFragment);
                }

                tv_ic_haoyouquan.setTextColor(getResources().getColor(R.color.ningmenghuang));
                tv_haoyouquan.setTextColor(getResources().getColor(R.color.ningmenghuang));
                break;

            case 3:
                if (nearbyFragment == null) {
                    nearbyFragment = new NearbyFragment();
                    transaction.add(R.id.fl_container, nearbyFragment);
                } else {
                    transaction.show(nearbyFragment);
                }

                tv_ic_nearby.setTextColor(getResources().getColor(R.color.ningmenghuang));
                tv_nearby.setTextColor(getResources().getColor(R.color.ningmenghuang));
                break;

            case 4:
                if (woFragment == null) {
                    woFragment = new WoFragment();
                    transaction.add(R.id.fl_container, woFragment);
                } else {
                    transaction.show(woFragment);
                }

                tv_ic_wo.setTextColor(getResources().getColor(R.color.ningmenghuang));
                tv_wo.setTextColor(getResources().getColor(R.color.ningmenghuang));
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (shouYeFragment != null) {
            transaction.hide(shouYeFragment);
        }

        if (shaiShaiFragment != null) {
            transaction.hide(shaiShaiFragment);
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
        tv_ic_shouye.setTextColor(getResources().getColor(R.color.xuese));
        tv_ic_shaishai.setTextColor(getResources().getColor(R.color.xuese));
        tv_ic_haoyouquan.setTextColor(getResources().getColor(R.color.xuese));
        tv_ic_nearby.setTextColor(getResources().getColor(R.color.xuese));
        tv_ic_wo.setTextColor(getResources().getColor(R.color.xuese));

        tv_shouye.setTextColor(getResources().getColor(R.color.xuese));
        tv_shaishai.setTextColor(getResources().getColor(R.color.xuese));
        tv_haoyouquan.setTextColor(getResources().getColor(R.color.xuese));
        tv_nearby.setTextColor(getResources().getColor(R.color.xuese));
        tv_wo.setTextColor(getResources().getColor(R.color.xuese));
    }
}