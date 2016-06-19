package com.shaiing.code1229.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by mike on 2016/6/11.
 */
public class CommonPreferences {
    public static final String CURRENT_IMG_INDEX = "current_img_index";

    public static void setCurrentImgIndex(Context context, int index) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt(CURRENT_IMG_INDEX, index).apply();
    }

    public static int getCurrentImgIndex(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(CURRENT_IMG_INDEX, 0);
    }
}
