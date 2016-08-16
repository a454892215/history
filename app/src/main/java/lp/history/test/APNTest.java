package lp.history.test;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import commom.utils.LogUtil;

/**
 * Created by Administrator on 2016/8/14.
 */
public class APNTest {

    public static void testAPN(Context context){
        //获取当前apn
        Uri uri = Uri.parse("content://telephony/carriers/preferapn");
        Cursor cr = context.getContentResolver().query(uri, null, null, null, null);
        while(cr!=null && cr.moveToNext()){

            // APN id

            String id = cr.getString(cr.getColumnIndex("_id"));

            // APN name

            String apn = cr.getString(cr.getColumnIndex("apn"));

            LogUtil.i("id:"+id+"apn:"+apn);
        }

    }
}
