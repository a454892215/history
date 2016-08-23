package lp.history.test;
import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import commom.utils.LogUtil;
import lp.history.test.record.call.PhoneCallReceiver;

public class PhoneStateTest {
    private String outNumber = "";
    private String inNumber = "";
    public  void  testPhoneState(Context context){
        //获取电话服务  
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        // 手动注册对PhoneStateListener中的listen_call_state状态进行监听
        manager.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
    }
    private void onInComingOffHook(String incomingNumber){
        LogUtil.i("===========onInComingOffHook==============:"+incomingNumber);
    }
    private void onInComingEnd(String incomingNumber){
        LogUtil.i("===========onInComingEnd==============:"+incomingNumber);
        this.inNumber = "";
    }
    private void onOutComingStart(){
         outNumber = PhoneCallReceiver.getOutNumber();
        LogUtil.i("===========onOutComingStart==============outNumber:"+outNumber);
    }
    private void onOutComingEnd(String outNumber){
        this.outNumber = "";
        LogUtil.i("===========onOutComingEnd==============outNumber:"+outNumber);
    }

     class  MyPhoneStateListener extends PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            LogUtil.d(" incomingNumber:"+incomingNumber+"  state:"+state);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE://挂断，或者空闲  华为手机 挂断时 incomingNumber为空
                    if(!"".equals(outNumber)){   //如果拨出的号码不为空
                        onOutComingEnd(outNumber);
                    }else if(!"".equals(PhoneStateTest.this.inNumber)){ //如果拨入的的号码不为空
                        onInComingEnd(PhoneStateTest.this.inNumber);
                    }else{
                        LogUtil.i("======IDLE=======拨出拨入都为空==========");//如果拨入电话未接 挂断incomingNumber为空
                    }
                    break;
                case TelephonyManager.CALL_STATE_RINGING: //响铃
                    LogUtil.d("RINGING  incomingNumber:"+incomingNumber);
                    PhoneStateTest.this.inNumber = incomingNumber;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK://接通或者当incomingNumber返回空字符串表示拨出电话 等待对方摘机 。拨出电话目前没有办法判断开始通话
                                                                //在华为PLug x6上 当拨入号码接听时 incomingNumber不能获取 但是在vivo plugX6手机上 拨出拨入都会有incomingNumber
                    LogUtil.d("OFF HOOK  incomingNumber:"+incomingNumber);
                   if(PhoneStateTest.this.inNumber!=""){
                       onInComingOffHook(PhoneStateTest.this.inNumber);
                   }else{
                       onOutComingStart();
                   }
                    break;
                default:
                    LogUtil.d("stater:"+state+ "  incomingNumber:"+incomingNumber);
                    break;
            }
        }
    }
}
