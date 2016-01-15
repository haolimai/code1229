package com.shaiing.code1229.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shaiing.code1229.R;

/**
 * Created by natalie on 2015/12/30.
 */
public class WelcomeActivity extends Activity implements View.OnClickListener {
    private Button btn_login;
    private Button btn_register;

    private TextView tv_explore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();
        initEvent();
        initData();

    }

    public void initView() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        tv_explore = (TextView) findViewById(R.id.tv_explore);
    }

    public void initEvent() {
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        tv_explore.setOnClickListener(this);
    }

    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.btn_register: {
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.tv_explore: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
