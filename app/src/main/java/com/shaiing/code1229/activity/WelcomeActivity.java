package com.shaiing.code1229.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaiing.code1229.R;

import java.net.URL;

/**
 * Created by natalie on 2015/12/30.
 */
public class WelcomeActivity extends Activity implements View.OnClickListener {
    private TextView tvLogin;
    private TextView tvReg;

    private Button btnExplore;
    private ImageView iv_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initData();
        initView();
        initEvent();

        //test
    }

    public void initView() {
        tvLogin = (TextView) findViewById(R.id.tvLogin);
        tvReg = (TextView) findViewById(R.id.tvReg);
        btnExplore = (Button) findViewById(R.id.btnExplore);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
    }

    public void initEvent() {
        tvLogin.setOnClickListener(this);
        tvReg.setOnClickListener(this);
        btnExplore.setOnClickListener(this);
        iv_icon.setOnClickListener(this);
    }

    public void initData() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLogin: {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.tvReg: {
                Intent intent = new Intent(this, RegActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.iv_icon: {
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

}
