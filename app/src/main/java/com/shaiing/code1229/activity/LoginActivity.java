package com.shaiing.code1229.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shaiing.code1229.R;

/**
 * Created by natalie on 2015/12/30.
 */
public class LoginActivity extends Activity {
    private EditText et_username;
    private EditText et_pwd;
    private Button btn_login;

    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();
        initEvent();

    }

    public void initView() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
    }

    public void initEvent() {
        et_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String username = s.toString().trim();
                String pwd = et_pwd.getText().toString().trim();

                if (username.equals("") || pwd.equals("")) {
                    if (flag) {
                        ObjectAnimator.ofFloat(btn_login, "Alpha", 1.0f, 0.0f).setDuration(500).start();
                        flag = false;
                    }
                } else if (!username.equals("") && !pwd.equals("")) {
                    if (!flag) {
                        ObjectAnimator.ofFloat(btn_login, "Alpha", 0.0f, 1.0f).setDuration(500).start();
                        flag = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String username = s.toString().trim();
                String pwd = et_username.getText().toString().trim();

                if (username.equals("") || pwd.equals("")) {
                    if (flag) {
                        ObjectAnimator.ofFloat(btn_login, "Alpha", 1.0f, 0.0f).setDuration(500).start();
                        flag = false;
                    }
                } else if (!username.equals("") && !pwd.equals("")) {
                    if (!flag) {
                        ObjectAnimator.ofFloat(btn_login, "Alpha", 0.0f, 1.0f).setDuration(500).start();
                        flag = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void initData() {
    }

}
