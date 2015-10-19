package com.Lee.ExchangeGo;

import android.os.Environment;

import java.io.File;

/**
 * Created by leithlee on 15/10/13.
 */
public class FileHelper {

    public static String filepath="ExchangeGo/Image";

    public static File getPicBaseFile(){
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                File file=new File(Environment.getExternalStorageDirectory(),filepath);
                if (!file.exists())
                    file.mkdirs();
                    return file;

            }
        else

        return null;
    }
}
