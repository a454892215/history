package lp.history.record;

import android.content.Context;

import commom.utils.LogUtil;
import lp.history.record.listener.CallStateListener;
import lp.history.record.listener.PhoneStateChangeCallback;

public class CallRecorder {

    public static void  record(Context context){
        CallStateListener callStateListener = new  CallStateListener();
        callStateListener.initListener(context,new PhoneStateChangeCallbackImp());
    }

    private static  class  PhoneStateChangeCallbackImp implements PhoneStateChangeCallback {

       @Override
       public void onInComingOffHook(String incomingNumber) {
           LogUtil.i("===onInComingOffHook=====incomingNumber"+incomingNumber);
       }

       @Override
       public void onInComingEnd(String incomingNumber) {
           LogUtil.i("===onInComingEnd=========incomingNumber"+incomingNumber);
       }

       @Override
       public void onOutComingStart(String outNumber) {
           LogUtil.i("===onOutComingStart=======outNumber"+outNumber);
       }

       @Override
       public void onOutComingEnd(String outNumber) {
           LogUtil.i("===onOutComingEnd===========outNumber"+outNumber);
       }
   }
}
