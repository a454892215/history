package commom.cache;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import commom.utils.LogUtil;

/**
 * Created by Liupan on 2017/9/16.
 */

public class DiskCacheHelper {


    public static void writeJsonToDisk(final String fileName, final String text, final Context context){
    //    text = " start 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 end";
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(fileName)||TextUtils.isEmpty(text)){
                    LogUtil.e("没有数据");
                    return ;
                }
                try {
                    File file = new File(context.getFilesDir(), fileName.replace(".","_").replace("/","_"));//
                    if(!file.exists()){
                        if(file.createNewFile()){
                            LogUtil.i("文件创建成功："+file.getAbsolutePath());
                        }else{
                            LogUtil.e("文件创建失败："+file.getAbsolutePath());
                        }
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(text.getBytes());
                    fileOutputStream.close();
                    LogUtil.d("文件 缓存成功 路径为=" + file.getAbsolutePath()+" text:"+text);
                } catch (Exception e) {
                    LogUtil.e(e.toString());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static String readJsonFromDisk(String fileName, Context context){
        File file = new File(context.getFilesDir(), fileName);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte [] buff = new byte[fileInputStream.available()];
           int len = fileInputStream.read(buff,0,fileInputStream.available());
            LogUtil.d(" 读取了缓存：文件路径：" + file.getAbsolutePath()+" len:"+len +" text:"+ new String(buff));
            fileInputStream.close();

        } catch (Exception e) {
            LogUtil.e("缓存读取异常"+e.toString());
            e.printStackTrace();
        }
        return null;
    }
}
