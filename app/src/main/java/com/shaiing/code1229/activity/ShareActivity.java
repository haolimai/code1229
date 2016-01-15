package com.shaiing.code1229.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shaiing.code1229.R;

public class ShareActivity extends Activity implements View.OnClickListener {
    private TextView tv_ic_close;
    private TextView tv_ic_text;
    private TextView tv_ic_photo;
    private TextView tv_ic_voice;
    private TextView tv_ic_video;
    private TextView tv_ic_shaiyishai;
    private TextView tv_ic_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        initView();
        initData();
        initEvent();
    }

    private void initData() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        tv_ic_close.setTypeface(font);
        tv_ic_text.setTypeface(font);
        tv_ic_photo.setTypeface(font);
        tv_ic_voice.setTypeface(font);
        tv_ic_video.setTypeface(font);
        tv_ic_shaiyishai.setTypeface(font);
        tv_ic_more.setTypeface(font);
    }

    private void initView() {
        tv_ic_close = (TextView) findViewById(R.id.tv_ic_close);

        tv_ic_text = (TextView) findViewById(R.id.tv_ic_text);
        tv_ic_photo = (TextView) findViewById(R.id.tv_ic_photo);
        tv_ic_voice = (TextView) findViewById(R.id.tv_ic_voice);
        tv_ic_video = (TextView) findViewById(R.id.tv_ic_video);
        tv_ic_shaiyishai = (TextView) findViewById(R.id.tv_ic_shaiyishai);
        tv_ic_more = (TextView) findViewById(R.id.tv_ic_more);

    }

    private void initEvent() {
        tv_ic_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ic_text: {

                break;
            }

            case R.id.tv_ic_photo: {

                break;
            }

            case R.id.tv_ic_voice: {

                break;
            }

            case R.id.tv_ic_video: {

                break;
            }

            case R.id.tv_ic_shaiyishai: {

                break;
            }

            case R.id.tv_ic_more: {

                break;
            }
        }
    }
}
