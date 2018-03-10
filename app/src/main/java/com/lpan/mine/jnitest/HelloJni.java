package com.lpan.mine.jnitest;


public class HelloJni {
    static
    {
        System.loadLibrary("helloJni");
    }
    public static native String test();
}
