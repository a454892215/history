package com.lpan.base;

import android.app.Application;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.lpan.messagepush.XiaomiPushInitAssistant;


import commom.utils.ToastUtil;


public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.init(this);
        new GlideBuilder(this)
                .setDiskCache(new InternalCacheDiskCacheFactory(this, 1024 * 1024 * 10));
        XiaomiPushInitAssistant.initXiaomiPush(getApplicationContext());
    }
}
