package com.shaiing.code1229.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaiing.code1229.MyPagerAdapter;
import com.shaiing.code1229.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by natalie on 2016/1/6.
 */
public class NearbyFragment extends Fragment {
    private MyPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<View> views = new ArrayList<>();
        views.add(View.inflate(getActivity(), R.layout.view7, null));
        views.add(View.inflate(getActivity(), R.layout.view8, null));
        views.add(View.inflate(getActivity(), R.layout.view9, null));

        adapter = new MyPagerAdapter(views);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1,true);

        return view;
    }
}
