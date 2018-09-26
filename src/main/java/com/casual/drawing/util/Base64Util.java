package com.casual.drawing.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @author 王川源
 * 负责处理base64码与图片的转换
 */
public class Base64Util {

    /**
     * 图片转为base64码
     * @param imgPath 图片地址
     * @return base64码
     */
    public static String transToBase64(String imgPath) {
        byte[] data = FileUtil.readFileAsBytes(imgPath);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * base64码转为图片
     * @param base64Str base64码
     * @return 转换结果
     */
    public static boolean transToImage(String base64Str, String imgPath) {
        if (base64Str == null || base64Str.length() == 0)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] data = decoder.decodeBuffer(base64Str);
            for (int i = 0; i < data.length; ++i) {
                if (data[i] < 0) {//调整异常数据
                    data[i] += 256;
                }
            }
            //生成图片
            return FileUtil.writeBytesToFile(data, imgPath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
