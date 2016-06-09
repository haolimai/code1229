package com.shaiing.code1229.activity;

import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shaiing.code1229.R;

/**
 * Created by natalie on 2015/12/30.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etUsername;
    private EditText etPwd;
    private Button btnLogin;

    private TextView tvClearUsername;
    private TextView tvClearPwd;
    private TextView tvAngleLeft;

    private boolean isVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();
        initEvent();

    }

    public void initView() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPwd = (EditText) findViewById(R.id.etPwd);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvClearUsername = (TextView) findViewById(R.id.tvClearUsername);
        tvClearUsername.setTypeface(font);
        tvClearPwd = (TextView) findViewById(R.id.tvClearPwd);
        tvClearPwd.setTypeface(font);
        tvAngleLeft = (TextView) findViewById(R.id.tvIcAngleLeft);
        tvAngleLeft.setTypeface(font);
    }

    public void initEvent() {
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String username = s.toString().trim();
                String pwd = etPwd.getText().toString().trim();

                if (username.equals("")) {
                    tvClearUsername.setVisibility(View.GONE);
                } else {
                    tvClearUsername.setVisibility(View.VISIBLE);
                }

                if (username.equals("") || pwd.equals("")) {
                    if (isVisible) {
                        ObjectAnimator.ofFloat(btnLogin, "alpha", 1.0f, 0.0f).setDuration(500).start();
                        isVisible = false;
                    }
                } else if (!username.equals("") && !pwd.equals("")) {
                    if (!isVisible) {
                        ObjectAnimator.ofFloat(btnLogin, "alpha", 0.0f, 1.0f).setDuration(500).start();
                        isVisible = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pwd = s.toString().trim();
                String username = etUsername.getText().toString().trim();

                if (pwd.equals("")) {
                    tvClearPwd.setVisibility(View.GONE);
                } else {
                    tvClearPwd.setVisibility(View.VISIBLE);
                }

                if (username.equals("") || pwd.equals("")) {
                    if (isVisible) {
                        ObjectAnimator.ofFloat(btnLogin, "alpha", 1.0f, 0.0f).setDuration(500).start();
                        isVisible = false;
                    }
                } else if (!username.equals("") && !pwd.equals("")) {
                    if (!isVisible) {
                        ObjectAnimator.ofFloat(btnLogin, "alpha", 0.0f, 1.0f).setDuration(500).start();
                        isVisible = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnLogin.setOnClickListener(this);
        tvClearUsername.setOnClickListener(this);
        tvClearPwd.setOnClickListener(this);
    }

    public void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvClearUsername: {
                if (!etUsername.isFocused()) {
                    etUsername.requestFocus();
                }
                etUsername.setText("");
                break;
            }

            case R.id.tvClearPwd: {
                if (!etPwd.isFocused()) {
                    etPwd.requestFocus();
                }
                etPwd.setText("");
                break;
            }

            case R.id.btnLogin: {
                if (v.getAlpha() == 1.0f) {
                    Toast.makeText(LoginActivity.this, "登陆", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
