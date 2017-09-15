package com.lpan.base;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.lpan.messagepush.XiaomiPushInitAssistant;


import commom.utils.ToastUtil;


public class AppApplication extends Application {
    public static Context appApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        appApplication = getApplicationContext();
        ToastUtil.init(this);
        new GlideBuilder(this)
                .setDiskCache(new InternalCacheDiskCacheFactory(this, 1024 * 1024 * 10));
        XiaomiPushInitAssistant.initXiaomiPush(getApplicationContext());
    }
}
