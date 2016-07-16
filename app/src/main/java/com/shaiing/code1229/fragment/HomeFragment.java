package com.shaiing.code1229.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaiing.code1229.R;
import com.shaiing.code1229.adapter.SimpleAdapter;
import com.shaiing.code1229.bean.HomePageBean;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<HomePageBean> mList;
    private SimpleAdapter mSimpleAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        mList = new ArrayList<>();
        HomePageBean homePageBean;
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                homePageBean = new HomePageBean("", "natalie portman", i, "", "desc" + i);
            } else {
                homePageBean = new HomePageBean("", "杉原杏璃", 1000, "", "desc" + i);
            }
            mList.add(homePageBean);
        }

        mSimpleAdapter = new SimpleAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(mSimpleAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }
}
