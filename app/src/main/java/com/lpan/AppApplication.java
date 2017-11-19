package com.lpan;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.lpan.messagepush.XiaomiPushInitAssistant;


import commom.utils.ToastUtil;
import io.realm.Realm;


public class AppApplication extends Application {
    public static Context appApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        appApplication = getApplicationContext();
        ToastUtil.init(this);
        Realm.init(this);
        new GlideBuilder(this)
                .setDiskCache(new InternalCacheDiskCacheFactory(this, 1024 * 1024 * 10));
        XiaomiPushInitAssistant.initXiaomiPush(getApplicationContext());
    }
}
