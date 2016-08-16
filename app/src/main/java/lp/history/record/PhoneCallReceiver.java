package lp.history.record;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import commom.utils.LogUtil;
import commom.utils.ToastUtil;


public class PhoneCallReceiver extends BroadcastReceiver {
    private int lastCallState = TelephonyManager.CALL_STATE_IDLE;
    private boolean isIncoming = false;
    private static String contactNum;
    public PhoneCallReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
      String  action = intent.getAction();
        LogUtil.i("action:"+action);
        switch (action){
            case Intent.ACTION_NEW_OUTGOING_CALL:
                ToastUtil.makeShort("电话接收器 当播出电话时");
                LogUtil.i("action:"+"电话接收器 当播出电话时");
                break;
            case Intent.ACTION_BOOT_COMPLETED:
                ToastUtil.makeShort("电话接收器 开机启动时");
                LogUtil.i("action:"+"电话接收器 开机启动时");
                break;
             default:
                 ToastUtil.makeShort("电话接收器 电话状态改变");
                 LogUtil.i("action:"+"电话接收器 电话状态改变");
                break;
        }






















        //如果是去电
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            contactNum = intent.getExtras().getString(Intent.EXTRA_PHONE_NUMBER);
        } else {
            String state = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String phoneNumber = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            int stateChange;
            if (TelephonyManager.EXTRA_STATE_IDLE.equals(state)) {
                //空闲状态
                //  stateChange = TelephonyManager.CALL_STATE_IDLE;
                if (isIncoming) {
                    onIncomingCallEnded(context, phoneNumber);
                } else {
                    onOutgoingCallEnded(context, phoneNumber);
                }
            } else if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(state)) {
                //摘机状态
                //  stateChange = TelephonyManager.CALL_STATE_OFFHOOK;
                if (lastCallState != TelephonyManager.CALL_STATE_RINGING) {
                    //如果最近的状态不是来电响铃的话，意味着本次通话是去电
                    isIncoming = false;
                    onOutgoingCallStarted(context, phoneNumber);
                } else {
                    //否则本次通话是来电
                    isIncoming = true;
                    onIncomingCallAnswered(context, phoneNumber);
                }
            } else if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
                //来电响铃状态
                stateChange = TelephonyManager.CALL_STATE_RINGING;
                lastCallState = stateChange;
                onIncomingCallReceived(context, contactNum);
            }
        }

    }


/*    protected void onIncomingCallStarted(Context context, String number) {
        Toast.makeText(context, "Incoming call is started"+number, Toast.LENGTH_LONG).show();
        context.startService(new Intent(context, AudioRecorderService.class));

    }*/

    protected void onOutgoingCallStarted(Context context, String number) {
        Toast.makeText(context, "Outgoing call is started" + number, Toast.LENGTH_LONG).show();
        context.startService(new Intent(context, AudioRecorderService.class));
    }

    protected void onOutgoingCallEnded(Context context, String number) {
        Toast.makeText(context, "Outgoing call is ended" + number, Toast.LENGTH_LONG).show();
        context.startService(new Intent(context, AudioRecorderService.class));
    }


    protected void onIncomingCallEnded(Context context, String number) {
        Toast.makeText(context, "Incoming call is ended" + number, Toast.LENGTH_LONG).show();
        context.startService(new Intent(context, AudioRecorderService.class));
    }


    protected void onIncomingCallReceived(Context context, String number) {
        Toast.makeText(context, "Incoming call is received" + number, Toast.LENGTH_LONG).show();
    }

    protected void onIncomingCallAnswered(Context context, String number) {
        Toast.makeText(context, "Incoming call is answered" + number, Toast.LENGTH_LONG).show();
    }
}