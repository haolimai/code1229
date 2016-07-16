package com.shaiing.code1229.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.shaiing.code1229.R;
import com.shaiing.code1229.util.CommonUtil;
import com.shaiing.code1229.view.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IndexFragment extends Fragment {
    private FragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new IndexHotFragment());
        fragments.add(new IndexUserFragment());
        fragments.add(new IndexHashtagFragment());
        fragments.add(new IndexLocationFragment());

        mFragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final List<String> titles = Arrays.asList("热门搜索", "用户", "标签", "地点");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_index, container, false);

        final EditText et_search = (EditText) view.findViewById(R.id.et_search);

        TextView tv_search = (TextView) view.findViewById(R.id.tv_search);
        CommonUtil.setFontAwesome(getActivity(), tv_search);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                et_search.setHint(titles.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(mFragmentPagerAdapter);

        ViewPagerIndicator viewPagerIndicator = (ViewPagerIndicator) view.findViewById(R.id.viewPagerIndicator);
        viewPagerIndicator.setIndicatorItem(titles);
        viewPagerIndicator.setViewPager(viewPager, 0);

        return view;
    }

}
