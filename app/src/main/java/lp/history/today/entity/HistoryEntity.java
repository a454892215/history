package lp.history.today.entity;

import java.util.Calendar;
import java.util.List;
import commom.utils.LogUtil;
import commom.utils.TimeUtils;
import lp.history.base.BaseEntity;

/**
 * error_code : 0
 * reason : 请求成功！
 * result : [{}]*
 * _id : 14920803
 * day : 3
 * des : 在524年前的今天，1492年8月3日 (农历七月十一)，哥伦布第一次远航。
 * lunar :
 * month : 8
 * pic : http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201108/3/10113339812.jpg
 * title : 哥伦布第一次远航
 * year : 1492
 */
public class HistoryEntity extends BaseEntity {

    public HistoryEntity() {
    }
   private static final String URL = "http://api.juheapi.com/japi/toh?v=1.0&key=9cbfe2233f0c827339ef83a180a88654";
    //public String URL = "http://www.baidu.com";
    private static final String [] paramsKey = {"month","day"};
    public int error_code;
    public String reason;
    public List<ResultBean>  result;
    public static class ResultBean {
        public String _id;
        public int day;
        public String des;
        public String lunar;
        public int month;
        public String pic;
        public String title;
        public int year;
        public String toString() {
            return "ResultBean{" +
                    "_id='" + _id + '\'' +
                    ", day=" + day +
                    ", des='" + des + '\'' +
                    ", lunar='" + lunar + '\'' +
                    ", month=" + month +
                    ", pic='" + pic + '\'' +
                    ", title='" + title + '\'' +
                    ", year=" + year +
                    '}';
        }
    }

    private static Integer[] currentTime = new Integer[2];
    public static String getUrl(boolean isLoadMore){
        Integer[]  requestParams;
        if(isLoadMore){
            requestParams = getLastDay();
        }else{
            requestParams  = getTodayMonthAndDay();
        }
        String url = URL;
           for (int i = 0;i<requestParams.length;i++){
               url+= "&"+paramsKey[i]+"="+requestParams[i];
           }
     return url;
    }

    public static Integer[]  getLastDay(){
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.MONTH,currentTime[0]-1);//notice： -1
        instance.set(Calendar.DAY_OF_MONTH,currentTime[1]);
        instance.add(Calendar.DAY_OF_MONTH,-1);
        LogUtil.i("=======getLastDay======:"+ TimeUtils.getFormedDate(instance.getTime()));
         currentTime[1] = instance.get(Calendar.DAY_OF_MONTH);
         currentTime[0] = instance.get(Calendar.MONTH)+1;//notice： +1
        return currentTime;
    }
    public static Integer[] getTodayMonthAndDay(){
        Integer[]  arr = new Integer[2];
        Calendar instance = Calendar.getInstance();
        LogUtil.i("=======getTodayMonthAndDay======:"+ TimeUtils.getFormedDate(instance.getTime()));
        int day = instance.get(Calendar.DAY_OF_MONTH);
        int month = instance.get(Calendar.MONTH);
        currentTime[0] = arr[0] = month+1; //notice： +1
        currentTime[1] = arr[1] = day;
        LogUtil.i("month:"+month+" day:"+day);
        return arr;
    }
}
