package com.shaiing.code1229;

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
    private EditText editText;
    private Button button;
    private Animation show;
    private Animation disappear;

    public MyTextWatcher(EditText editText, Button button, Animation show, Animation disappear) {
        this.editText = editText;
        this.button = button;
        this.show = show;
        this.disappear = disappear;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String pwd = editText.getText().toString().trim();
        String username = s.toString().trim();

        if (username.equals("") || pwd.equals("")) {
            if (button.getVisibility() == View.INVISIBLE) {
                return;
            }
            button.setVisibility(View.INVISIBLE);
            button.startAnimation(disappear);
            return;
        }

        if (button.getVisibility() == View.INVISIBLE) {
            button.setVisibility(View.VISIBLE);
            button.startAnimation(show);
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
