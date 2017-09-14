package com.lpan.test;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;

import java.lang.reflect.Method;

import commom.utils.LogUtil;
import commom.utils.SDCardUtils;
import com.lpan.test.appinfo.ApplicationInfo;

public class TestManager {

    public static void test(Context context) {
        testAppInfo(context);

        // testUsageStatsManager(context);
    }


    public void testInvokeAdimin(Context context) {
        LogUtil.i("=============start test DevicePolicyManager");
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        Class clazz = devicePolicyManager.getClass();
        try {

            Method m = clazz.getDeclaredMethod("setActiveAdmin", ComponentName.class, boolean.class);
            ComponentName policyReceiver = new ComponentName("lp.history_", "lp.history.test.device.DeviceOwnerReceiver");
            m.invoke(devicePolicyManager, policyReceiver, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testPermission(Activity activity) {
        PermissionTest.testPermisisons(activity);
    }

    public static void testAPN(Activity activity) {
        //  APNTest.testAPN(activity);

    }

    public static void testdeviceOwner(Activity context) {
        boolean deviceOwnerApp = ((DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE))
                .isDeviceOwnerApp(context.getApplicationContext().getPackageName());
        LogUtil.i("LLpp==========deviceOwnerApp================ " + deviceOwnerApp);
    }

    public static void testPhoneState(Context context) {
        new PhoneStateTest().testPhoneState(context);
    }

    public static void testUsageStatsManager(Context context) {
        //  UsageStatsManagerTest.test(context);
    }

    public static void testSDCardUtil(Context context) {
        SDCardUtils.testSD();
    }

    public static void testAppInfo(Context context) {
        ApplicationInfo.collectInstalledAppInfo(context);
    }
}
