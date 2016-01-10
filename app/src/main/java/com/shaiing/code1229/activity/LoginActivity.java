package com.shaiing.code1229.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.shaiing.code1229.MyTextWatcher;
import com.shaiing.code1229.R;

/**
 * Created by natalie on 2015/12/30.
 */
public class LoginActivity extends Activity {
    private EditText et_activity_login_username;
    private EditText et_activity_login_pwd;
    private Button btn_activity_login_login;

    private boolean flag;

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
        et_activity_login_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String username = s.toString().trim();
                String pwd = et_activity_login_pwd.getText().toString().trim();

                if (username.equals("") || pwd.equals("")) {
                    if (flag) {
                        ObjectAnimator.ofFloat(btn_activity_login_login, "Alpha", 1.0f, 0.0f).setDuration(500).start();
                        flag = false;
                    }
                } else if (!username.equals("") && !pwd.equals("")) {
                    if (!flag) {
                        ObjectAnimator.ofFloat(btn_activity_login_login, "Alpha", 0.0f, 1.0f).setDuration(500).start();
                        flag = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_activity_login_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String username = s.toString().trim();
                String pwd = et_activity_login_username.getText().toString().trim();

                if (username.equals("") || pwd.equals("")) {
                    if (flag) {
                        ObjectAnimator.ofFloat(btn_activity_login_login, "Alpha", 1.0f, 0.0f).setDuration(500).start();
                        flag = false;
                    }
                } else if (!username.equals("") && !pwd.equals("")) {
                    if (!flag) {
                        ObjectAnimator.ofFloat(btn_activity_login_login, "Alpha", 0.0f, 1.0f).setDuration(500).start();
                        flag = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_activity_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void initData() {
    }

}
