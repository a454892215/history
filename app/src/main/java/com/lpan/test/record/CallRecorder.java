package com.lpan.test.record;
import android.content.Context;
import commom.utils.LogUtil;
import com.lpan.test.record.call.CallStateListener;
import com.lpan.test.record.call.PhoneStateChangeCallback;

public class CallRecorder {

    public void record(Context context) {
        CallStateListener callStateListener = new CallStateListener();
        callStateListener.initListener(context, new PhoneStateChangeCallbackImp());
    }

    private class PhoneStateChangeCallbackImp implements PhoneStateChangeCallback {
        @Override
        public void onInComingOffHook(String incomingNumber) {
            LogUtil.i("===onInComingOffHook=====incomingNumber" + incomingNumber);
            startRecord();
        }

        @Override
        public void onInComingEnd(String incomingNumber) {
            LogUtil.i("===onInComingEnd=========incomingNumber" + incomingNumber);
            stopRecord();
        }

        @Override
        public void onOutComingStart(String outNumber) {
            LogUtil.i("===onOutComingStart=======outNumber" + outNumber);
            startRecord();
        }

        @Override
        public void onOutComingEnd(String outNumber) {
            LogUtil.i("===onOutComingEnd===========outNumber" + outNumber);
            stopRecord();
        }
    }

    private void startRecord() {
    }

    private void stopRecord() {
    }
}
