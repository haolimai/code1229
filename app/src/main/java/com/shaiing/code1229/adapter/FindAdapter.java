package com.shaiing.code1229.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shaiing.code1229.R;

import java.util.List;

/**
 * Created by mike on 2016/7/13.
 */
public class FindAdapter extends RecyclerView.Adapter<FindAdapter.MyViewHolder> {
    private Context mContext;
    private int mLayoutId;
    private List<Integer> mData;
    private LayoutInflater mInflater;
    private int mScreenWidth;
    private int mPhotoHeight;

    public FindAdapter(Context context, int layoutId, List<Integer> data, int photo_height) {
        mContext = context;
        mLayoutId = layoutId;
        mInflater = LayoutInflater.from(context);
        mData = data;

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        mScreenWidth = displayMetrics.widthPixels;

        mPhotoHeight = photo_height;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(mLayoutId, parent, false);
        return new MyViewHolder(view, mScreenWidth, mPhotoHeight);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.iv_photo.setImageResource(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_photo;

        public MyViewHolder(View itemView, int screenWidth, int photo_height) {
            super(itemView);

            iv_photo = (ImageView) itemView.findViewById(R.id.iv_photo);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) iv_photo.getLayoutParams();
            lp.height = screenWidth / photo_height;
            iv_photo.setLayoutParams(lp);
        }
    }
}
