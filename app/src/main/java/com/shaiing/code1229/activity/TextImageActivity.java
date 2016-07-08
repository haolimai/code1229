package com.shaiing.code1229.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.NinePatchDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shaiing.code1229.R;
import com.shaiing.code1229.util.CommonPreferences;
import com.shaiing.code1229.util.CommonUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TextImageActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener, View.OnLongClickListener {
    private static final int CACHE_ITEM = 3;

    private int[] mImgs = {
            R.drawable.glass, R.drawable.white, R.drawable.clear, R.drawable.hot, R.drawable.rain
    };

    private Context context;
    private String et_text;
    private int mCurrentIndex;
    private int screenWidth;

    private LinearLayout ll_img;
    private TextView tv_text;

    private List<RelativeLayout> mListRls;
    private List<ImageView> mListIvs;
    private List<TextView> mListTvs;

    private Dialog diyDialog;
    private int[] mTextColors = {
            R.color.baicaoshuang, R.color.shenyan
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_image);

        context = this;

        Intent intent = getIntent();
        et_text = intent.getStringExtra("et_text");

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();

        //type face
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        //init view
        ll_img = (LinearLayout) findViewById(R.id.ll_img);
        tv_text = (TextView) findViewById(R.id.tv_text);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        //init data
        tv_text.setText(et_text);
        mListRls = new ArrayList<>();
        mListIvs = new ArrayList<>();
        mListTvs = new ArrayList<>();

        RelativeLayout rl;
        ImageView iv;
        TextView tv;
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        for (int mImg : mImgs) {
            rl = new RelativeLayout(context);

            iv = new ImageView(context);
            iv.setImageResource(mImg);
            iv.setOnClickListener(imgOnClickListener);
            mListIvs.add(iv);

            tv = new TextView(context);
            tv.setText(getResources().getString(R.string.fa_check));
            tv.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, context.getResources().getDisplayMetrics()));
            tv.setTextColor(getResources().getColor(R.color.fanqiehong));
            tv.setTypeface(font);
            tv.setVisibility(View.GONE);
            mListTvs.add(tv);

            rl.addView(iv);
            rl.addView(tv, lp);

            mListRls.add(rl);
        }

        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mImgs.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mListRls.get(position));
                return mListRls.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mListRls.get(position));
            }
        };

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(CACHE_ITEM);
        viewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.pager_margin));
        viewPager.addOnPageChangeListener(this);

        viewPager.setCurrentItem(CommonPreferences.getCurrentImgIndex(context));
        setCurrentImgSelected(CommonPreferences.getCurrentImgIndex(context));

        //init event
        ll_img.setOnClickListener(this);
    }

    private void getTextImg() {
        NinePatchDrawable bg = (NinePatchDrawable) ll_img.getBackground();
        Log.d("hlm", "ll_img = " + ll_img.getMeasuredWidth());
        Log.d("hlm", "ll_img = " + ll_img.getMeasuredHeight());
        Log.d("hlm", "ll_img = " + ll_img.getWidth());
        Log.d("hlm", "ll_img = " + ll_img.getHeight());

        Bitmap bitmap = Bitmap
                .createBitmap(
                        ll_img.getMeasuredWidth(),
                        ll_img.getMeasuredHeight(),
                        bg.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        bg.setBounds(0, 0, ll_img.getMeasuredWidth(),
                ll_img.getMeasuredHeight());
        bg.draw(canvas);
        Log.d("hlm", "hlm:" + bitmap.getWidth());
        Log.d("hlm", "hlm:" + bitmap.getHeight());

        Canvas textImgCanvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFakeBoldText(true);
        paint.setColor(getResources().getColor(R.color.xuese));
        paint.setTextSize(tv_text.getTextSize());

        Rect textBounds = new Rect();
        paint.getTextBounds(et_text, 0, et_text.length(), textBounds);

        int start = 0;
        int end = et_text.length();
        int row = 1;
        int textHeight = textBounds.height();
        float line_spacing = tv_text.getLineSpacingExtra();

        while (start < et_text.length()) {
            int t = paint.breakText(et_text, start, end, true, bitmap.getWidth(), null);
            textImgCanvas.drawText(et_text, start, start + t, 0, row * (textHeight + line_spacing), paint);
            start += t;
            row++;
        }

        File textImg = CommonUtil.getOutputMediaFile(CommonUtil.MEDIA_TYPE_IMAGE);
        try {
            FileOutputStream fos = new FileOutputStream(textImg);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Toast.makeText(TextImageActivity.this, "保存成功", Toast.LENGTH_SHORT).show();

            MediaScannerConnection.scanFile(
                    context,
                    new String[]{textImg.toString()},
                    null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.d("hlm", "Scanned " + path + ":");
                            Log.d("hlm", "-> uri=" + uri);
                        }
                    }
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("hlm", "failed...");
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrentIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_img:
                getTextImg();
                break;
        }
    }

    private View.OnClickListener imgOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 0, count = mListIvs.size(); i < count; i++) {
                if (v == mListIvs.get(i)) {
                    ll_img.setBackgroundResource(mImgs[i]);
                    break;
                }
            }
            setCurrentImgSelected(mCurrentIndex);
            CommonPreferences.setCurrentImgIndex(context, mCurrentIndex);
        }
    };

    private void setCurrentImgSelected(int index) {
        for (int i = 0, l = mListTvs.size(); i < l; i++) {
            mListTvs.get(i).setVisibility(View.GONE);
        }
        mListTvs.get(index).setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onLongClick(View v) {
        if (diyDialog == null) {
            diyDialog = new Dialog(context, R.style.diyDialog);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_diy, null, false);
            LinearLayout ll_img = (LinearLayout) view.findViewById(R.id.ll_img);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) ll_img.getLayoutParams();
            if (lp == null) {
                lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
            lp.width = screenWidth;
            lp.height = screenWidth;
            ll_img.setLayoutParams(lp);
            diyDialog.setContentView(view);
            Window window = diyDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.diaDialogAnim);
        }

        if (!diyDialog.isShowing()) {
            diyDialog.show();
        }

        return true;
    }
}
