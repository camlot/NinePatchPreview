package com.rita.ninepatchpreview;

import android.graphics.drawable.NinePatchDrawable;

/**
 * Created by rita on 2019/3/20.
 */

public class NinePatch {

    private NinePatchDrawable mNinePatchDrawable;
    private int width;
    private int height;

    public NinePatch(NinePatchDrawable ninePatchDrawable, int width, int height) {
        mNinePatchDrawable = ninePatchDrawable;
        this.width = width;
        this.height = height;
    }

    public NinePatchDrawable getNinePatchDrawable() {
        return mNinePatchDrawable;
    }

    public void setNinePatchDrawable(NinePatchDrawable ninePatchDrawable) {
        mNinePatchDrawable = ninePatchDrawable;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "\nNinePatch{" +
                "mNinePatchDrawable=" + mNinePatchDrawable +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
