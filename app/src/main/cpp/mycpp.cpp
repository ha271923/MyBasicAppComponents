//
// Created by ha271 on 2017/8/1.
//

#include <jni.h>
#include <sys/time.h>

#include <cstdint>
#include <cassert>
#include <string>
static class mycpp {

public:
    /* Return current time in milliseconds */
    static double now_ms(void) {
        struct timeval tv;
        gettimeofday(&tv, NULL);
        return tv.tv_sec * 1000. + tv.tv_usec / 1000.;
    }

};

mycpp mMyCpp;

extern "C" {

    JNIEXPORT jstring JNICALL Java_sample_hawk_com_mybasicappcomponents_jni_MyCppJNI_helloString(
            JNIEnv* env, jobject obj, jstring str){
        return env->NewStringUTF("Hello from JNI !");
    }

    JNIEXPORT jint JNICALL Java_sample_hawk_com_mybasicappcomponents_jni_MyCppJNI_multiply(
            JNIEnv *env, jobject thiz, jint a, jint b) {
        jint total = 0;
        total = a * b;
        return total;
    }

    JNIEXPORT jdouble JNICALL Java_sample_hawk_com_mybasicappcomponents_jni_MyCppJNI_getTimeOfDay(
            JNIEnv *env, jobject thiz) {
        return mMyCpp.now_ms();
    }

}
