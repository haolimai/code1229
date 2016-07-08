package com.shaiing.code1229.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shaiing.code1229.R;
import com.shaiing.code1229.util.CommonUtil;

/**
 * Created by natalie on 2016/1/6.
 */
public class UserFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        TextView tv_mars = (TextView) view.findViewById(R.id.tv_mars);
        TextView tv_certificate = (TextView) view.findViewById(R.id.tv_certificate);
        CommonUtil.setFontAwesome(getActivity(), tv_mars, tv_certificate);

        return view;
    }
}
