package com.lpan.base;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import commom.utils.ToastUtil;

public class BaseActivity extends FragmentActivity {
    protected Context context = this;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ToastUtil.makeShort("用户允许了");
                // Permission Granted
            } else {
                // Permission Denied
                ToastUtil.makeShort("用户拒绝了");
            }
    }

    protected void replaceFragment(Fragment fragment, int viewId) {
        getSupportFragmentManager().beginTransaction().replace(viewId, fragment).commit();
    }
    protected void addFragment(Fragment fragment, int viewId) {
        getSupportFragmentManager().beginTransaction().add(viewId, fragment).commit();
    }
    protected void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }
    protected void hideFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }

}
