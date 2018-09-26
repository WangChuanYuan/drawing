package com.casual.drawing.util;

import java.io.*;

/**
 * @author 王川源
 * 负责文件的读写
 */
public class FileUtil {

    /**
     * 读取文件
     * @param filePath 文件路径
     * @return
     */
    public static byte[] readFileAsBytes(String filePath) {
        InputStream inputStream;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(filePath);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 写入文件
     * @param data 二进制数据
     * @param filePath 文件路径
     * @return
     */
    public static boolean writeBytesToFile(byte[] data, String filePath) {
        OutputStream outputStream;
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            outputStream = new FileOutputStream(file);
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
