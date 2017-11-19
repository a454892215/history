package com.lpan.topnew;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by 刘攀
 */
public class TopEntity extends RealmObject {
    @PrimaryKey
    private static final String URL = "http://v.juhe.cn/toutiao/index?key=5ac63094e99467342f086967b71c2f5e&type=";
    //  private static final String [] paramsKey = {"month","day"};
    public String reason;
    public ResultBean result;
    public int error_code;

    public static class ResultBean extends RealmObject  {
        public String stat;
        public List<DataBean> data;

        public static class DataBean {
            public String title;
            public String date;
            public String author_name;
            public String thumbnail_pic_s;
            public String thumbnail_pic_s02;
            public String thumbnail_pic_s03;
            public String url;
            public String uniquekey;
            public String type;
            public String realtype;
        }
    }

    public static String getUrl(String newsType) {
        return URL+newsType;
    }
}
