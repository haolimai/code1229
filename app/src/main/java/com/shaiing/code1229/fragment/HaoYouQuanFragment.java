package com.shaiing.code1229.fragment;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shaiing.code1229.MyPagerAdapter;
import com.shaiing.code1229.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by natalie on 2016/1/6.
 */
public class HaoYouQuanFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private MyPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> titles = new ArrayList<>();
        titles.add("圈儿");
        titles.add("好友");

        List<View> views = new ArrayList<>();
        views.add(View.inflate(getActivity(), R.layout.view5, null));
        views.add(View.inflate(getActivity(), R.layout.view6, null));

        adapter = new MyPagerAdapter(views, titles);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hao_you_quan, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

//        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
//        tabLayout.setupWithViewPager(viewPager);

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
