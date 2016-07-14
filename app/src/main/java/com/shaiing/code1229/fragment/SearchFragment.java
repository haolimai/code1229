package com.shaiing.code1229.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaiing.code1229.R;
import com.shaiing.code1229.view.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by natalie on 2016/1/6.
 */
public class SearchFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private List<String> mTitles = Arrays.asList("发现", "晒什么", "搜索");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FindFragment());
        fragments.add(new ShareFragment());
        fragments.add(new IndexFragment());

        mFragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

        };

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(mFragmentPagerAdapter);
        viewPager.addOnPageChangeListener(this);

        ViewPagerIndicator viewPagerIndicator = (ViewPagerIndicator) view.findViewById(R.id.viewPagerIndicator);
        viewPagerIndicator.setIndicatorItem(mTitles);
        viewPagerIndicator.setViewPager(viewPager, 0);

        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0: {
                break;
            }

            case 1: {
                break;
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
