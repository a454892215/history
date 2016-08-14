package lp.history.permission;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import org.xutils.common.util.LogUtil;
import java.util.Arrays;
public class PermissionActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] permissions = getIntent().getStringArrayExtra("ask_for_permissions");
        LogUtil.i("传递过来的权限是：" + Arrays.toString(permissions) + " 长度：" + permissions.length);
        PermissionHelper.getInstance().setPermissionActivity(this);
        String[] hasFilteredPermissions = PermissionHelper.getInstance().filterPermissions(permissions);
        requestPermission(hasFilteredPermissions);
    }

    private void requestPermission(String[] permissions) {
        boolean isMinSdkM = Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
        if (isMinSdkM || permissions.length == 0) {
            PermissionHelper.getInstance().onAllPermissionGranted();
            return;
        }
        ActivityCompat.requestPermissions(this, permissions, 1);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < grantResults.length; i++) {
            boolean isTip = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                if (isTip) {
                    requestPermission(PermissionHelper.getInstance().filterPermissions(permissions));
                } else {
                    PermissionMonitorService.start(this);
                }
                return;
            }
        }
        PermissionHelper.getInstance().onAllPermissionGranted();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PermissionHelper.getInstance().cancelPermissionActivity();
        PermissionMonitorService.stop(this);
        LogUtil.i("========onDestroy===========");
    }

    @Override
    public void onBackPressed() {
    }
}