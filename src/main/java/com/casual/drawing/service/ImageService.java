package com.casual.drawing.service;

import com.casual.drawing.vo.ImageVO;
import com.casual.drawing.vo.ResultMessage;

import java.util.List;

/**
 * @author 王川源
 * 图像相关的Service接口
 */
public interface ImageService {

    /**
     * 检测图片中的图形，并高亮显示
     * @param base64Str 原图像的base64码
     * @return 处理后的图像
     */
    ImageVO detectShapes(String base64Str);

    /**
     * 存储图像
     * @param imgName 图像名
     * @param base64Str 图像的base64码
     * @return 处理结果
     */
    ResultMessage saveImg(String imgName, String base64Str);

    /**
     * 修改原图像
     * @param imgName 图像名
     * @param base64Str 图像的base64码
     * @return 处理结果
     */
    ResultMessage modifyImg(String imgName, String base64Str);

    /**
     * 通过图像名查找图像
     * @param imgName 图像名
     * @return 符合条件的图像
     */
    ImageVO getImgByName(String imgName);

    /**
     * 获得存储的所有图像名
     * @return 图像名集合
     */
    List<String> getAllImgNames();
}
