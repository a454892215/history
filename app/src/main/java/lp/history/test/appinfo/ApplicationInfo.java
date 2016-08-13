package lp.history.test.appinfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import org.xutils.common.util.LogUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/10.
 */
public class ApplicationInfo  {


    /**
     * Collect installed app info list.
     *
     * @return the list
     */
    public static  List<Info.ApkInfo> collectInstalledAppInfo(Context context) {
        List<Info.ApkInfo> apps = new ArrayList();

        int retrieveFlags = PackageManager.GET_DISABLED_COMPONENTS | PackageManager.GET_PERMISSIONS;
        List<PackageInfo> packageInfoList = context.getPackageManager()
                .getInstalledPackages(retrieveFlags);
        int size = packageInfoList.size();
        LogUtil.i("========================LLpp:最初获取的app数目是："+size);
        for (PackageInfo appInfo:packageInfoList) {
            String appName  = appInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
          //  LogUtil.i("========================LLpp:最初获取的app名字是appName："+appName);

        }

//        List<PackageInfo> packageInfoList = AppUtil
//                .getInstalledPackages(MyManager.getAppContext().getPackageManager());
        for (PackageInfo packageInfo : packageInfoList) {
            if (checkUninstallable(packageInfo.applicationInfo,context)) {
                Info.ApkInfo app = parse(packageInfo,context);
                apps.add(app);
            }
        }
        LogUtil.i("========================LLpp:过滤后app数目是："+apps.size());
        return apps;
    }

    /**
     * Check uninstallable boolean.
     *
     * @param applicationInfo the application info
     * @return the boolean
     */
    public static boolean checkUninstallable(android.content.pm.ApplicationInfo applicationInfo,Context context) {
        String appName  = applicationInfo.loadLabel(context.getPackageManager()).toString();
        LogUtil.i("=sourceDir:"+applicationInfo.sourceDir+" appName:"+ appName );
        if (applicationInfo.sourceDir.contains("delapp") || applicationInfo.sourceDir.contains("dataapp")||
                applicationInfo.sourceDir.contains("preloadapks")||applicationInfo.sourceDir.contains("data/hw_init")) {

            LogUtil.e("第一次判断true=sourceDir:"+applicationInfo.sourceDir+" appName:"+ appName );
            return true;
        }

        if (!isSystemApp(applicationInfo)) {
            LogUtil.e("第二次判断tr=sourceDir:"+applicationInfo.sourceDir+" appName:"+ appName );
            return true;
        }
        return false;
    }

    /**
     * 判断应用是否为系统应用
     */
    public static boolean isSystemApp(android.content.pm.ApplicationInfo applicationInfo) {
        if (applicationInfo == null) {
            return false;
        }

        if ((applicationInfo.flags & android.content.pm.ApplicationInfo.FLAG_SYSTEM) > 0) {
            return true;
        }
        return false;
    }

    private static Info.ApkInfo parse(PackageInfo packageInfo,Context context) {
        Info.ApkInfo app = new Info.ApkInfo();
        app.appName = packageInfo.applicationInfo
                .loadLabel(context.getPackageManager())
                .toString();
        app.packageName = packageInfo.packageName;
        app.versionCode = packageInfo.versionCode;
        app.versionName = packageInfo.versionName;
        app.firstInstallTime = packageInfo.firstInstallTime;
        app.lastUpdateTime = packageInfo.lastUpdateTime;
        String[] requestedPermissions = packageInfo.requestedPermissions;
        app.requestedPermissions = requestedPermissions;
        app.size = new File(packageInfo.applicationInfo.sourceDir).length();

        return app;
    }

}
