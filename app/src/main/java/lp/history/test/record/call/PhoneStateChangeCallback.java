package lp.history.test.record.call;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface PhoneStateChangeCallback {
     void onInComingOffHook(String incomingNumber);
     void onInComingEnd(String incomingNumber);
     void onOutComingStart(String outNumber);
     void onOutComingEnd(String outNumber);
}
