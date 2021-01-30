#include <jni.h>
#include <opencv2/opencv.hpp>
using namespace cv;

extern "C"
JNIEXPORT void JNICALL
Java_com_cy_opencvandroidniubility_JniUtils_gray(JNIEnv *env, jclass clazz, jlong mat_addr_src,
                                                 jlong mat_addr_dst) {
    Mat *mat_src = (Mat *) mat_addr_src;
    Mat *mat_dst = (Mat *) mat_addr_dst;
    cvtColor(*mat_src, *mat_dst, COLOR_BGR2GRAY);

//    Mat &mat_src = *(Mat *) mat_addr_src;
//    Mat &mat_dst = *(Mat *) mat_addr_dst;
//    cvtColor(mat_src, mat_dst, COLOR_BGR2GRAY);

}extern "C"
JNIEXPORT void JNICALL
Java_com_cy_opencvandroidniubility_JniUtils_bilateralFilter(JNIEnv *env, jclass clazz,
                                                            jlong mat_addr_src,
                                                            jlong mat_addr_dst) {
    // TODO: implement bilateralFilter()
    Mat *mat_src = (Mat *) mat_addr_src;
    Mat *mat_dst = (Mat *) mat_addr_dst;
    bilateralFilter(*mat_src, *mat_dst, 25, 50, 12.5);
}