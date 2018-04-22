package utils;

import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/23.
 */

public class Utis {
    public static String getTime() {
        Date dt = new Date();
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
        return matter1.format(dt);
    }
    /**
     * 返回app文件夹，在内存卡的一级目录下，以该应用名称建立的文件夹
     */
    public static String getAppFolder() {
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);//判断sd卡是否存在
        if(sdCardExist)
        {
            String path = Environment.getExternalStorageDirectory() + "/" +"Exercise" + "/";
            File f = new File(path);
            if(!f.exists()) f.mkdir();
            return  path;
        }else {
            return  null;
        }
    }
}
