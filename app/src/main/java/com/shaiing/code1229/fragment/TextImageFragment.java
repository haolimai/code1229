package com.shaiing.code1229.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shaiing.code1229.R;
import com.shaiing.code1229.activity.TextImageActivity;

public class TextImageFragment extends Fragment implements View.OnClickListener {
    private EditText et_text;
    private TextView tv_arrow_right;

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getContext();

        //
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text_image, container, false);
        et_text = (EditText) view.findViewById(R.id.et_text);
        tv_arrow_right = (TextView) view.findViewById(R.id.tv_arrow_right);
        tv_arrow_right.setTypeface(font);

        //init event
        tv_arrow_right.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_arrow_right:
                Intent intent = new Intent(context, TextImageActivity.class);
                intent.putExtra("et_text", et_text.getText().toString());
                startActivity(intent);
                break;
        }
    }
}
