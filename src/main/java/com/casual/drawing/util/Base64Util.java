package com.casual.drawing.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class Base64Util {

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

    public static boolean transToImage(String base64Str) {
        if (base64Str == null || base64Str.length() == 0)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            base64Str = base64Str.replace(" ", "+").substring(base64Str.indexOf(",") + 1);
            byte[] b = decoder.decodeBuffer(base64Str);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpg图片
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
