package com.shaiing.code1229;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by natalie on 2015/12/30.
 */
public class WelcomeActivity extends Activity implements View.OnClickListener {
    private Button btn_activity_welcome_login;
    private Button btn_activity_welcome_register;

    private TextView tv_activity_welcome_explore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initViews();
        initEvents();
        initData();

    }

    public void initViews() {
        btn_activity_welcome_login = (Button) findViewById(R.id.btn_activity_welcome_login);
        btn_activity_welcome_register = (Button) findViewById(R.id.btn_activity_welcome_register);
        tv_activity_welcome_explore = (TextView) findViewById(R.id.tv_activity_welcome_explore);
    }

    public void initEvents() {
        btn_activity_welcome_login.setOnClickListener(this);
        btn_activity_welcome_register.setOnClickListener(this);
        tv_activity_welcome_explore.setOnClickListener(this);
    }

    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_activity_welcome_login: {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.btn_activity_welcome_register: {
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.tv_activity_welcome_explore: {
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
