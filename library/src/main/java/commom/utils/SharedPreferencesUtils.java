package commom.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by LiuPan on 2017/9/16.
 */

public class SharedPreferencesUtils {
private static final String fileName = "sp_file";

    public static String get(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
       return sharedPreferences.getString(key, "");
    }

    public static void put(Context context,String key,String value){
        SharedPreferences.Editor editor = context.getSharedPreferences(fileName,Context.MODE_PRIVATE).edit();
        editor.putString(key,value);
        editor.commit();
    }
}
