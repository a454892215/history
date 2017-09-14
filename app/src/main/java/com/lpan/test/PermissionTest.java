package com.lpan.test;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;

import com.lpan.test.permission.PermissionActivity;

/**
 * Created by Administrator on 2016/8/14.
 */
public class PermissionTest {

    /**
     * 测试权限
     */
    public static void testPermisisons(Activity activity) {
        String[] permissions = new String[]{
//            Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECEIVE_MMS,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.SEND_SMS,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.PROCESS_OUTGOING_CALLS,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                //              Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.READ_CALENDAR,
//            Manifest.permission.WRITE_CALENDAR,
//            Manifest.permission.CAMERA,
//            Manifest.permission.GET_ACCOUNTS,

//            Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.WRITE_CALL_LOG,
//            Manifest.permission.ADD_VOICEMAIL,
//            Manifest.permission.USE_SIP,
//            Manifest.permission.BODY_SENSORS,
//            Manifest.permission.RECEIVE_WAP_PUSH,
        };

        //  PermissionMonitorService.start(this,permissions);
        Intent intent = new Intent(activity, PermissionActivity.class);
        intent.putExtra("ask_for_permissions",permissions);
        activity.startActivity(intent);
    }

}
