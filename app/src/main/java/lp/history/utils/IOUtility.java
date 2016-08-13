package lp.history.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import org.xutils.common.util.LogUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2016/8/7.
 */
public class IOUtility {


    public static void writeToSd(String text,Activity activity) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录

            File sdFile = new File(sdCardDir, "aa测试.txt");
            checkPermission(activity);
            try {
                FileOutputStream fos = new FileOutputStream(sdFile);
                fos.write(text.getBytes());
                fos.close();
                ToastUtil.makeLong("信息写入SD卡成功");
            } catch (FileNotFoundException e) {
                ToastUtil.makeLong(e.toString());
                e.printStackTrace();
            } catch (IOException e) {
                ToastUtil.makeLong(e.toString());
                e.printStackTrace();
            }
        }
    }

     private static void checkPermission(Activity activity){
         LogUtil.i("WRITE_EXTERNAL_STORAGE:"+(ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                 == PackageManager.PERMISSION_GRANTED));

         LogUtil.i("READ_CONTACTS:"+(ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS)
                 == PackageManager.PERMISSION_GRANTED));

         LogUtil.i("WRITE_CONTACTS:"+(ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CONTACTS)
                 == PackageManager.PERMISSION_GRANTED));

         LogUtil.i("RECEIVE_SMS:"+(ContextCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_SMS)
                 == PackageManager.PERMISSION_GRANTED));

         LogUtil.i("READ_SMS:"+(ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS)
                 == PackageManager.PERMISSION_GRANTED));

         LogUtil.i("SEND_SMS:"+(ContextCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS)
                 == PackageManager.PERMISSION_GRANTED));

         LogUtil.i("READ_CALL_LOG:"+(ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CALL_LOG)
                 == PackageManager.PERMISSION_GRANTED));



         if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CONTACTS)
                 != PackageManager.PERMISSION_GRANTED) {
             //申请WRITE_EXTERNAL_STORAGE权限
             ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                     Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS,
                             Manifest.permission.RECEIVE_SMS,
                             Manifest.permission.READ_SMS,
                             Manifest.permission.SEND_SMS,
                             Manifest.permission.READ_CALL_LOG,},
                     11);

             ToastUtil.makeShort("没有有权限");
         }else{
             ToastUtil.makeShort("已经拥有权限");
         }
     }
    /**
     * 追加文件：使用FileWriter
     * @param content
     */
    public static void writeToSD( String content,Activity activity) {
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            checkPermission(activity);
            File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录
            FileWriter writer = new FileWriter(new File(sdCardDir,"bb测试.txt"),true);
            writer.write(content);
            writer.close();
            ToastUtil.makeShort("信息写入SD卡成功");
        } catch (IOException e) {
            ToastUtil.makeLong(e.toString());
            e.printStackTrace();
        }
    }
}
