package com.cy.opencvandroidniubility;

import org.opencv.core.Mat;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2021/1/28 15:18
 * @UpdateUser:
 * @UpdateDate: 2021/1/28 15:18
 * @UpdateRemark:
 * @Version:
 */
public class JniUtils {
    static {
        System.loadLibrary("native-lib");
    }
    public static  native  void gray(long mat_addr_src,long mat_addr_dst);
}
