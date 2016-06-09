package com.shaiing.code1229;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaiing.code1229.bean.HomePageBean;

import java.util.List;

/**
 * Created by mike on 2016/3/3.
 */
public class SimpleAdapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnClickListener {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<HomePageBean> mList;

    private boolean isLike;
    private boolean isSmile;
    private boolean isFrown;

    public SimpleAdapter(Context context, List<HomePageBean> list) {
        this.mContext = context;
        this.mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item1, parent, false);

        Typeface font = Typeface.createFromAsset(mContext.getAssets(), "fontawesome-webfont.ttf");

        TextView ic_thumbs_up = (TextView) view.findViewById(R.id.tv_ic_thumbs_up);
        ic_thumbs_up.setTypeface(font);
        ic_thumbs_up.setOnClickListener(this);
        TextView tv_ic_smile = (TextView) view.findViewById(R.id.tv_ic_smile);
        tv_ic_smile.setTypeface(font);
        tv_ic_smile.setOnClickListener(this);
        TextView tv_ic_frown = (TextView) view.findViewById(R.id.tv_ic_frown);
        tv_ic_frown.setTypeface(font);
        tv_ic_frown.setOnClickListener(this);
        TextView tv_ic_comment_o = (TextView) view.findViewById(R.id.tv_ic_comment_o);
        tv_ic_comment_o.setTypeface(font);
        tv_ic_comment_o.setOnClickListener(this);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HomePageBean homePageBean = mList.get(position);
        holder.getIv_avatar().setImageResource(R.mipmap.a);
        holder.getTv_nickname().setText(homePageBean.getNickname());
        holder.getTv_pub_time().setText("一天前");//String.valueOf(homePageBean.getPubTime())
        holder.getIv_picture().setImageResource(R.mipmap.demo2);
        holder.getTv_desc().setText("like this place");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        TextView tv = (TextView) v;
        switch (v.getId()) {
            case R.id.tv_ic_thumbs_up: {
                isLike = !isLike;
                if (isLike) {
                    tv.setTextColor(mContext.getResources().getColor(R.color.yanghong));
                } else {
                    tv.setTextColor(mContext.getResources().getColor(R.color.zitenghui));
                }
                break;
            }

            case R.id.tv_ic_smile: {
                isSmile = !isSmile;
                if (isSmile) {
                    tv.setTextColor(mContext.getResources().getColor(R.color.yanghong));
                } else {
                    tv.setTextColor(mContext.getResources().getColor(R.color.zitenghui));
                }
                break;
            }

            case R.id.tv_ic_frown: {
                isFrown = !isFrown;
                if (isFrown) {
                    tv.setTextColor(mContext.getResources().getColor(R.color.yanghong));
                } else {
                    tv.setTextColor(mContext.getResources().getColor(R.color.zitenghui));
                }
                break;
            }

            case R.id.tv_ic_comment_o: {

                break;
            }
        }
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    private ImageView iv_avatar;
    private TextView tv_nickname;
    private TextView tv_pub_time;
    private ImageView iv_picture;
    private TextView tv_desc;

    public MyViewHolder(View itemView) {
        super(itemView);
        iv_avatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
        tv_nickname = (TextView) itemView.findViewById(R.id.tv_nickname);
        tv_pub_time = (TextView) itemView.findViewById(R.id.tv_pub_time);
        iv_picture = (ImageView) itemView.findViewById(R.id.iv_picture);
        tv_desc = (TextView) itemView.findViewById(R.id.tv_desc);
    }

    public ImageView getIv_avatar() {
        return iv_avatar;
    }

    public void setIv_avatar(ImageView iv_avatar) {
        this.iv_avatar = iv_avatar;
    }

    public TextView getTv_nickname() {
        return tv_nickname;
    }

    public void setTv_nickname(TextView tv_nickname) {
        this.tv_nickname = tv_nickname;
    }

    public TextView getTv_pub_time() {
        return tv_pub_time;
    }

    public void setTv_pub_time(TextView tv_pub_time) {
        this.tv_pub_time = tv_pub_time;
    }

    public ImageView getIv_picture() {
        return iv_picture;
    }

    public void setIv_picture(ImageView iv_picture) {
        this.iv_picture = iv_picture;
    }

    public TextView getTv_desc() {
        return tv_desc;
    }

    public void setTv_desc(TextView tv_desc) {
        this.tv_desc = tv_desc;
    }
}
