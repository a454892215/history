package lp.history.base;

import android.app.Application;
import android.text.Editable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskCacheAdapter;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;

import commom.utils.ToastUtil;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.init(this);
        new GlideBuilder(this)
                .setDiskCache(new InternalCacheDiskCacheFactory(this, 1024 * 1024 * 10));

    }
}
