package lp.history;

import android.app.Application;

import org.xutils.x;

import lp.history.utils.ToastUtil;


public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        ToastUtil.init(this);
        x.Ext.setDebug(true);
    }
}
