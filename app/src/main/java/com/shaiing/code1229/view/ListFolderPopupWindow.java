package com.shaiing.code1229.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.shaiing.code1229.R;
import com.shaiing.code1229.bean.FolderBean;
import com.shaiing.code1229.util.ImageLoader;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by mike on 2016/6/27.
 */
public class ListFolderPopupWindow extends PopupWindow {
    private int mWidth;
    private int mHeight;
    private View mConvertView;
    private ListView mListView;
    private List<FolderBean> mData;

    public interface OnFolderSelectedListener {
        void onSelected(FolderBean folderBean);
    }

    public OnFolderSelectedListener mListener;

    public void setOnFolderSelectedListener(OnFolderSelectedListener listener) {
        mListener = listener;
    }

    public ListFolderPopupWindow(Context context, List<FolderBean> data) {
        calWidthAndHeight(context);
        mConvertView = LayoutInflater.from(context).inflate(R.layout.popup_folder, null);
        mData = data;

        setContentView(mConvertView);
        setWidth(mWidth);
        setHeight(mHeight);

        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });

        initView(context);
        initEvent();
    }

    private void initEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null) {
                    mListener.onSelected(mData.get(position));
                }
            }
        });
    }

    private void initView(Context context) {
        mListView = (ListView) mConvertView.findViewById(R.id.listView);
        mListView.setAdapter(new FolderAdapter(context, mData));
    }

    private void calWidthAndHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mWidth = dm.widthPixels;
        mHeight = (int) (dm.heightPixels * 0.7f);
    }

    private class FolderAdapter extends ArrayAdapter<FolderBean> {
        private LayoutInflater mInflater;

        public FolderAdapter(Context context, List<FolderBean> objects) {
            super(context, 0, objects);
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = new ViewHolder();
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_folder, parent, false);
                viewHolder.iv_first_img = (ImageView) convertView.findViewById(R.id.iv_first_img);
                viewHolder.tv_folder_name = (TextView) convertView.findViewById(R.id.tv_folder_name);
                viewHolder.tv_folder_count = (TextView) convertView.findViewById(R.id.tv_folder_count);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            FolderBean folderBean = getItem(position);
            viewHolder.iv_first_img.setImageResource(R.drawable.picture_no);
            ImageLoader.getInstance().loadImage(folderBean.getFirstImgPath(), viewHolder.iv_first_img);

            viewHolder.tv_folder_name.setText(folderBean.getName());
            viewHolder.tv_folder_count.setText(String.valueOf(folderBean.getCount()));
            return convertView;
        }

        private class ViewHolder {
            ImageView iv_first_img;
            TextView tv_folder_name;
            TextView tv_folder_count;
        }
    }
}
