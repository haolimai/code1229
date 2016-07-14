package com.shaiing.code1229.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaiing.code1229.R;
import com.shaiing.code1229.adapter.FindAdapter;
import com.shaiing.code1229.adapter.ShareAdapter;

import java.util.Arrays;
import java.util.List;

public class ShareFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share, container, false);

        List<Integer> data = Arrays.asList(R.mipmap.a1, R.mipmap.a2, R.mipmap.a3, R.mipmap.a4);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        ShareAdapter findAdapter = new ShareAdapter(getActivity(), data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(findAdapter);

        return view;
    }
}
