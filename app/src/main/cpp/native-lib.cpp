#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_space_polylog_owasp_needleremover_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello OWASP (from native code)";
    return env->NewStringUTF(hello.c_str());
}

const char* native_function(){
    return "Something Something Android 343...";
}

extern "C"
JNIEXPORT jstring JNICALL
Java_space_polylog_owasp_needleremover_Native_1Call_stringFromJNI(JNIEnv *env, jobject instance) {

    return env->NewStringUTF(native_function());
}