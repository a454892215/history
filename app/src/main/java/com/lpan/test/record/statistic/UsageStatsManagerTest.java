package com.lpan.test.record.statistic;

import android.annotation.TargetApi;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import commom.utils.LogUtil;
import commom.utils.TimeUtils;

public class UsageStatsManagerTest {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    public static List<StatisticAppEntity> getData(Context context) {
        Long[] intervalTime = UsageStatsManagerTest.getIntervalTime(context);
        LogUtil.i("time of doing statistic app usage tate :" + TimeUtils.getFormedDate(intervalTime[0]) + " end time ：" + intervalTime[1]);
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        List<UsageStats> queryUsageStats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, intervalTime[0], intervalTime[1]);
        if (queryUsageStats == null || queryUsageStats.isEmpty()) {
            LogUtil.i("no app statistic data");
            return null;
        }
        return getData(context, queryUsageStats);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    private static ArrayList<StatisticAppEntity> getData(Context context, List<UsageStats> queryUsageStats) {
        ArrayList<StatisticAppEntity> arr = new ArrayList();
        for (UsageStats usageStats : queryUsageStats) {
            int mLaunchCount = 0;
            StatisticAppEntity statisticAppEntity = new StatisticAppEntity();
            String packageName = usageStats.getPackageName();
            statisticAppEntity.AppName = getAppNameByPackageName(context, packageName);
            statisticAppEntity.mLaunchCount = getLaunchCount(usageStats, mLaunchCount);
            statisticAppEntity.totalTimeInForeground = usageStats.getTotalTimeInForeground() / 1000 / 60;
            statisticAppEntity.firstTimeStamp = usageStats.getFirstTimeStamp();
            statisticAppEntity.lastTimeStamp  = usageStats.getLastTimeStamp();
            statisticAppEntity.lastTimeUsed  = usageStats.getLastTimeUsed();
            arr.add(statisticAppEntity);
        }
        return arr;
    }

    private static int getLaunchCount(UsageStats usageStats, int mLaunchCount) {
        try {
            mLaunchCount = usageStats.getClass().getDeclaredField("mLaunchCount").getInt(usageStats);
        } catch (Exception e) {
           LogUtil.i(""+e.toString());
        }
        return mLaunchCount;
    }

    /**
     * time of get statistic data  of app usage rate
     */
    private static Long[] getIntervalTime(Context context) {
        Long[] time = new Long[2];
       // ZteManager.getInstance(context).allowGetUsageStats(context.getPackageName());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        time[1] = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        time[0] = calendar.getTimeInMillis();
        return time;
    }

    /**
     * get app name by package name
     */
    private static String getAppNameByPackageName(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        String name = packageName;
        try {
            name = pm.getApplicationLabel(
                    pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA)).toString();

        } catch (Exception e) {
            LogUtil.i("fail to get app Name:" + name);
        }
        return name;
    }

}
