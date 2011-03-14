/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class uk_co_mmscomputing_device_capi_jcapi */

#ifndef _Included_uk_co_mmscomputing_device_capi_jcapi
#define _Included_uk_co_mmscomputing_device_capi_jcapi
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    getPtrSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_getPtrSize
  (JNIEnv *, jclass);

/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    checkInstalled
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_checkInstalled
  (JNIEnv *, jclass);

/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    acquireNative32bitDataPtr
 * Signature: ([B)I
 */
JNIEXPORT jint JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_acquireNative32bitDataPtr
  (JNIEnv *, jclass, jbyteArray);

/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    releaseNative32bitDataPtr
 * Signature: ([BI)V
 */
JNIEXPORT void JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_releaseNative32bitDataPtr
  (JNIEnv *, jclass, jbyteArray, jint);

/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    acquireNative64bitDataPtr
 * Signature: ([B)J
 */
JNIEXPORT jlong JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_acquireNative64bitDataPtr
  (JNIEnv *, jclass, jbyteArray);

/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    releaseNative64bitDataPtr
 * Signature: ([BJ)V
 */
JNIEXPORT void JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_releaseNative64bitDataPtr
  (JNIEnv *, jclass, jbyteArray, jlong);

/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    copyFromNative32bitDataPtr
 * Signature: ([BII)[B
 */
JNIEXPORT jbyteArray JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_copyFromNative32bitDataPtr
  (JNIEnv *, jclass, jbyteArray, jint, jint);

/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    copyFromNative64bitDataPtr
 * Signature: ([BJI)[B
 */
JNIEXPORT jbyteArray JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_copyFromNative64bitDataPtr
  (JNIEnv *, jclass, jbyteArray, jlong, jint);

/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    register
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_register
  (JNIEnv *, jclass, jint, jint, jint);

/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    release
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_release
  (JNIEnv *, jclass, jint);

/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    putMessage
 * Signature: (I[B)V
 */
JNIEXPORT void JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_putMessage
  (JNIEnv *, jclass, jint, jbyteArray);

/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    getMessage
 * Signature: (I[B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_getMessage
  (JNIEnv *, jclass, jint, jbyteArray);

/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    waitForMessage
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_waitForMessage
  (JNIEnv *, jclass, jint);

/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    getManufacturer
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_getManufacturer
  (JNIEnv *, jclass, jint);

/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    getVersion
 * Signature: (I)[I
 */
JNIEXPORT jintArray JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_getVersion
  (JNIEnv *, jclass, jint);

/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    getSerialNumber
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_getSerialNumber
  (JNIEnv *, jclass, jint);

/*
 * Class:     uk_co_mmscomputing_device_capi_jcapi
 * Method:    getProfile
 * Signature: (I)[B
 */
JNIEXPORT jbyteArray JNICALL Java_uk_co_mmscomputing_device_capi_jcapi_getProfile
  (JNIEnv *, jclass, jint);

#ifdef __cplusplus
}
#endif
#endif
