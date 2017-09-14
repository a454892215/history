package commom.utils;

import android.app.Activity;
import android.os.Environment;

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


    /**
     * 追加文件：使用FileWriter
     * @param content
     */
    public static void writeToSD( String content,Activity activity) {
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
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
