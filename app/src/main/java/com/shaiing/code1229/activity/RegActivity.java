package com.shaiing.code1229.activity;

import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shaiing.code1229.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class RegActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etUsername;
    private EditText etNewPwd;
    private Button btnContinue;

    private TextView tvClearUsername;
    private TextView tvClearNewPwd;
    private TextView tvIcAngleLeft;

    private ProgressBar progressBar;

    private boolean isVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        initView();
        initData();
        initEvent();

    }

    public void initView() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        etUsername = (EditText) findViewById(R.id.etUsername);
        etNewPwd = (EditText) findViewById(R.id.etNewPwd);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        tvClearUsername = (TextView) findViewById(R.id.tvClearUsername);
        tvClearUsername.setTypeface(font);
        tvClearNewPwd = (TextView) findViewById(R.id.tvClearNewPwd);
        tvClearNewPwd.setTypeface(font);
        tvIcAngleLeft = (TextView) findViewById(R.id.tvIcAngleLeft);
        tvIcAngleLeft.setTypeface(font);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void initEvent() {
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String username = s.toString().trim();
                String pwd = etNewPwd.getText().toString().trim();

                if (username.equals("")) {
                    tvClearUsername.setVisibility(View.GONE);
                } else {
                    tvClearUsername.setVisibility(View.VISIBLE);
                }

                if (username.equals("") || pwd.equals("")) {
                    if (isVisible) {
                        ObjectAnimator.ofFloat(btnContinue, "alpha", 1.0f, 0.0f).setDuration(500).start();
                        isVisible = false;
                    }
                } else if (!username.equals("") && !pwd.equals("")) {
                    if (!isVisible) {
                        ObjectAnimator.ofFloat(btnContinue, "alpha", 0.0f, 1.0f).setDuration(500).start();
                        isVisible = true;
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etNewPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pwd = s.toString().trim();
                String username = etUsername.getText().toString().trim();

                if (pwd.equals("")) {
                    tvClearNewPwd.setVisibility(View.GONE);
                } else {
                    tvClearNewPwd.setVisibility(View.VISIBLE);
                }

                if (username.equals("") || pwd.equals("")) {
                    if (isVisible) {
                        ObjectAnimator.ofFloat(btnContinue, "alpha", 1.0f, 0.0f).setDuration(500).start();
                        isVisible = false;
                    }
                } else if (!username.equals("") && !pwd.equals("")) {
                    if (!isVisible) {
                        ObjectAnimator.ofFloat(btnContinue, "alpha", 0.0f, 1.0f).setDuration(500).start();
                        isVisible = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnContinue.setOnClickListener(this);
        tvClearUsername.setOnClickListener(this);
        tvClearNewPwd.setOnClickListener(this);
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

            case R.id.tvClearNewPwd: {
                if (!etNewPwd.isFocused()) {
                    etNewPwd.requestFocus();
                }
                etNewPwd.setText("");
                break;
            }

            case R.id.btnContinue: {
                if (v.getAlpha() == 1.0f) {
                    String url = "http://101.200.166.175/PhalApi/Public/demo/";
                    AsyncTask<String, Void, Void> asyncTask = new AsyncTask<String, Void, Void>() {
                        @Override
                        protected void onPreExecute() {
                            btnContinue.setTextColor(getResources().getColor(R.color.tianqing));
                            progressBar.setVisibility(View.VISIBLE);
                        }

                        @Override
                        protected Void doInBackground(String... params) {
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            try {
                                URL url = new URL(params[0]);
                                URLConnection urlConnection = url.openConnection();
                                urlConnection.setDoOutput(true);
                                InputStream is = url.openStream();
                                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                                StringBuffer sb = new StringBuffer();
                                String str = null;
                                while ((str = br.readLine()) != null) {
                                    sb.append(str);
                                }

                                Log.d("aaa", sb.toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            btnContinue.setTextColor(getResources().getColor(R.color.xuese));
                            progressBar.setVisibility(View.GONE);
                        }
                    };
                    asyncTask.execute(url);


                    //Intent intent = new Intent(this, CheckUsernameActivity.class);
                    //startActivity(intent);
                }
                break;
            }
        }
    }
}
