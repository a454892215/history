package lp.history.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import commom.utils.ToastUtil;
import lp.history.R;

public class BaseActivity extends Activity {
    protected Context context = this;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode,grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == 11) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ToastUtil.makeShort("用户允许了");
                // Permission Granted
            } else {
                // Permission Denied
                ToastUtil.makeShort("用户拒绝了");
            }
        }
    }

    protected void setFragment(Fragment fragment,int viewId)
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(viewId, fragment);
        transaction.commit();
    }


}
