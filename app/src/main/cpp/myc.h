//
// Created by ha271 on 2017/8/1.
//
#include<jni.h>

#ifndef MYBASICAPPCOMPONENTS_MYC_H
#define MYBASICAPPCOMPONENTS_MYC_H

    JNIEXPORT jstring JNICALL Java_sample_hawk_com_mybasicappcomponents_jni_MyCJNIActivity_helloString(
            JNIEnv *env, jobject obj, jstring str);

    JNIEXPORT jint JNICALL Java_sample_hawk_com_mybasicappcomponents_jni_MyCJNIActivity_multiply(
            JNIEnv *env, jobject thiz, jint a, jint b);

    JNIEXPORT jdouble JNICALL Java_sample_hawk_com_mybasicappcomponents_jni_MyCJNIActivity_getTimeOfDay(
            JNIEnv *env, jobject thiz);

#endif //MYBASICAPPCOMPONENTS_MYC_H
