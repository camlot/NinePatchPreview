package com.rita.ninepatchpreview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by rita on 2019/3/20.
 */

public class NinePatchUtil {

    private static final String TAG = "NinePatchUtil";

    public static NinePatch LoadNinePatchImageFromLocal(Resources res, String filePath) {
        try {
            Log.d(TAG, "LoadNinePatchImageFromLocal - filePath:" + filePath);
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap bmp = BitmapFactory.decodeFile(filePath, options);
            NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(res, bmp, bmp.getNinePatchChunk(), new Rect(), null);
            NinePatch ninePatch = new NinePatch(ninePatchDrawable, options.outWidth, options.outHeight);
            Log.d(TAG, "LoadNinePatchImageFromLocal - " + ninePatch.toString());
            return ninePatch;
        } catch (OutOfMemoryError oom) {
            Log.e(TAG, "LoadNinePatchImageFromLocal - ", oom);
            ToastUtil.showToast(R.string.image_too_large_warning, Toast.LENGTH_SHORT);
        } catch (Exception e) {
            Log.e(TAG, "LoadNinePatchImageFromLocal - ", e);
        }
        return null;
    }

}
