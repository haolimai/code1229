package com.shaiing.code1229.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shaiing.code1229.R;

import java.util.List;

/**
 * Created by mike on 2016/7/13.
 */
public class ShareAdapter extends RecyclerView.Adapter<ShareAdapter.MyViewHolder> {
    private Context mContext;
    private List<Integer> mData;
    private LayoutInflater mInflater;
    private int mScreenWidth;

    public ShareAdapter(Context context, List<Integer> data) {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(context);

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        mScreenWidth = displayMetrics.widthPixels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_share, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //holder.iv_photo.setImageResource(mData.get(position));

        holder.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        holder.recyclerView.setAdapter(new FindAdapter(mContext, R.layout.item_photo_share, mData, 2));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_avatar;
        TextView tv_nickname;
        TextView tv_hashtag;
        RecyclerView recyclerView;

        public MyViewHolder(View itemView) {
            super(itemView);

            iv_avatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            tv_nickname = (TextView) itemView.findViewById(R.id.tv_nickname);
            tv_hashtag = (TextView) itemView.findViewById(R.id.tv_hashtag);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);
        }
    }
}
