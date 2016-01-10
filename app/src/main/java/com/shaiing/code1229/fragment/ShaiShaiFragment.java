package com.shaiing.code1229.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.shaiing.code1229.MyPagerAdapter;
import com.shaiing.code1229.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by natalie on 2016/1/6.
 */
public class ShaiShaiFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private MyPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<View> views = new ArrayList<>();
        views.add(View.inflate(getActivity(), R.layout.view3, null));
        views.add(View.inflate(getActivity(), R.layout.view4, null));

        adapter = new MyPagerAdapter(views);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shai_shai, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Animation a1 = AnimationUtils.loadAnimation(getActivity(),R.anim.alpha_0_point_5_to_1);
        Animation a2 = AnimationUtils.loadAnimation(getActivity(),R.anim.alpha_1_to_0_point_5);

        if (position == 0) {
        } else if (position == 1) {
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
