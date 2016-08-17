package lp.history.record;

import java.util.HashMap;

/**
 * 因为PhoneCallReceiver每次回调都是新的PhoneCallReceiver对象
 * 难以存储成员变量 故加PhoneCallHelper单例模式以存储来电去电的相关状态
 */
public class PhoneCallHelper {


    private static PhoneCallHelper phoneCallHelper;
    public  boolean isOutNum = false;
    public  boolean isInNum = false;
    public  String outNum;
    public HashMap<String,String> inNums ;
    public  String lastState ="";
    private PhoneCallHelper() {
    }

    public static PhoneCallHelper getInstance() {
        if (phoneCallHelper == null) {
            phoneCallHelper = new PhoneCallHelper();
        }
        return phoneCallHelper;
    }

}
