package lp.history.test.record.call;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import commom.utils.LogUtil;

public class CallStateListener {
    private String outNumber = "";
    private String inNumber = "";
    private PhoneStateChangeCallback phoneStateChangeCallback;

    public void initListener(Context context, PhoneStateChangeCallback phoneStateChangeCallback) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        manager.listen(new MyPhoneStateListener(), android.telephony.PhoneStateListener.LISTEN_CALL_STATE);
        this.phoneStateChangeCallback = phoneStateChangeCallback;
    }

    private class MyPhoneStateListener extends android.telephony.PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            LogUtil.d(" incomingNumber:" + incomingNumber + "  state:" + state);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE://挂断，或者空闲  华为手机 挂断时 incomingNumber为空
                    onIDLE();
                    break;
                case TelephonyManager.CALL_STATE_RINGING: //响铃
                    LogUtil.d("RINGING  incomingNumber:" + incomingNumber);
                    inNumber = incomingNumber;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK://接通或者当incomingNumber返回空字符串表示拨出电话 等待对方摘机 。拨出电话目前没有办法判断开始通话
                    LogUtil.d("CALL_STATE_OFF HOOK  inNumber:" + inNumber);
                    onOffHook();                                 //在华为PLug x6上 当拨入号码接听时 incomingNumber不能获取 但是在vivo plugX6手机上 拨出拨入都会有incomingNumber
                    break;
                default:
                    LogUtil.d("stater:" + state + "  incomingNumber:" + incomingNumber);
                    break;
            }
        }
    }

    /**
     * 来电接通或者拨打
     */
    private void onOffHook() {
        if (!TextUtils.isEmpty(inNumber)) {
            phoneStateChangeCallback.onInComingOffHook(inNumber);
        } else {
            outNumber = PhoneCallReceiver.getOutNumber();
            phoneStateChangeCallback.onOutComingStart(outNumber);
        }
    }

    /**
     * 挂断或者空闲
     */
    private void onIDLE() {
        if (!TextUtils.isEmpty(outNumber)) {   //如果拨出的号码不为空
            phoneStateChangeCallback.onOutComingEnd(outNumber);
            outNumber = "";
        } else if (!TextUtils.isEmpty(inNumber)) { //如果拨入的的号码不为空
            phoneStateChangeCallback.onInComingEnd(inNumber);
            inNumber = "";
        } else {
            LogUtil.i("======IDLE=======拨出拨入都为空==========");
        }
    }
}
