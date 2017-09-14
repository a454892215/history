package com.lpan.test.design.pattern.observer;

import java.util.Observable;
import java.util.Observer;

import commom.utils.LogUtil;

/**
 继承JDK中规范的观察者类Observer
 */
public  class MyObserver implements Observer {
    @Override
    public void update(Observable observable, Object data) {
        LogUtil.i(String.format(" update data is:%s", data));


    }
}
