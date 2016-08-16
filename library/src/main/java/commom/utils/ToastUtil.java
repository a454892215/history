package commom.utils;

import android.app.Application;
import android.widget.Toast;


public class ToastUtil {

    private static Application myContext;

    public static void makeLong(String str) {
        Toast.makeText(myContext, str, Toast.LENGTH_LONG).show();
    }

    public static void makeShort(String str) {
        Toast.makeText(myContext, str, Toast.LENGTH_SHORT).show();
    }

    public static void init(Application context) {
        myContext = context;
    }
}
