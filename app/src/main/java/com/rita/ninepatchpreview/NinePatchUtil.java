package com.rita.ninepatchpreview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.util.Log;

/**
 * Created by rita on 2019/3/20.
 */

public class NinePatchUtil {

    private static final String TAG = "NinePatchUtil";

    public static NinePatch LoadNinePatchImageFromLocal(Resources res, String filePath) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap bmp = BitmapFactory.decodeFile(filePath, options);
            NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(res, bmp, bmp.getNinePatchChunk(), new Rect(), null);
            NinePatch ninePatch = new NinePatch(ninePatchDrawable, options.outWidth, options.outHeight);
            Log.d(TAG, "LoadNinePatchImageFromLocal - " + "\nfilePath:" + filePath + ninePatch.toString());
            return ninePatch;
        } catch (Exception e) {
            Log.e(TAG, "LoadNinePatchImageFromLocal - ", e);
        }
        return null;
    }

}
