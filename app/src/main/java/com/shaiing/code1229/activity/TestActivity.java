package com.shaiing.code1229.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.shaiing.code1229.R;

public class TestActivity extends AppCompatActivity {
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        iv = (ImageView) findViewById(R.id.iv);
        Intent intent = getIntent();
        String p = intent.getStringExtra("pic");
        Bitmap bitmap = BitmapFactory.decodeFile(p);
        iv.setImageBitmap(bitmap);
    }
}
