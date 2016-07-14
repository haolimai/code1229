package com.shaiing.code1229.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaiing.code1229.R;
import com.shaiing.code1229.util.CommonUtil;
import com.shaiing.code1229.util.ImageLoader;

import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mike on 2016/6/27.
 */
public class GalleryAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mImgs;
    private String mParentPath;
    private LayoutInflater mInflater;

    private static Set<String> mSelectedImgs = new HashSet<>();

    public GalleryAdapter(Context context, List<String> imgs, String parentPath) {
        mContext = context;
        mImgs = imgs;
        mParentPath = parentPath;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mImgs.size();
    }

    @Override
    public Object getItem(int position) {
        return mImgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_gallery, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv_item);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv_select);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().loadImage(mParentPath + "/" + mImgs.get(position), viewHolder.iv);

        //重置图片状态
        final String filePath = mParentPath + "/" + mImgs.get(position);
        viewHolder.iv.setImageResource(R.drawable.picture_no);
        viewHolder.iv.setColorFilter(null);
        viewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedImgs.contains(filePath)) {
                    mSelectedImgs.remove(filePath);
                    viewHolder.iv.setColorFilter(null);
                    viewHolder.tv.setTextColor(mContext.getResources().getColor(android.R.color.white));
                } else {
                    mSelectedImgs.add(filePath);
                    viewHolder.iv.setColorFilter(Color.parseColor("#7f000000"));
                    viewHolder.tv.setTextColor(mContext.getResources().getColor(R.color.jiehuang));
                }

            }
        });
        viewHolder.tv.setTextColor(mContext.getResources().getColor(android.R.color.white));
        CommonUtil.setFontAwesome(mContext, viewHolder.tv);

        if (mSelectedImgs.contains(filePath)) {
            viewHolder.iv.setColorFilter(Color.parseColor("#7f000000"));
            viewHolder.tv.setTextColor(mContext.getResources().getColor(R.color.jiehuang));
        }

        return convertView;
    }

    private class ViewHolder {
        ImageView iv;
        TextView tv;
    }
}
