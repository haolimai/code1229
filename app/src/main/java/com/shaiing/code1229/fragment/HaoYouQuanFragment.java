package com.shaiing.code1229.fragment;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    private View border_bottom_left;
    private View border_bottom_right;

    private TextView text_left;
    private TextView text_right;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<View> views = new ArrayList<>();
        views.add(View.inflate(getActivity(), R.layout.view5, null));
        views.add(View.inflate(getActivity(), R.layout.view6, null));

        adapter = new MyPagerAdapter(views);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hao_you_quan, container, false);

        text_left = (TextView) view.findViewById(R.id.text_left);
        text_left.setText("圈儿");
        text_right = (TextView) view.findViewById(R.id.text_right);
        text_right.setText("好友");

        border_bottom_left = view.findViewById(R.id.border_bottom_left);
        border_bottom_right = view.findViewById(R.id.border_bottom_right);

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
        switch (position) {
            case 0: {
                toggleTab(border_bottom_left, border_bottom_right, text_left, text_right);
                break;
            }

            case 1: {
                toggleTab(border_bottom_right, border_bottom_left, text_right, text_left);
                break;
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void toggleTab(View show, View disappear, TextView selected, TextView unselected) {
        ObjectAnimator.ofFloat(show, "alpha", 0.5f, 1.0f).setDuration(500).start();
        ObjectAnimator.ofFloat(disappear, "alpha", 1.0f, 0.0f).setDuration(500).start();

        selected.setTextColor(getResources().getColor(R.color.ningmenghuang));
        ObjectAnimator.ofFloat(selected, "alpha", 0.5f, 1.0f).setDuration(500).start();
        unselected.setTextColor(getResources().getColor(R.color.xuese));
    }
}
