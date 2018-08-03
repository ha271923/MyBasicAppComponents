/*
 *
 * Java型別	型別表示	字節大小(bit)
 *  boolean	jboolean    8, unsigned
 *  byte	jbyte	    8
 *  char	jchar	    16, unsigned
 *  short	jshort	    16
 *  int	    jint	    32
 *  long	jlong	    64
 *  float	jfloat	    32
 *  double	jdouble	    64
 *  void	void	    ---
 */

#include <jni.h>
#include <time.h>
#include <stdio.h>
#include <string.h>
#include "myc.h"
#include <android/Log.h>

#define TAG "myc" //
#define ALOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) //
#define ALOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__)  //
#define ALOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__)  //
#define ALOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) //
#define ALOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) //

/* Return current time in milliseconds */
static double now_ms(void)
{
    struct timeval tv;
    gettimeofday(&tv, NULL);
    return tv.tv_sec*1000. + tv.tv_usec/1000.;
}

JNIEXPORT jstring JNICALL Java_sample_hawk_com_mybasicappcomponents_jni_MyCJNIActivity_JAVAtoJNI(
        JNIEnv* env, jobject obj, jstring inputStr){
    ALOGD("JNI: inputStr= %s",inputStr);
    return (*env)->NewStringUTF(env, "Output from JNI !");
}


// C and Cpp diff at:
// .C   is   (*env)->FindClass(env, ...)
// .Cpp is     env->FindClass(...)
JNIEXPORT jstring JNICALL Java_sample_hawk_com_mybasicappcomponents_jni_MyCJNIActivity_JAVAtoJNIcallbackJAVA(
		JNIEnv* env, jobject obj, jstring inputString){
    ALOGD("JNI: input= %s",inputString);
    // setup callback api +++
    jclass clazz = (*env)->FindClass(env, "sample/hawk/com/mybasicappcomponents/jni/MyCJNIActivity");
    jmethodID apiID = (*env)->GetMethodID(env, clazz, "fromJNIcallbackJAVA", "(Ljava/lang/String;)Ljava/lang/String;");
    jobject result = (*env)->CallObjectMethod(env, obj, apiID, inputString);
    // setup callback api ---

    const char* str = (*env)->GetStringUTFChars(env,(jstring) result, NULL); // should be released but what a heck, it's a tutorial :)
    ALOGD("JNI: output= %s",str);
    return (*env)->NewStringUTF(env, str);
}

JNIEXPORT jint JNICALL Java_sample_hawk_com_mybasicappcomponents_jni_MyCJNIActivity_multiply(
        JNIEnv *env, jobject thiz, jint a, jint b) {
    jint total = 0;
    total = a * b;
    return total;
}

JNIEXPORT jdouble JNICALL Java_sample_hawk_com_mybasicappcomponents_jni_MyCJNIActivity_getTimeOfDay(
        JNIEnv *env, jobject thiz) {
    return now_ms();
}
