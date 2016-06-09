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
 * Created by natalie on 2015/12/29.
 */
public class CheckUsernameActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etEmail;
    private TextView tvClearEmail;
    private Button btnContinue;
    private TextView tvAngleLeft;

    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_username);

        initData();
        initViews();
        initEvents();
    }

    public void initViews() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        etEmail = (EditText) findViewById(R.id.etEmail);
        tvClearEmail = (TextView) findViewById(R.id.tvClearEmail);
        tvClearEmail.setTypeface(font);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        tvAngleLeft = (TextView) findViewById(R.id.tvIcAngleLeft);
        tvAngleLeft.setTypeface(font);
    }

    public void initEvents() {
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString().trim();

                if (email.equals("")) {
                    tvClearEmail.setVisibility(View.GONE);
                    if (flag) {
                        ObjectAnimator.ofFloat(btnContinue, "Alpha", 1.0f, 0.0f).setDuration(500).start();
                        flag = false;
                    }
                } else {
                    tvClearEmail.setVisibility(View.VISIBLE);
                    if (!flag) {
                        ObjectAnimator.ofFloat(btnContinue, "Alpha", 0.0f, 1.0f).setDuration(500).start();
                        flag = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnContinue.setOnClickListener(this);
        tvClearEmail.setOnClickListener(this);
    }

    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvClearEmail: {
                etEmail.setText("");
                break;
            }

            case R.id.btnContinue: {
                if (v.getAlpha() == 1.0f) {
                    Toast.makeText(CheckUsernameActivity.this, "用户名", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
