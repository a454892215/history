package lp.history.test;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import commom.utils.LogUtil;

public class TestManager {
    public static void test(Context context){
        testPhoneState(context);
    }



    public static void testPermission(Activity activity){
        PermissionTest.testPermisisons(activity);
    }
    public static void testAPN(Activity activity){
        APNTest.testAPN(activity);
        LogUtil.i("test APN  success");
    }

    public static void testdeviceOwner(Activity context){
        boolean deviceOwnerApp = ((DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE))
                .isDeviceOwnerApp(context.getApplicationContext().getPackageName());
        LogUtil.i("LLpp==========deviceOwnerApp================ "+deviceOwnerApp);
    }

    public static void  testPhoneState(Context context){
       new  PhoneStateTest().testPhoneState(context);
    }


}
