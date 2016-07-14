package com.shaiing.code1229.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shaiing.code1229.R;
import com.shaiing.code1229.util.CommonUtil;

/**
 * Created by natalie on 2016/1/6.
 */
public class UserFragment extends Fragment {
    private Button btn_follow;
    private boolean following;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        TextView tv_mars = (TextView) view.findViewById(R.id.tv_mars);
        TextView tv_certificate = (TextView) view.findViewById(R.id.tv_certificate);
        CommonUtil.setFontAwesome(getActivity(), tv_mars, tv_certificate);

        btn_follow = (Button) view.findViewById(R.id.btn_follow);

        btn_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (following) {
                    btn_follow.setBackgroundResource(R.drawable.btn_unfollow_style);
                    btn_follow.setTextColor(getResources().getColor(R.color.ningmenghuang));
                    btn_follow.setText(R.string.follow);
                } else {
                    btn_follow.setBackgroundResource(R.drawable.btn_follow_style);
                    btn_follow.setTextColor(getResources().getColor(android.R.color.white));
                    btn_follow.setText(R.string.unfollow);
                }

                following = !following;
            }
        });

        return view;
    }
}
