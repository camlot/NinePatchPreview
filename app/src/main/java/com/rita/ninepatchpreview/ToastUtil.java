package com.rita.ninepatchpreview;

import android.widget.Toast;

/**
 * Created by rita on 2019/3/20.
 */

public class ToastUtil {

    public static void showToast(String content, int duration) {
        Toast.makeText(MyApplication.getInstance().getApplicationContext(), content, duration).show();
    }

    public static void showToast(int content, int duration) {
        Toast.makeText(MyApplication.getInstance().getApplicationContext(), content, duration).show();
    }

}
