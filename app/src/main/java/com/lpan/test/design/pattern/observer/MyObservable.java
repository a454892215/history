package com.lpan.test.design.pattern.observer;

import java.util.Observable;

/**
 继承JDK中规范的被观察类Observable
 */
public class MyObservable extends Observable {

    public void test(){
        this.notifyObservers("哈哈");
    }
}
