package com.shaiing.code1229.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaiing.code1229.R;
import com.shaiing.code1229.adapter.FindAdapter;
import com.shaiing.code1229.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(R.mipmap.a6);
        }

        FindAdapter findAdapter = new FindAdapter(getActivity(),R.layout.item_photo_find, data, 3);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
        recyclerView.setAdapter(findAdapter);

        return view;
    }


}
