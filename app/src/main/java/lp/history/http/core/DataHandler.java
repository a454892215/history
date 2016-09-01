package lp.history.http.core;

import com.google.gson.Gson;

import lp.history.base.BaseEntity;

public class DataHandler {

    public static <T> T encapsulateData(String result, Class<T> type) {
        return  new Gson().fromJson(result, type);
    }
}
