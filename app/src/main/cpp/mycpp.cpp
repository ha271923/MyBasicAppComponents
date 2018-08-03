//
// Created by ha271 on 2017/8/1.
//

#include <jni.h>
#include <sys/time.h>

#include <cstdint>
#include <cassert>
#include <string>
#include <android/Log.h>

#define TAG "myc" //
#define ALOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) //
#define ALOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__)  //
#define ALOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__)  //
#define ALOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) //
#define ALOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) //

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

    JNIEXPORT jstring JNICALL Java_sample_hawk_com_mybasicappcomponents_jni_MyCppJNI_JAVAtoJNI(
            JNIEnv* env, jobject thiz, jstring str){
        return env->NewStringUTF("Output from JNI !");
    }

    // C and Cpp diff at:
    // .C   is   (*env)->FindClass(env, ...)
    // .Cpp is     env->FindClass(...)
    JNIEXPORT jstring JNICALL Java_sample_hawk_com_mybasicappcomponents_jni_MyCppJNI_JAVAtoJNIcallbackJAVA(
            JNIEnv* env, jobject thiz, jstring inputString){
        ALOGD("JNI: input= %s",inputString);

        // setup callback api +++
        jclass clazz = env->FindClass("sample/hawk/com/mybasicappcomponents/jni/MyCppJNI");
        jmethodID apiID = env->GetMethodID(clazz, "fromJNIcallbackJAVA", "(Ljava/lang/String;)Ljava/lang/String;");
        jobject result = env->CallObjectMethod(thiz, apiID, inputString);
        // setup callback api ---

        const char* str = env->GetStringUTFChars((jstring) result, NULL); // should be released but what a heck, it's a tutorial :)
        ALOGD("JNI: output= %s",str);
        return env->NewStringUTF(str);
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
