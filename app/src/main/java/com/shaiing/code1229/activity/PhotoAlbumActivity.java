package com.shaiing.code1229.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaiing.code1229.R;

import java.util.ArrayList;
import java.util.List;

public class PhotoAlbumActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private List<ImageView> mListImageViews;
    private PagerAdapter mAdapter;
    private Button btn_back;

    private ArrayList<String> mListPhotos;

    private TextView tv_current_photo;
    private TextView tv_total_photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_album);
        //init view
        tv_current_photo = (TextView) findViewById(R.id.tv_current_photo);
        tv_total_photos = (TextView) findViewById(R.id.tv_total_photos);

        Intent intent = getIntent();
        mListPhotos = intent.getStringArrayListExtra("mListPhotos");
        int currentIndex = intent.getIntExtra("currentIndex", 0);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mListImageViews = new ArrayList<>();
        for (int i = 0; i < mListPhotos.size(); i++) {
            ImageView iv = new ImageView(this);
            Matrix matrix = new Matrix();
            matrix.setRotate(90);
            Bitmap bitmap = BitmapFactory.decodeFile(mListPhotos.get(i));
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
            iv.setImageBitmap(bitmap);
            mListImageViews.add(iv);
        }

        mAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mListImageViews.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mListImageViews.get(position));
                return mListImageViews.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mListImageViews.get(position));
            }
        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(currentIndex);

        tv_total_photos.setText(String.valueOf(mListPhotos.size()));
        tv_current_photo.setText(String.valueOf(currentIndex + 1));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tv_current_photo.setText(String.valueOf(position + 1));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
