package com.shaiing.code1229.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shaiing.code1229.R;
import com.shaiing.code1229.bean.HomePageBean;
import com.shaiing.code1229.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mike on 2016/3/3.
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.MyViewHolder> implements View.OnClickListener {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<HomePageBean> mList;

    private boolean isLike;

    private int mScreenWidth;

    public SimpleAdapter(Context context, List<HomePageBean> list) {
        this.mContext = context;
        this.mList = list;
        mInflater = LayoutInflater.from(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item1, parent, false);

        TextView tv_heart_o = (TextView) view.findViewById(R.id.tv_heart_o);
        tv_heart_o.setOnClickListener(this);
        TextView tv_comment_o = (TextView) view.findViewById(R.id.tv_comment_o);
        tv_comment_o.setOnClickListener(this);
        TextView tv_share_square_o = (TextView) view.findViewById(R.id.tv_share_square_o);
        tv_share_square_o.setOnClickListener(this);
        CommonUtil.setFontAwesome(mContext, tv_heart_o, tv_comment_o, tv_share_square_o);

        MyViewHolder holder = new MyViewHolder(view);
        ViewGroup.LayoutParams lp = holder.viewPager.getLayoutParams();
        lp.height = mScreenWidth;
        holder.viewPager.setLayoutParams(lp);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        HomePageBean homePageBean = mList.get(position);
        holder.iv_avatar.setImageResource(R.mipmap.a);
        holder.tv_nickname.setText(homePageBean.getNickname());
        holder.tv_pub_time.setText("一天前");

        final List<Integer> data = new ArrayList<>();
        final List<ImageView> ivs = new ArrayList<>();
        int[] t = {R.mipmap.a1, R.mipmap.a2,
                R.mipmap.a3,
                R.mipmap.a4,
                R.mipmap.a5,
                R.mipmap.bb,
                R.mipmap.cc,
                R.mipmap.a8,
                R.mipmap.a9,
                R.mipmap.aa
        };

        holder.tv_total_pages.setText(String.valueOf(t.length));
        for (int i = 0; i < t.length; i++) {
            data.add(t[i]);
        }

        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView iv = new ImageView(mContext);
                iv.setImageResource(data.get(position));
                container.addView(iv);
                ivs.add(iv);
                return iv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(ivs.get(position));
            }
        };

        holder.viewPager.setAdapter(pagerAdapter);

        holder.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                holder.tv_current_page.setText(String.valueOf(position + 1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        TextView tv = (TextView) v;
        switch (v.getId()) {
            case R.id.tv_heart_o: {
                isLike = !isLike;
                if (isLike) {
                    tv.setText(mContext.getString(R.string.fa_heart));
                    tv.setTextColor(mContext.getResources().getColor(R.color.yanghong));
                } else {
                    tv.setText(mContext.getString(R.string.fa_heart_o));
                    tv.setTextColor(mContext.getResources().getColor(R.color.xiangsihui));
                }
                break;
            }

            case R.id.tv_comment_o: {

                break;
            }

        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_avatar;
        TextView tv_nickname;
        TextView tv_pub_time;
        ViewPager viewPager;
        TextView tv_current_page;
        TextView tv_total_pages;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_avatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            tv_nickname = (TextView) itemView.findViewById(R.id.tv_nickname);
            tv_pub_time = (TextView) itemView.findViewById(R.id.tv_pub_time);
            viewPager = (ViewPager) itemView.findViewById(R.id.viewPager);
            tv_current_page = (TextView) itemView.findViewById(R.id.tv_current_page);
            tv_total_pages = (TextView) itemView.findViewById(R.id.tv_total_pages);
        }
    }
}


