package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by swg on 2017/3/9.
 */

public class TimeUtiss {
    public static String getTime() {
        Date dt = new Date();
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return matter1.format(dt);
    }
    public static String getDate() {
        Date dt = new Date();
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
        return matter1.format(dt);
    }
    public static String getHours() {
        Date dt = new Date();
        SimpleDateFormat matter1 = new SimpleDateFormat("HH");
        return matter1.format(dt);
    }
    /*
    * 将时间戳转换为时间
    */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
