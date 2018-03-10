//
// Created by Administrator on 2018/3/10.
//
#include <jni.h>
#include "com_lpan_mine_jnitest_HelloJni.h"

JNIEXPORT jstring JNICALL Java_com_lpan_mine_jnitest_HelloJni_test
        (JNIEnv *env, jclass obj)
{
    return (*env)->NewStringUTF(env,"hello, I am from C");
}