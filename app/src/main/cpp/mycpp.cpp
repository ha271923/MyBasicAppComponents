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

JavaVM* mJvm = NULL;

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
        ALOGD("JNI: inputStr= %s",inputStr);
        callbackHandleEvent(1,2,3); // JNI callback to JAVA directly
        return env->NewStringUTF("Output from JNI !");
    }

    // C and Cpp diff at:
    // .C   is   (*env)->FindClass(env, ...)
    // .Cpp is     env->FindClass(...)
    JNIEXPORT jstring JNICALL Java_sample_hawk_com_mybasicappcomponents_jni_MyCppJNI_JAVAtoJNIcallbackJAVA(
            JNIEnv* env, jobject thiz, jstring inputString){
        ALOGD("JNI: input= %s",inputString);

        // setup callback api +++
        // jclass clazz = env->FindClass("sample/hawk/com/mybasicappcomponents/jni/MyCppJNIActivity");
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

    jint JNI_OnLoad(JavaVM* vm, void* reserved) {
        ALOGD("JNI: JNI_OnLoad");
        Context *ctx = new Context(vm);
        if (!ctx) return JNI_VERSION_1_6;
        mJvm = vm;
        return JNI_VERSION_1_6;
    }

    jint JNI_OnUnLoad(JavaVM* vm, void* reserved) {
        ALOGD("JNI: JNI_OnUnLoad");
        delete Context::getInstance();
    }

    void callbackHandleEvent(int type, int deviceType, int inputId) {
        // if( mMainActivity == NULL || mEnv == NULL ) {
        //     LOGD("ERROR!! callbackHandleEvent  got mMainActivity==NULL or  mEnv==NULL");
        //     return;
        // }

        if (mJvm->GetEnv((void **) &mEnv, JNI_VERSION_1_6) != JNI_OK) {
            return;
        }
        jclass clazz = mEnv->FindClass("sample/hawk/com/mybasicappcomponents/jni/MyCppJNIActivity");
        jmethodID apiID = mEnv->GetStaticMethodID(clazz, "onHandleEvent", "(III)V");
        mEnv->CallStaticVoidMethod(clazz, apiID, type, deviceType, inputId);
        mEnv->DeleteLocalRef(clazz); // FindClass will consume memory, need to DeleteLocalRef.
    }

}
