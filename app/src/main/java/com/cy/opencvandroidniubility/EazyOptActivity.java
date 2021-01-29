package com.cy.opencvandroidniubility;

import android.Manifest;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.cy.dialog.BaseDialog;
import com.cy.permissionniubility.OnPermissionCallback;
import com.cy.permissionniubility.PermissionUtils;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.io.InputStream;


public class EazyOptActivity extends AppCompatActivity {

    private BaseDialog baseDialog;
    private ImageView iv_normal;
    private ImageView iv_deal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eazy_opt);
        baseDialog = new BaseDialog(this)
                .contentView(R.layout.dialog_pic_deal)
                .gravity(Gravity.CENTER)
                .animType(BaseDialog.AnimInType.CENTER);

        iv_normal = baseDialog.findViewById(R.id.iv_normal);
        iv_deal = baseDialog.findViewById(R.id.iv_deal);
        findViewById(R.id.btn_gray).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.show();
                PermissionUtils.checkPermission(EazyOptActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        new OnPermissionCallback(EazyOptActivity.this) {
                            @Override
                            public void onPermissionHave() {
                                Mat mat_src = new Mat();
                                Mat mat_dst = new Mat();
                                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gou_1);
                                iv_normal.setImageBitmap(bitmap);

                                Bitmap bitmap_deal = bitmap.copy(Bitmap.Config.RGB_565, true);
                                Utils.bitmapToMat(bitmap_deal, mat_src);
                                Imgproc.cvtColor(mat_src, mat_dst, Imgproc.COLOR_BGR2GRAY);
                                Utils.matToBitmap(mat_dst, bitmap_deal);
                                iv_deal.setImageBitmap(bitmap_deal);
                            }
                        });
            }
        });
        findViewById(R.id.btn_gray_jni).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.show();
                PermissionUtils.checkPermission(EazyOptActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        new OnPermissionCallback(EazyOptActivity.this) {
                            @Override
                            public void onPermissionHave() {
                                Mat mat_src = new Mat();
                                Mat mat_dst = new Mat();
                                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gou_1);
                                iv_normal.setImageBitmap(bitmap);


                                Bitmap bitmap_deal = bitmap.copy(Bitmap.Config.RGB_565, true);
                                Utils.bitmapToMat(bitmap_deal, mat_src);
//                                Imgproc.cvtColor(mat_src, mat_dst, Imgproc.COLOR_BGR2GRAY);
                                //自定义JNI方法调用
                                JniUtils.gray(mat_src.nativeObj,mat_dst.nativeObj);
                                Utils.matToBitmap(mat_dst, bitmap_deal);
                                iv_deal.setImageBitmap(bitmap_deal);
                            }
                        });
            }
        });
        findViewById(R.id.btn_erode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.show();
                PermissionUtils.checkPermission(EazyOptActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        new OnPermissionCallback(EazyOptActivity.this) {
                            @Override
                            public void onPermissionHave() {
                                Mat mat_src = new Mat();
                                Mat mat_dst = new Mat();
                                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gou_2);
                                iv_normal.setImageBitmap(bitmap);

                                Bitmap bitmap_deal = bitmap.copy(Bitmap.Config.RGB_565, true);
                                Utils.bitmapToMat(bitmap_deal, mat_src);
                                Mat mat_deal = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(15, 15));
                                Imgproc.erode(mat_src, mat_dst, mat_deal);
                                Utils.matToBitmap(mat_dst, bitmap_deal);
                                iv_deal.setImageBitmap(bitmap_deal);
                            }
                        });
            }
        });
        findViewById(R.id.btn_blur).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.show();
                PermissionUtils.checkPermission(EazyOptActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        new OnPermissionCallback(EazyOptActivity.this) {
                            @Override
                            public void onPermissionHave() {
                                Mat mat_src = new Mat();
                                Mat mat_dst = new Mat();
                                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mao_1);
                                iv_normal.setImageBitmap(bitmap);

                                Bitmap bitmap_deal = bitmap.copy(Bitmap.Config.RGB_565, true);
                                Utils.bitmapToMat(bitmap_deal, mat_src);
                                Imgproc.blur(mat_src, mat_dst, new Size(17, 17));
                                Utils.matToBitmap(mat_dst, bitmap_deal);
                                iv_deal.setImageBitmap(bitmap_deal);
                            }
                        });
            }
        });
        findViewById(R.id.btn_canny).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.show();
                PermissionUtils.checkPermission(EazyOptActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        new OnPermissionCallback(EazyOptActivity.this) {
                            @Override
                            public void onPermissionHave() {
                                /**
                                 * // 将边缘检测后的图 cannyImage 边以黑色的形式贴在原图 image上。
                                 * void pasteEdge(Mat &image, Mat &outImg, IplImage cannyImage)
                                 * {
                                 *     Mat cannyMat;
                                 *     //将IplImage转化为Mat
                                 *     cannyMat = cvarrToMat(&cannyImage);
                                 *     //颜色反转
                                 *     cannyMat = cannyMat < 100;
                                 *     image.copyTo(outImg, cannyMat);
                                 * }
                                 */
                                Mat mat_src = new Mat();
                                Mat mat_blur = new Mat();
                                Mat mat_dst = new Mat();
                                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.man_ls);
                                iv_normal.setImageBitmap(bitmap);

                                Bitmap bitmap_deal = bitmap.copy(Bitmap.Config.RGB_565, true);
                                Utils.bitmapToMat(bitmap_deal, mat_src);
                                Imgproc.blur(mat_src, mat_blur, new Size(3, 3));
                                Imgproc.Canny(mat_blur, mat_dst, 30, 30 * 3, 3);

                                Utils.matToBitmap(mat_dst, bitmap_deal);
                                //颜色反转
//                                GPUImageFilter gpuImageFilter = new GPUImageColorInvertFilter();
//                                Bitmap bitmapR=bitmap_filter(EazyOptActivity.this,bitmap_deal,gpuImageFilter);

//                                Mat mat3 = new Mat();
//                                Utils.bitmapToMat(bitmapR,mat3);


//                                Mat mat4 = new Mat();
//                                Utils.bitmapToMat(bitmap, mat4);
//                                mat4.copyTo(mat_r, mat3);
//
//                                Bitmap bitmap_ = Bitmap.createBitmap(bitmapR.getWidth(),bitmapR.getHeight(), Bitmap.Config.RGB_565);
//                                Utils.matToBitmap(mat_r,bitmap_);
//                                iv_deal.setImageBitmap(bitmapR);
                            }
                        });
            }
        });
        findViewById(R.id.btn_cover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.show();
                PermissionUtils.checkPermission(EazyOptActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        new OnPermissionCallback(EazyOptActivity.this) {
                            @Override
                            public void onPermissionHave() {
                                //定义一个Mat类型，用于存放，图像的ROI
//                                Mat imageROI;
////方法一
//                                imageROI = image(Rect(800, 350, logo.cols, logo.rows));
////方法二
////imageROI=image(Range(350,350+logo.rows),Range(800,800+logo.cols));
//
////将logo加到原图上
//                                addWeighted(imageROI, 0.5, logo, 0.3, 0., imageROI);
//
////显示结果
//                                namedWindow("【4】原画+logo图");
//                                imshow("【4】原画+logo图", image);


                                // 初始化数据
                                Mat mat1 = new Mat();
                                Mat mat2 = new Mat();
                                Mat mat1Sub = new Mat();
//                                Mat mask= new Mat();

                                // 加载图片
                                Bitmap bt1 = BitmapFactory.decodeResource(getResources(),
                                        R.drawable.man_ls);
                                Bitmap bt2 = BitmapFactory.decodeResource(getResources(),
                                        R.drawable.gou_2);
                                Bitmap bt3 = null;

                                // 转换数据
                                Utils.bitmapToMat(bt1, mat1);
                                Utils.bitmapToMat(bt2, mat2);
//                                Imgproc.cvtColor(mat2, mask, Imgproc.COLOR_BGR2GRAY);

                                /** 方法一加权 高级方式 可实现水印效果*********/

//                                 mat1Sub=mat1.submat(0, (int) (mat2.rows()*1f/2), 0, (int) (mat2.cols()*1f/2));
//                                 Core.addWeighted(mat1Sub, 0, mat2, 1, 0, mat1Sub);

                                /** 方法二 求差 ********/

                                // submat(y坐标, 图片2的高, x坐标，图片2的宽);
                                // mat1Sub=mat1.submat(0, mat2.rows(), 0, mat2.cols());
                                // mat2.copyTo(mat1Sub);

                                /*** 方法三兴趣区域裁剪 **/
                                // 定义感兴趣区域Rect(x坐标，y坐标,图片2的宽，图片2的高)
                                Rect rec = new Rect(0, 0, mat2.cols(), mat2.rows());
//                                // submat(y坐标, 图片2的高, x坐标，图片2的宽);
                                mat1Sub = mat1.submat(rec);

                                mat2.copyTo(mat1Sub);

//                                //【1】读入图像
//                                Mat srcImage1= imread("dota_pa.jpg");
//                                Mat logoImage= imread("dota_logo.jpg");
//                                if(!srcImage1.data ) { printf("你妹，读取srcImage1错误~！ \n"); return false; }
//                                if(!logoImage.data ) { printf("你妹，读取logoImage错误~！ \n"); return false; }
//
//                                //【2】定义一个Mat类型并给其设定ROI区域
//                                Mat imageROI= srcImage1(Rect(200,250,logoImage.cols,logoImage.rows));
//
//                                //【3】加载掩模（必须是灰度图）
//                                Mat mask= imread("dota_logo.jpg",0);
//
//                                //【4】将掩膜拷贝到ROI
//                                logoImage.copyTo(imageROI,mask);

                                //转化为android识别的图像数据注意bt3的宽高要和mat1一至
                                bt3 = Bitmap.createBitmap(mat1.cols(), mat1.rows(), Bitmap.Config.RGB_565);
                                Utils.matToBitmap(mat1, bt3);
                                iv_normal.setImageBitmap(bt3);
                            }
                        });
            }
        });
    }


    /**
     * 从Assets中读取图片
     */
    private Bitmap getImageFromAssetsFile(String fileName) {
        Bitmap image = null;
        AssetManager am = getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }
}
