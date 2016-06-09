package com.shaiing.code1229.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.shaiing.code1229.R;
import com.shaiing.code1229.fragment.ImageFragment;
import com.shaiing.code1229.fragment.TextImageFragment;

import java.util.ArrayList;
import java.util.List;

public class PictureActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        initView();
        initData();
        initEvent();
        mViewPager.setCurrentItem(1);
    }

    public void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    public void initEvent() {

    }

    public void initData() {


        Fragment mTextImageFragment = new TextImageFragment();
        Fragment mImageFragment = new ImageFragment();

        mFragments = new ArrayList<>();
        mFragments.add(mTextImageFragment);
        mFragments.add(mImageFragment);

        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };

        mViewPager.setAdapter(mAdapter);
    }

}
