package com.shaiing.code1229.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.shaiing.code1229.R;

/**
 * Created by natalie on 2015/12/29.
 */
public class RegisterActivity extends Activity {
    private EditText et_username;
    private EditText et_pwd;

    private Button btn_kacha;
    private Button btn_register;

    private RelativeLayout rl_root;

    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initData();
        initViews();
        initEvents();
    }

    public void initViews() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_kacha = (Button) findViewById(R.id.btn_kacha);
        btn_register = (Button) findViewById(R.id.btn_register);
        rl_root = (RelativeLayout) findViewById(R.id.rl_root);
    }

    public void initEvents() {
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
                        ObjectAnimator.ofFloat(btn_register, "Alpha", 1.0f, 0.0f).setDuration(500).start();
                        flag = false;
                    }
                } else if (!username.equals("") && !pwd.equals("")) {
                    if (!flag) {
                        ObjectAnimator.ofFloat(btn_register, "Alpha", 0.0f, 1.0f).setDuration(500).start();
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
                        ObjectAnimator.ofFloat(btn_register, "Alpha", 1.0f, 0.0f).setDuration(500).start();
                        flag = false;
                    }
                } else if (!username.equals("") && !pwd.equals("")) {
                    if (!flag) {
                        ObjectAnimator.ofFloat(btn_register, "Alpha", 0.0f, 1.0f).setDuration(500).start();
                        flag = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_kacha.setOnClickListener(new View.OnClickListener() {
            private boolean flag;

            @Override
            public void onClick(View v) {
                if (!flag) {
                    btn_kacha.setText("重拍");
                    btn_kacha.setBackgroundResource(R.drawable.round_sanlyv);
                    flag = true;
                } else {
                    btn_kacha.setText("咔嚓");
                    btn_kacha.setBackgroundResource(R.drawable.round_ningmenghuang);
                    flag = false;
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rl_root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int heightDiff = rl_root.getRootView().getHeight() - rl_root.getHeight();
                        if (heightDiff > 100) {
                            //键盘弹出状态
                            btn_kacha.setVisibility(View.INVISIBLE);
                        } else {
                            //键盘未弹出状态
                            btn_kacha.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    public void initData() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
