package com.lpan.histoday.entity;

import java.util.List;

/**
 * _id : 15990606
 * content : 在417年....
 * day : 6
 * des : 在417年前的今天，....
 * lunar :
 * month : 6
 * pic : http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201106/6/CC221544255.jpg
 * title : 西班牙画家蒂埃哥·委拉士开兹出生
 * year : 1599
 */
public class HistoryDetailEntity  {
   public String URL = "http://api.juheapi.com/japi/tohdet?v=1.0&key=9cbfe2233f0c827339ef83a180a88654&id=" ;

    public String error_code;
    public String reason;
    public List<ResultBean> result;

    public static class ResultBean {
        public String _id;
        public String content;
        public int day;
        public String des;
        public String lunar;
        public int month;
        public String pic;
        public String title;
        public int year;
    }
  public String getUrl(String id){
      return URL+id;
  }

}
