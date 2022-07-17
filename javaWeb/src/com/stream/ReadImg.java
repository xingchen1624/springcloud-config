/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: ReadImg
 * Author:   xingc
 * Date:     2021/3/9 21:19
 * Description: 读取文件流保存为本地文件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.stream;

import com.sun.imageio.plugins.common.ImageUtil;

import java.io.*;

/**
 * 〈一句话功能简述〉
 * 〈读取文件流保存为本地文件〉
 * @author xingc
 * @date 2021/3/9
 * @since 1.0.0
 **/
public class ReadImg {
    public static void main(String[] args) {
        String url = "E:\\图片\\猎人.jpg";
        String writeUrl = "E:\\a.png";
        byte[] imageFromLocalByUrl = getImageFromLocalByUrl(url);
        if (null != imageFromLocalByUrl && imageFromLocalByUrl.length > 0) {
            System.out.println("读取到：" + imageFromLocalByUrl.length + " 字节");
            writeImageToDisk(imageFromLocalByUrl, writeUrl);
        } else {
            System.out.println("没有从该连接获得内容");
        }

    }

    /**
     * 将图片写入到磁盘
     *
     * @param img
     *            图片数据流
     * @param zipImageUrl
     *            文件保存时的名称
     */
    public static void writeImageToDisk(byte[] img, String zipImageUrl) {
        try {
            File file = new File(zipImageUrl);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(img);
            fops.flush();
            fops.close();
            System.out.println("图片已经写入" + zipImageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        /**
         * 根据地址获得数据的字节流
         *
         * @param strUrl 本地连接地址
         * @return
         */
    public static byte[] getImageFromLocalByUrl(String strUrl) {
        try {
            File imageFile = new File(strUrl);
            InputStream inStream = new FileInputStream(imageFile);
            byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从输入流中获取数据
     *
     * @param inStream
     *            输入流
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}