package com.university.lab3;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

public class CanvasSaver {
    public static boolean save(DrawView drawView) {
        try {
            drawView.setDrawingCacheEnabled(true);
            Bitmap bitmap = drawView.getDrawingCache();

            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Pictures");
            boolean mkdirs;
            if (!file.exists()) {
                mkdirs = file.mkdirs();
                Log.i("filesaver", file.getAbsolutePath());
                if (!mkdirs) {
                    throw new Exception("Папка не создана");
                }
            }
            File f = new File(file.getAbsolutePath() + File.separator + "image_" + UUID.randomUUID() + ".png");
            FileOutputStream oStream = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, oStream);
            oStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
