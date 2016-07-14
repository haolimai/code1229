package com.shaiing.code1229.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shaiing.code1229.R;

import java.util.List;


/**
 * Created by mike on 2016/4/23.
 */
public class ViewPagerIndicator extends LinearLayout {
    private int mUnderlineWidth;
    private float mUnderlineHeight;
    private int mTranslationX;

    private Paint mPaint;
    private Path mPath;

    private int mVisibleItemCount;
    private static final int DEFAULT_COUNT = 3;

    private static final int TEXT_COLOR_NORMAL = 0xff303030;
    private static final int TEXT_COLOR_HIGHLIGHT = 0xffe9db39;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
        mVisibleItemCount = ta.getInt(R.styleable.ViewPagerIndicator_visible_item_count, DEFAULT_COUNT);
        mUnderlineHeight = ta.getDimension(R.styleable.ViewPagerIndicator_underline_height, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics()));
        ta.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(TEXT_COLOR_HIGHLIGHT);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(mTranslationX, getHeight());
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mUnderlineWidth = w / mVisibleItemCount;
        initTriangle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            lp.weight = 0;
            lp.width = getScreenWidth() / mVisibleItemCount;
            child.setLayoutParams(lp);
        }

        setIndicatorItemClickListener();
    }

    private int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    private void initTriangle() {
        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mUnderlineWidth, 0);
        mPath.lineTo(mUnderlineWidth, -mUnderlineHeight);
        mPath.lineTo(0, -mUnderlineHeight);
        mPath.close();
    }

    public void scroll(int position, float positionOffset) {
        int itemWidth = getWidth() / mVisibleItemCount;
        mTranslationX = (int) (itemWidth * (position + positionOffset));
        Log.d("hlm", "position = " + position);
        Log.d("hlm", "positionOffset = " + positionOffset);

        if (position >= mVisibleItemCount - 2 && positionOffset > 0 && getChildCount() > mVisibleItemCount) {
            Log.d("hlm", "i am here");
            if (mVisibleItemCount != 1) {
                this.scrollTo(
                        (int) (itemWidth * positionOffset + (position - (mVisibleItemCount - 2)) * itemWidth),
                        0
                );
            } else {
                this.scrollTo((int) (position * itemWidth + itemWidth * positionOffset), 0);
            }

        }

        invalidate();
    }

    public void setVisibleItemCount(int count) {
        mVisibleItemCount = count;
    }

    public void setIndicatorItem(List<String> itemText) {
        if (itemText != null && itemText.size() > 0) {
            removeAllViews();
            for (String item : itemText) {
                addView(generateTextView(item));
            }
        }

        setIndicatorItemClickListener();
    }

    private View generateTextView(String item) {
        TextView tv = new TextView(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.width = getScreenWidth() / mVisibleItemCount;
        tv.setLayoutParams(lp);
        tv.setText(item);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        tv.setTextColor(TEXT_COLOR_NORMAL);
        return tv;
    }

    private ViewPager mViewPager;

    public interface OnPageChangListener {
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        public void onPageSelected(int position);

        public void onPageScrollStateChanged(int state);
    }

    private OnPageChangListener mListener;

    public void setOnPageChangeListener(OnPageChangListener listener) {
        this.mListener = listener;
    }

    public void setViewPager(ViewPager viewPager, int pos) {
        this.mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                scroll(position, positionOffset);
                if (mListener != null) {
                    mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (mListener != null) {
                    mListener.onPageSelected(position);
                }
                indicatorItemSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mListener != null) {
                    mListener.onPageScrollStateChanged(state);
                }
            }
        });
        mViewPager.setCurrentItem(pos);
        indicatorItemSelected(pos);
    }

    private void indicatorItemUnselected() {
        for (int i = 0; i < getChildCount(); i++) {
            TextView tv = (TextView) getChildAt(i);
            if (tv != null) {
                tv.setTextColor(TEXT_COLOR_NORMAL);
            }
        }
    }

    private void indicatorItemSelected(int pos) {
        indicatorItemUnselected();
        TextView tv = (TextView) getChildAt(pos);
        if (tv != null) {
            tv.setTextColor(TEXT_COLOR_HIGHLIGHT);
        }
    }

    private void setIndicatorItemClickListener() {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            final int j = i;
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(j);
                }
            });
        }
    }

}
