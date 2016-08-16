package lp.history.permission;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import commom.utils.LogUtil;
import commom.utils.ToastUtil;

public class PermissionHelper {
    private static PermissionHelper permissionHelper;
    /**
     * 此activity要注意内存泄漏 必须调用cancelPermissionActivity()
     */
    private PermissionActivity permissionActivity;

    private PermissionHelper() {
    }

    public static PermissionHelper getInstance() {
        if (permissionHelper == null) {
            permissionHelper = new PermissionHelper();
        }
        return permissionHelper;
    }

    public void setPermissionActivity(PermissionActivity activity) {
        this.permissionActivity = activity;
    }

    public void cancelPermissionActivity() {
        this.permissionActivity = null;
        permissionHelper = null;
    }

    public String[] filterPermissions(String[] permissions) {
        ArrayList<String> list = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(permissionActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                list.add(permission);
            }
        }
        LogUtil.i("has been filter permission:" + list + " " + list.size());
        return list.toArray(new String[list.size()]);
    }

    public void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + permissionActivity.getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        ToastUtil.makeShort("请允许所有权限");
        permissionActivity.startActivity(intent);
    }

    public boolean isOpenMonitor = true;
    private boolean isHasMonitorPermission = false;

    public void monitorPermission() {
        if (isHasMonitorPermission) {
            return;
        }
        isHasMonitorPermission = true;
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isOpenMonitor) {
                        String[] permissions = permissionActivity.getIntent().getStringArrayExtra("ask_for_permissions");
                        executeMonitor(permissions);
                        Thread.sleep(10 * 1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void executeMonitor(String[] permissionsTask) {
        if (filterPermissions(permissionsTask).length == 0) {
            onAllPermissionGranted();
        } else {//有权限没有获取
            permissionActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    startAppSettings();
                }
            });
        }
    }

    public void onAllPermissionGranted() {
        isOpenMonitor = false;
        LogUtil.i("all permissions has been granted");
        PermissionMonitorService.stop(permissionActivity);
        permissionActivity.finish();
    }
}
