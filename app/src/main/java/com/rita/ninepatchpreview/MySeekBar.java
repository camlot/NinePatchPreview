package com.rita.ninepatchpreview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

/**
 * Created by rita on 2019/3/20.
 */

public class MySeekBar extends android.support.v7.widget.AppCompatSeekBar {

    public MySeekBar(Context context) {
        super(context);
    }

    public MySeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        // do what you want
        return super.performClick();
    }



}
