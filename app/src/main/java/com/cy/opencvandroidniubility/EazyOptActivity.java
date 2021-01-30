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
                Mat mat_src = new Mat();
                Mat mat_dst = new Mat();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gou_1);
                iv_normal.setImageBitmap(bitmap);

                Utils.bitmapToMat(bitmap, mat_src);

                Bitmap bitmap_deal = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);
                Imgproc.cvtColor(mat_src, mat_dst, Imgproc.COLOR_BGR2GRAY);
                Utils.matToBitmap(mat_dst, bitmap_deal);
                iv_deal.setImageBitmap(bitmap_deal);
            }
        });
        findViewById(R.id.btn_gray_jni).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.show();
                Mat mat_src = new Mat();
                Mat mat_dst = new Mat();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gou_1);
                iv_normal.setImageBitmap(bitmap);

                Utils.bitmapToMat(bitmap, mat_src);

                Bitmap bitmap_deal = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);
                //Imgproc.cvtColor(mat_src, mat_dst, Imgproc.COLOR_BGR2GRAY);
                //自定义JNI方法调用
                JniUtils.gray(mat_src.nativeObj, mat_dst.nativeObj);
                Utils.matToBitmap(mat_dst, bitmap_deal);
                iv_deal.setImageBitmap(bitmap_deal);
            }
        });
        findViewById(R.id.btn_bilateralFilter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.show();
                /**
                 * Bilateral filter error, Assertion failed ((src.type() == CV_8UC1 || src.type() == CV_8UC3) && src.data != dst.data)
                 * 一、概述
                 *
                 *         这个异常是在用OpenCV做高斯双边滤波做图像美化的时候出现的异常。这个异常信息的意思是图像类型不对。高斯双边滤波只允许CV_8UC1或者CV_8UC3即只能加载单通多的灰色图片或者三通道的彩色图片。
                 *
                 * 二、产生的原因
                 *
                 * 　　原因在于我在做测试的时候直接用BitmapFactory加载了一个Bitmap对象，并把Bitmap对象通过opencv的Uitls工具转换为了Mat对象，然后操作了这个Mat对象。由于Bitmap默认的通道时RGBA，而openCV默认的是GBR，由于通道不一致所以导致了以上的那个错误。
                 *
                 * 三、解决方法
                 *
                 * 　　解决方案也是比较简单，把RGBA色彩控件转换为BGR色彩空间就OK了。具体的转换是通过 Imgproc.cvtColor(target,dst,Imgproc.COLOR_RGBA2BGR);
                 */

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.man_ls);
                iv_normal.setImageBitmap(bitmap);

                Mat mat_src = new Mat();
                Mat mat_dst = new Mat();

                Utils.bitmapToMat(bitmap, mat_src);

                Bitmap bitmap_deal = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);
                Imgproc.cvtColor(mat_src, mat_src, Imgproc.COLOR_RGB2BGR);
                Imgproc.bilateralFilter(mat_src, mat_dst, 25, 50, 12.5);
//                JniUtils.bilateralFilter(mat_src.nativeObj, mat_dst.nativeObj);

                Imgproc.cvtColor(mat_dst, mat_dst, Imgproc.COLOR_BGR2RGB);
                Utils.matToBitmap(mat_dst, bitmap_deal);
                iv_deal.setImageBitmap(bitmap_deal);
            }
        });
        findViewById(R.id.btn_bilateralFilter_jni).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.show();

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.man_ls);
                iv_normal.setImageBitmap(bitmap);

                Mat mat_src = new Mat();
                Mat mat_dst = new Mat();

                Utils.bitmapToMat(bitmap, mat_src);

                Bitmap bitmap_deal = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);
                Imgproc.cvtColor(mat_src, mat_src, Imgproc.COLOR_RGB2BGR);
//                Imgproc.bilateralFilter(mat_src, mat_dst,25, 50, 12.5);
                JniUtils.bilateralFilter(mat_src.nativeObj, mat_dst.nativeObj);

                Imgproc.cvtColor(mat_dst, mat_dst, Imgproc.COLOR_BGR2RGB);
                Utils.matToBitmap(mat_dst, bitmap_deal);
                iv_deal.setImageBitmap(bitmap_deal);
            }
        });
        findViewById(R.id.btn_erode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.show();
                Mat mat_src = new Mat();
                Mat mat_dst = new Mat();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gou_2);
                iv_normal.setImageBitmap(bitmap);

                Utils.bitmapToMat(bitmap, mat_src);

                Bitmap bitmap_deal = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);
                Imgproc.erode(mat_src, mat_dst, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(15, 15)));
                Utils.matToBitmap(mat_dst, bitmap_deal);
                iv_deal.setImageBitmap(bitmap_deal);
            }
        });
        findViewById(R.id.btn_blur).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.show();
                Mat mat_src = new Mat();
                Mat mat_dst = new Mat();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mao_1);
                iv_normal.setImageBitmap(bitmap);

                Utils.bitmapToMat(bitmap, mat_src);

                Bitmap bitmap_deal = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);
                Imgproc.blur(mat_src, mat_dst, new Size(17, 17));
                Utils.matToBitmap(mat_dst, bitmap_deal);
                iv_deal.setImageBitmap(bitmap_deal);
            }
        });
        findViewById(R.id.btn_canny).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.show();
                Mat mat_src = new Mat();
                Mat mat_blur = new Mat();
                Mat mat_dst = new Mat();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.man_ls);
                iv_normal.setImageBitmap(bitmap);

                Utils.bitmapToMat(bitmap, mat_src);

                Bitmap bitmap_deal = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);
                Imgproc.blur(mat_src, mat_blur, new Size(3, 3));
                Imgproc.Canny(mat_blur, mat_dst, 30, 30 * 3, 3);
                Utils.matToBitmap(mat_dst, bitmap_deal);
                iv_deal.setImageBitmap(bitmap_deal);
            }
        });
        findViewById(R.id.btn_cover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.show();
                iv_deal.setImageBitmap(null);

                Mat mat_bottom = new Mat();
                Mat mat_top = new Mat();
                Mat mat_rect = new Mat();

                Bitmap bitmap_bottom = BitmapFactory.decodeResource(getResources(),
                        R.drawable.man_ls);
                Bitmap bitmap_top = BitmapFactory.decodeResource(getResources(),
                        R.drawable.gou_2);
                Bitmap bitmap_deal = Bitmap.createBitmap(bitmap_bottom.getWidth(), bitmap_bottom.getHeight(), Bitmap.Config.RGB_565);

                Utils.bitmapToMat(bitmap_bottom, mat_bottom);
                Utils.bitmapToMat(bitmap_top, mat_top);

                Rect rec = new Rect(0, 0, mat_top.cols(), mat_top.rows());
                mat_rect = mat_bottom.submat(rec);

                mat_top.copyTo(mat_rect);
                Utils.matToBitmap(mat_bottom, bitmap_deal);
                iv_normal.setImageBitmap(bitmap_deal);

            }
        });
    }

}
