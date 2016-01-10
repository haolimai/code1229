package com.shaiing.code1229.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shaiing.code1229.R;

public class ShareActivity extends Activity {
    private Typeface font;

    private TextView tv_activity_share_close;
    private TextView tv_activity_share_text;
    private TextView tv_activity_share_photo;
    private TextView tv_activity_share_voice;
    private TextView tv_activity_share_video;
    private TextView tv_activity_share_artice;
    private TextView tv_activity_share_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        initData();
        initViews();
        initEvents();
    }

    private void initData() {
        font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
    }

    private void initViews() {
        tv_activity_share_close = (TextView) findViewById(R.id.tv_activity_share_icon_close);
        tv_activity_share_close.setTypeface(font);
        tv_activity_share_text = (TextView) findViewById(R.id.tv_activity_share_icon_text);
        tv_activity_share_text.setTypeface(font);
        tv_activity_share_photo = (TextView) findViewById(R.id.tv_activity_share_icon_photo);
        tv_activity_share_photo.setTypeface(font);
        tv_activity_share_voice = (TextView) findViewById(R.id.tv_activity_share_icon_voice);
        tv_activity_share_voice.setTypeface(font);
        tv_activity_share_video = (TextView) findViewById(R.id.tv_activity_share_icon_video);
        tv_activity_share_video.setTypeface(font);
        tv_activity_share_artice = (TextView) findViewById(R.id.tv_activity_share_icon_article);
        tv_activity_share_artice.setTypeface(font);
        tv_activity_share_more = (TextView) findViewById(R.id.tv_activity_share_icon_more);
        tv_activity_share_more.setTypeface(font);

    }

    private void initEvents() {
        tv_activity_share_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.alpha_0_to_1, R.anim.alpha_1_to_0);
            }
        });
    }


}
