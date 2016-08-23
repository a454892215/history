package lp.history.http.core;

import com.google.gson.Gson;

import lp.history.base.BaseEntity;

public class DataHandler {

    public static BaseEntity encapsulateData(String result, Class entityType) {
        return (BaseEntity) new Gson().fromJson(result, entityType);
    }
}
