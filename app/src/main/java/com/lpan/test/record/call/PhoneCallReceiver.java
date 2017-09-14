package com.lpan.test.record.call;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import commom.utils.LogUtil;

/**
 * 可不可能出现一个拨出的电话还没有挂 再拨出一个电话？？
 */
public class PhoneCallReceiver extends BroadcastReceiver {
   private static String  OutNumber = "";
    private static  long saveTime;
    @Override
    public void onReceive(Context context, Intent intent) {
       String action = intent.getAction();

        if(action.equals(Intent.ACTION_NEW_OUTGOING_CALL)){
            saveTime = System.currentTimeMillis();
            OutNumber = intent.getExtras().getString(Intent.EXTRA_PHONE_NUMBER);
            LogUtil.e("==============onReceive=========================OutNumber:" + OutNumber);
        }
    }

    /**
     * 只在拨出去的时候调用 然后自己保存 挂断就不能再调用 已经超时
     * @return
     */
   public static String getOutNumber(){
       long currentTime = System.currentTimeMillis();
       long dTime = currentTime-saveTime;
       LogUtil.e("==============getOutNumber====dTime:" + dTime+"  OutNumber："+OutNumber);
       if(dTime>1500){ //有效期为1.5秒 华为Plug6响应时间25-50  vivo Plug6 为90左右 中兴C 880S 响应时间为近1000毫秒
           return "";
       }
       return OutNumber;
   }
}