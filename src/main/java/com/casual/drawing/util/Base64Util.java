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
        InputStream inputStream;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgPath);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * base64码转为图片
     * @param base64Str base64码
     * @return 转换结果
     */
    public static boolean transToImage(String base64Str) {
        if (base64Str == null || base64Str.length() == 0)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(base64Str);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成图片
            File img = new File(Const.TEMP_IMG);
            if (!img.getParentFile().exists())
                img.getParentFile().mkdirs();
            OutputStream out = new FileOutputStream(Const.TEMP_IMG);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
