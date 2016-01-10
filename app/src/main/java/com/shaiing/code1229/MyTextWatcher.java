package com.shaiing.code1229;

import android.animation.ObjectAnimator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by natalie on 2015/12/30.
 */
public class MyTextWatcher implements TextWatcher {
    private EditText et1;
    private EditText et2;
    private Button button;
    private boolean flag = false;

    public MyTextWatcher(EditText et1, EditText et2, Button button) {
        this.et1 = et1;
        this.et2 = et2;
        this.button = button;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String username = s.toString().trim();
        String pwd = et2.getText().toString().trim();

        if (username.equals("") || pwd.equals("")) {
            if (flag) {
                ObjectAnimator.ofFloat(button, "Alpha", 1.0f, 0.0f).setDuration(500).start();
                flag = false;
            }
        } else if (!username.equals("") && !pwd.equals("")) {
            if (!flag) {
                ObjectAnimator.ofFloat(button, "Alpha", 0.0f, 1.0f).setDuration(500).start();
                flag = true;
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
