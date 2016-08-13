package lp.history.module.entity;

import java.util.List;
import java.util.Map;

import lp.history.entity.BaseEntityImpl;

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
public class HistoryEntity extends BaseEntityImpl {

    public HistoryEntity() {
    }

    public String URL = "http://api.juheapi.com/japi/toh?v=1.0&month=6&day=6&key=9cbfe2233f0c827339ef83a180a88654";
    //public String URL = "http://www.baidu.com";
    public Map params;
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

    }

    public void setParams(Map params) {
        this.params = params;
    }

    public Map getParams() {
        return params;
    }
}
