package lp.history.test;

import android.content.Context;
import java.util.List;
import commom.utils.LogUtil;
import lp.history.test.appinfo.ApplicationInfo;
import lp.history.test.appinfo.Info;

/**
 * Created by Administrator on 2016/8/14.
 */
public class InstalledAppTest {

    /**
     * 测试预装和用户安装的app
     */
    public static void testPreinstalledAppsAndUserApp(Context context){
        List<Info.ApkInfo> apkInfos = ApplicationInfo.collectInstalledAppInfo(context);
        int size = apkInfos.size();
        LogUtil.i("================size:"+size);
        for (Info.ApkInfo app: apkInfos) {
            String appName = app.appName;
            LogUtil.i("=============appName:"+appName);
        }
    }
}
