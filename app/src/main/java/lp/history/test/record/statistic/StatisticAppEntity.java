package lp.history.test.record.statistic;


public class StatisticAppEntity {
    public String AppName;
    public long totalTimeInForeground;
    public long firstTimeStamp;
    public long lastTimeStamp;
    public long lastTimeUsed;
    public int mLaunchCount;

    @Override
    public String toString() {
        return "StatisticAppEntity{" +
                "AppName='" + AppName + '\'' +
                ", totalTimeInForeground=" + totalTimeInForeground +
                ", firstTimeStamp=" + firstTimeStamp +
                ", lastTimeStamp=" + lastTimeStamp +
                ", lastTimeUsed=" + lastTimeUsed +
                ", mLaunchCount=" + mLaunchCount +
                '}';
    }
}
