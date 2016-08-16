package lp.history.base;

import android.app.Application;

import commom.utils.ToastUtil;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.init(this);
    }
}
