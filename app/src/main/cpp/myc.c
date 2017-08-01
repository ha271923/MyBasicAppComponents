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

#include "myc.h"

/* Return current time in milliseconds */
static double now_ms(void)
{
    struct timeval tv;
    gettimeofday(&tv, NULL);
    return tv.tv_sec*1000. + tv.tv_usec/1000.;
}


JNIEXPORT jstring JNICALL Java_sample_hawk_com_mybasicappcomponents_jni_MyCJNIActivity_helloString(
		JNIEnv* env, jobject obj, jstring str){
    return (*env)->NewStringUTF(env, "Hello from JNI !");
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
