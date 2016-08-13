package lp.history.permission;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lp.history.utils.ToastUtil;

public  class PermissionHelper {
    /**
     * 此activity要注意内存泄漏
     */
   private static  PermissionActivity permissionActivity;
    private PermissionHelper(){
    }
    public static void setPermissionActivity( PermissionActivity activity){
        PermissionHelper.permissionActivity = activity;
    }
    public static void cancelPermissionActivity(){
        PermissionHelper.permissionActivity = null;
    }

    public static String[] filterPermissions(String[] permissions) {
        ArrayList<String> list = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(permissionActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                list.add(permission);
            }
        }
        LogUtil.i("has been filter permission:"+list+" "+list.size());
        return list.toArray(new String[list.size()]);
    }

    public static void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + permissionActivity.getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        ToastUtil.makeShort("请允许所有权限");
        permissionActivity.startActivity(intent);
    }

    public static boolean isOpenMonitor = true;
    static boolean isHasMonitorPermission = false;
    public static void monitorPermission() {
        if(isHasMonitorPermission){
            return;
        }
        isHasMonitorPermission = true;
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isOpenMonitor) {
                        String[] permissions =permissionActivity.getIntent().getStringArrayExtra("ask_for_permissions");
                        executeMonitor(permissions);
                        Thread.sleep(10 * 1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static void executeMonitor(String[] permissionsTask) {
        if (PermissionHelper.filterPermissions(permissionsTask).length == 0) {
              onAllPermissionGranted();
        } else {//有权限没有获取
            permissionActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    PermissionHelper.startAppSettings();
                }
            });
        }
    }

    public static void onAllPermissionGranted(){
        isOpenMonitor = false;
        LogUtil.i("all permissions has been granted");
        PermissionMonitorService.stop(permissionActivity);
        permissionActivity.finish();
    }
}
