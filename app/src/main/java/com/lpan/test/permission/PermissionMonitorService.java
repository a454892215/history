package com.lpan.test.permission;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import commom.utils.LogUtil;

public class PermissionMonitorService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public static void start(Context context){
        Intent intent = new Intent(context,PermissionMonitorService.class);
        context.startService(intent);
    }
    public static void stop(Context context){
        Intent intent = new Intent(context,PermissionMonitorService.class);
        context.stopService(intent);
    }
    @Override
    public void onCreate() {
        super.onCreate();
            LogUtil.i("LLpp======onCreate===========权限监控服务=============");
        PermissionHelper.getInstance().monitorPermission();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (null == intent) {
            if (startId % 3 == 0) {
                startService(new Intent(this, this.getClass()));
                return START_NOT_STICKY;
            }
        }
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PermissionHelper.getInstance().isOpenMonitor = false;
        LogUtil.i("=============监控服务被停止================");
    }
}
