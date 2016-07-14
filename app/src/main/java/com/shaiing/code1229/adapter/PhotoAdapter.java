package com.shaiing.code1229.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shaiing.code1229.R;

import java.util.List;

/**
 * Created by mike on 2016/7/8.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {
    private Context mContext;
    private List<Bitmap> mData;
    private LayoutInflater mInflater;

    public PhotoAdapter(Context context, List<Bitmap> data) {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_photo_find, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int pos = holder.getLayoutPosition();
        if (pos > 0) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holder.iv_photo.getLayoutParams();
            lp.leftMargin = mContext.getResources().getDimensionPixelSize(R.dimen.photo_space);
            holder.iv_photo.setLayoutParams(lp);
        }
        holder.iv_photo.setImageBitmap(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_photo;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_photo = (ImageView) itemView.findViewById(R.id.iv_photo);
        }
    }
}

