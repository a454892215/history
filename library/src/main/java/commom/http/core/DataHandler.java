package commom.http.core;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import commom.utils.LogUtil;
import commom.utils.ToastUtil;

public class DataHandler {

    public static <T> T encapsulateData(String result, Class<T> type) {
        T t = null ;
        try {
            t = new Gson().fromJson(result, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            ToastUtil.makeLong("数据处理异常");
            LogUtil.e("Data processing exception: "+result);
        }
        return  t;
    }
}
