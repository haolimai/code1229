package com.shaiing.code1229;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by natalie on 2015/12/29.
 */
public class RegisterActivity extends Activity {
    private EditText et_activity_register_username;
    private EditText et_activity_register_pwd;
    private Button btn_activity_register_kacha;
    private Button btn_activity_register_register;
    private RelativeLayout rl_activity_register_root;

    private Animation show;
    private Animation disappear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        initData();
        initEvents();

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        rl_activity_register_root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int heightDiff = rl_activity_register_root.getRootView().getHeight() - rl_activity_register_root.getHeight();

                        if (heightDiff > 100) {
//                            Log.v("TAG", "键盘弹出状态");
                            btn_activity_register_kacha.setVisibility(View.INVISIBLE);
                        } else {
//                            Log.v("TAG", "键盘收起状态");
                            btn_activity_register_kacha.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }

    //初始化控件
    public void initViews() {
        et_activity_register_username = (EditText) findViewById(R.id.et_activity_register_username);
        et_activity_register_pwd = (EditText) findViewById(R.id.et_activity_register_pwd);
        btn_activity_register_kacha = (Button) findViewById(R.id.btn_activity_register_kacha);
        btn_activity_register_register = (Button) findViewById(R.id.btn_activity_register_register);
        rl_activity_register_root = (RelativeLayout) findViewById(R.id.rl_activity_register_root);
    }

    //初始化事件
    public void initEvents() {
        et_activity_register_username.addTextChangedListener(new MyTextWatcher(et_activity_register_pwd, btn_activity_register_register,show,disappear));

        et_activity_register_pwd.addTextChangedListener(new MyTextWatcher(et_activity_register_username, btn_activity_register_register,show,disappear));

        btn_activity_register_kacha.setOnClickListener(new View.OnClickListener() {
            private boolean flag;

            @Override
            public void onClick(View v) {
                if (!flag) {
                    btn_activity_register_kacha.setText("重拍");
                    btn_activity_register_kacha.setBackgroundResource(R.drawable.round_sanlyv);
                    flag = true;
                } else {
                    btn_activity_register_kacha.setText("咔嚓");
                    btn_activity_register_kacha.setBackgroundResource(R.drawable.round_ningmenghuang);
                    flag = false;
                }
            }
        });
        
        btn_activity_register_register.setOnClickListener(new View.OnClickListener() {
            private Animation animation = AnimationUtils.loadAnimation(RegisterActivity.this,R.anim.btn_alpha_0_point_5_to_1);
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                Toast.makeText(RegisterActivity.this, "注册了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //初始化数据
    public void initData() {
        show  = AnimationUtils.loadAnimation(this,R.anim.btn_alpha_0_to_1);
        disappear = AnimationUtils.loadAnimation(this,R.anim.btn_alpha_1_to_0);
    }

}
