package lp.history.record;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import commom.utils.LogUtil;
import commom.utils.ToastUtil;
public class PhoneCallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        LogUtil.e("=======action:" + action);
        String state = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
        String lastState = PhoneCallHelper.getInstance().lastState;
        if(lastState!=null&&lastState.equals(state)){
            return ;
        }
        PhoneCallHelper.getInstance().lastState = state;
        switch (action) {
            case Intent.ACTION_NEW_OUTGOING_CALL:
                PhoneCallHelper.getInstance().isOutNum = true;
                PhoneCallHelper.getInstance().outNum = intent.getExtras().getString(Intent.EXTRA_PHONE_NUMBER);
                onOutgoingCallStart(context);
                   LogUtil.i("action:"+"============1=============电话接收器 当播出电话时 outNum:"+PhoneCallHelper.getInstance().outNum  + " state:"+ state +" action:"+action);
                break;
            case Intent.ACTION_BOOT_COMPLETED:
                ToastUtil.makeShort("电话接收器 开机启动时");
                LogUtil.i("action:" + "电话接收器 开机启动时");
                break;
            case "android.intent.action.PHONE_STATE":
                onCallStateChanged(context, intent, state);
                break;
        }
    }

    private void onCallStateChanged(Context context, Intent intent, String state) {
        String inNum = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        LogUtil.i("======onCallStateChanged outNum:" + PhoneCallHelper.getInstance().outNum + " inMum:" + inNum + " state:" + state +
                " EXTRA_STATE_IDLE:" + TelephonyManager.EXTRA_STATE_IDLE + " isOutNum:" + PhoneCallHelper.getInstance().isOutNum);
        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
            PhoneCallHelper.getInstance().isOutNum = false;
            PhoneCallHelper.getInstance().outNum = "";
            PhoneCallHelper.getInstance().inNum = inNum;
            onIncomingCallStart(context);
        } else if (TelephonyManager.EXTRA_STATE_IDLE.equals(state) && PhoneCallHelper.getInstance().isOutNum) {
            onOutgoingCallEnd(context);
        } else if (TelephonyManager.EXTRA_STATE_IDLE.equals(state) && !PhoneCallHelper.getInstance().isOutNum) {
            onIncomingCallEnd(context);
        }
    }


    protected void onOutgoingCallStart(Context context) {
        LogUtil.i("====on=======播出了电话：号码是：" + PhoneCallHelper.getInstance().outNum);
    }

    protected void onOutgoingCallEnd(Context context) {
        LogUtil.i("=====on======播出电话结束：号码是:" + PhoneCallHelper.getInstance().outNum);
    }

    protected void onIncomingCallStart(Context context) {
        LogUtil.i("=====on=====有电话打进来========"+ PhoneCallHelper.getInstance().inNum);
    }

    protected void onIncomingCallEnd(Context context) {
        LogUtil.i("=====on===拨入电话结束，号码是："+ PhoneCallHelper.getInstance().inNum);
    }


}