package com.yizhitong.userclient.utils.photo;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.lgh.huanglib.util.L;
import com.yizhitong.userclient.utils.Constanst;
import com.yizhitong.userclient.utils.photo.utilFixSevent.PhotoFitSevent;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.yizhitong.userclient.utils.photo.BitmapUtil.PHOTO_DIR2;


public class PicUtils {
    public static final String TAG = "PicUtils";

    /**
     * 按比例压缩图片
     *
     * @param sourceFile 源文件
     * @param targetFile 存放文件
     * @param scale      缩放的比例
     * @throws FileNotFoundException
     */
    public static void compressPic(File sourceFile, File targetFile, int scale) throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = scale;
        Bitmap bitmap = BitmapFactory.decodeFile(sourceFile.getPath(), options);
        boolean isCompress = bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
        // Log.v(TAG,"compressPic_isCompress:"+isCompress);
    }

    public static int getPicHeight(String fileDirAndName) {
        BitmapFactory.Options options = getPicInfo(fileDirAndName);
        return options.outHeight;
    }

    public static int getPicWidth(String fileDirAndName) {
        BitmapFactory.Options options = getPicInfo(fileDirAndName);
        return options.outWidth;
    }

    /**
     * 最关键在此，把options.inJustDecodeBounds = true;
     * 这里再decodeFile()，返回的bitmap为空，但此时调用options.outHeight时，已经包含了图片的高了
     */
    public static BitmapFactory.Options getPicInfo(String fileDirAndName) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 此时返回的bitmap为null
        Bitmap bitmap = BitmapFactory.decodeFile(fileDirAndName, options);
        return options;
    }

    /**
     * 取出裁剪的图
     *
     * @throws FileNotFoundException scale是控制大中小（总共就分为4等分，原图就不压缩，大是4分之三，中是2分之一，小是4分之一）
     */
    public static boolean showCutPhoto(Intent data, int scale, String targetFilePath)
            throws FileNotFoundException {
        boolean isSuccess = false;
        if (data != null) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap cutPhoto = extras.getParcelable("data");
                if (cutPhoto != null) {
                    compress50(cutPhoto);

                    FileOutputStream fileOutputStream = new FileOutputStream(targetFilePath);

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = false;
                    options.inSampleSize = scale;

                    isSuccess = cutPhoto.compress(CompressFormat.JPEG, 100, fileOutputStream);// (0
                    // -
                    // 100)压缩文件
                } else {
                    isSuccess = false;
                }
            }
        }
        return isSuccess;

    }

    public static String getCompressedImgPath(String sourceImgPath) {
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            Bitmap bmp = BitmapFactory.decodeFile(sourceImgPath, opts);
            opts.inJustDecodeBounds = false;

            int w = opts.outWidth;
            int h = opts.outHeight;
            float standardW = 800f;//分辨率
            float standardH = 480f;

            int zoomRatio = 1;
            if (w > h && w > standardW) {
                zoomRatio = (int) (w / standardW);
            } else if (w < h && h > standardH) {
                zoomRatio = (int) (h / standardH);
            }
            if (zoomRatio <= 0)
                zoomRatio = 1;
            opts.inSampleSize = zoomRatio;

            bmp = BitmapFactory.decodeFile(sourceImgPath, opts);

            File compressedImg = new File(sourceImgPath);
            L.e("lgh", compressedImg.getPath());
            FileOutputStream fos = new FileOutputStream(compressedImg);
            bmp.compress(CompressFormat.JPEG, 80, fos);
            fos.flush();
            fos.close();
            L.e("lgh", compressedImg.getPath());
            return compressedImg.getPath();

        } catch (FileNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return null;
    }

    public static ByteArrayOutputStream compress50(Bitmap cutPhoto) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        cutPhoto.compress(CompressFormat.JPEG, 100, baos);
        // Log.v(TAG,"_baos.toByteArray().length:"+baos.toByteArray().length);
        while (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            Log.v("compress50", "_baos.toByteArray().length:" + baos.toByteArray().length);

            baos.reset();// 重置baos即清空baos
            cutPhoto.compress(CompressFormat.JPEG, 50, baos);// 这里压缩50%，把压缩后的数据存放到baos中
            // Log.v(TAG,"_baos.toByteArray().length:"+baos.toByteArray().length);
        }
        return baos;
    }
    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            if (!PHOTO_DIR2.exists()) {
                PHOTO_DIR2.mkdirs();
            }
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }
    /**
     * 裁剪方法
     */
    public static void showPicToCutPhoto(Uri uri, Activity activity) {

        try {

            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
            intent.putExtra("crop", "true");
            // System.out.println("activityName:"+activity.getClass());
            int aspectY = (int) (PixelConvertUtil.getScreenHeight(activity) / PixelConvertUtil
                    .getScreenWidth(activity));
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", aspectY);
            // 这里裁剪图片宽高不能乘积不能大于255的平方
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
            intent.putExtra("return-data", true);

            activity.startActivityForResult(intent,  PhotoFitSevent.SELECT_CUT_PICK_PHOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 裁剪方法
     */
    public static void showPicToCutPhoto2(File sourceFile, Activity activity) {
        try {


            //authority 就是 内容提供者的包名  注意----注意
            Uri uri = FileProvider.getUriForFile(activity, Constanst.PROVIDER, sourceFile);
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            // System.out.println("activityName:"+activity.getClass());
            int aspectY = (int) (PixelConvertUtil.getScreenHeight(activity) / PixelConvertUtil
                    .getScreenWidth(activity));
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", aspectY);
            // 这里裁剪图片宽高不能乘积不能大于255的平方
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
            //开启临时权限
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //重点:针对7.0以上的操作
            intent.setClipData(ClipData.newRawUri(MediaStore.EXTRA_OUTPUT, uri));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.putExtra("return-data", false);
            intent.putExtra("noFaceDetection", false);

            intent.putExtra("outputFormat", CompressFormat.JPEG.toString());

            activity.startActivityForResult(intent, PhotoFitSevent.SELECT_CUT_PICK_PHOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ByteArrayOutputStream doCompressByHeightAndWidth(String sourceFilePath, float targetHeight,
                                                                   float targetWidth) throws Exception {
        int zoomSacle = getZoomSacleByHeightAndWidth(sourceFilePath, targetHeight, targetWidth);

        // long beginTime = //System.currentTimeMillis();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = getBitmapByZoomSacle(sourceFilePath, zoomSacle);
        bitmap.compress(CompressFormat.JPEG, 100, baos);
        // long endTime = //System.currentTimeMillis();
        // //System.out.println(endTime-beginTime);
        // if (baos!=null) {
        // //System.out.println(baos.toByteArray().length/1024);
        // }
        return baos;
    }

    public static int getZoomSacleByHeightAndWidth(String sourceFilePath, float targetHeight,
                                                   float targetWidth) {
        int originalHeight = PicUtils.getPicHeight(sourceFilePath);
        int originalWidth = PicUtils.getPicWidth(sourceFilePath);
        float scaleWidth = originalWidth / (float) targetWidth;
        float scaleHeight = originalHeight / (float) targetHeight;
        int zoomSacle = 0;
        if (scaleWidth > scaleHeight) {
            zoomSacle = (int) scaleHeight;
        } else {
            zoomSacle = (int) scaleWidth;
        }
        if (zoomSacle < 0 || zoomSacle == 0) {
            zoomSacle = 1;
        }
        return zoomSacle;
    }

    /***
     * 指定缩放倍数
     *
     * @param sourceFilePath
     * @param zoomSacle
     * @return
     * @throws Exception
     */
    public static Bitmap getBitmapByZoomSacle(String sourceFilePath, int zoomSacle) throws Exception {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = zoomSacle;
        return BitmapFactory.decodeStream(new FileInputStream(sourceFilePath), null, options);
    }

    /*
     * 得到图片字节流 数组大小
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 8];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * 将转换后的图片输出到指定文件夹
     *
     * @param sourceFilePath
     * @param
     * @param zoomSacle
     * @return
     * @throws Exception
     */
    public static boolean doCompressByZoomSacle(String sourceFilePath, String targetFilePath, int zoomSacle)
            throws Exception {
        FileOutputStream fot = new FileOutputStream(targetFilePath);
        Bitmap bitmap = getBitmapByZoomSacle(sourceFilePath, zoomSacle);
        boolean b = bitmap.compress(CompressFormat.JPEG, 100, fot);
        bitmap.recycle();
        fot.close();
        return b;
    }


    ///////////////////////////////////////////////////////////////////////

    /**
     * 裁剪方法
     */
    public static void showPicToCutPhotoAuto(File sourceFile, Activity activity) {

        try {


            Uri uri = FileProvider.getUriForFile(activity, Constanst.PROVIDER, sourceFile);

            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            // System.out.println("activityName:"+activity.getClass());
            int aspectY = (int) (PixelConvertUtil.getScreenHeight(activity) / PixelConvertUtil
                    .getScreenWidth(activity));
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", aspectY);
            // 这里裁剪图片宽高不能乘积不能大于255的平方
//        intent.putExtra("outputX", 200);
//        intent.putExtra("outputY", 200);
            intent.putExtra("scale", true);
            intent.putExtra("outputX", 350);
            intent.putExtra("outputY", 50);
            intent.putExtra("return-data", true);


            activity.startActivityForResult(intent, BitmapUtil.PIC_FROM_CUTPHOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println("getCutPhoto----cc");
    }

    /**
     * 裁剪方法
     */
    public static void showPicToCutPhotoAutoTwo(Uri uri, Activity activity) {

        try {

            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
            intent.putExtra("crop", "true");
            // System.out.println("activityName:"+activity.getClass());
            int aspectY = (int) (PixelConvertUtil.getScreenHeight(activity) / PixelConvertUtil
                    .getScreenWidth(activity));
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", aspectY);
            intent.putExtra("scale", true);
            // 这里裁剪图片宽高不能乘积不能大于255的平方
//        intent.putExtra("outputX", 200);
//        intent.putExtra("outputY", 200);
            intent.putExtra("outputX", 350);
            intent.putExtra("outputY", 50);
            intent.putExtra("return-data", true);

            activity.startActivityForResult(intent, BitmapUtil.PIC_FROM_CUTPHOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
