package com.shaiing.code1229;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by natalie on 2015/12/30.
 */
public class LoginActivity extends Activity {
    private EditText et_activity_login_username;
    private EditText et_activity_login_pwd;
    private Button btn_activity_login_login;

    private Animation show;
    private Animation disappear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initData();
        initEvents();

    }

    public void initViews() {
        et_activity_login_username = (EditText) findViewById(R.id.et_activity_login_username);
        et_activity_login_pwd = (EditText) findViewById(R.id.et_activity_login_pwd);
        btn_activity_login_login = (Button) findViewById(R.id.btn_activity_login_login);
    }

    public void initEvents() {
        et_activity_login_username.addTextChangedListener(new MyTextWatcher(et_activity_login_pwd, btn_activity_login_login, show, disappear));
        et_activity_login_pwd.addTextChangedListener(new MyTextWatcher(et_activity_login_username, btn_activity_login_login, show, disappear));
        btn_activity_login_login.setOnClickListener(new View.OnClickListener() {
            private Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.btn_alpha_0_point_5_to_1);

            @Override
            public void onClick(View v) {
                if (v.getVisibility() == View.VISIBLE) {
                    v.startAnimation(animation);
                }
            }
        });
    }

    public void initData() {
        show = AnimationUtils.loadAnimation(this, R.anim.btn_alpha_0_to_1);
        disappear = AnimationUtils.loadAnimation(this, R.anim.btn_alpha_1_to_0);
    }

}
