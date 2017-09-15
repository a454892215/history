package com.lpan.message;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

import commom.utils.LogUtil;

/**
 * Created by Administrator on 2017/9/15.
 */

public class XiaomiPushInitAssistant {

    // user your appid the key.
    private static final String APP_ID = "2882303761517616773";
    // user your appid the key.
    private static final String APP_KEY = "5891761693773";
    // 此TAG在adb logcat中检索自己所需要的信息， 只需在命令行终端输入 adb logcat | grep
    // com.xiaomi.mipushdemo
    public static final String TAG = "xiaomi_push";
    private static XiaomiPushHandler xiaomiPushHandler = null;
    private static MessagePushActivity messagePushActivity = null;

    public  static void initXiaomiPush(Context context) {
        // 注册push服务，注册成功后会向DemoMessageReceiver发送广播
        // 可以从DemoMessageReceiver的onCommandResult方法中MiPushCommandMessage对象参数中获取注册信息
        if (shouldInit(context)) {
            MiPushClient.registerPush(context, APP_ID, APP_KEY);
        }

        LoggerInterface newLogger = new LoggerInterface() {

            @Override
            public void setTag(String tag) {
                // ignore
            }

            @Override
            public void log(String content, Throwable t) {
                Log.d(TAG, content, t);
            }

            @Override
            public void log(String content) {
                Log.d(TAG, content);
            }
        };
        Logger.setLogger(context, newLogger);
        if (xiaomiPushHandler == null) {
            xiaomiPushHandler = new XiaomiPushHandler();
        }
    }

    private static boolean shouldInit(Context context) {
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = context.getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    public static class XiaomiPushHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            String s = (String) msg.obj;
            if (messagePushActivity != null) {
                messagePushActivity.refreshLogInfo();
            }
            if (!TextUtils.isEmpty(s)) {
                //    Toast.makeText(context, s, Toast.LENGTH_LONG).show();
                LogUtil.d("==============llpp==========小米重构代码测试");
            }
        }
    }

    public static XiaomiPushHandler getHandler() {
        return xiaomiPushHandler;
    }

    public static void setMessagePushActivity(MessagePushActivity messagePushActivity_) {
        messagePushActivity = messagePushActivity_;
    }
}
