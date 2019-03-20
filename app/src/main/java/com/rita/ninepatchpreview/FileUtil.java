package com.rita.ninepatchpreview;

import android.os.Environment;

import java.io.File;

/**
 * Created by rita on 2019/3/20.
 */

public class FileUtil {

    public static String externalStorageDir = Environment.getExternalStorageDirectory().getAbsolutePath();

    public static String getNinePatchRootPath() {
        return externalStorageDir + Config.DEFAULT_NINE_PATCH_PATH;
    }

    public static boolean isFileExists(String path) {
        File file = new File(path);
        return file.exists();
    }

}
