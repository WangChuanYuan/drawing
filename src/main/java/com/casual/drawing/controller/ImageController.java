package com.casual.drawing.controller;

import com.casual.drawing.vo.ImageVO;
import com.casual.drawing.vo.ResultMessage;
import com.casual.drawing.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 王川源
 * 图像相关的服务
 */
@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    /**
     * 处理经ajax传输的base64码，使可以转为图片
     * @param src 源图像的base64码
     * @return 转换后的base64码
     */
    private String regBase64Str(String src){
        String des = src == null ? "" : src.replace(" ", "+").substring(src.indexOf(",") + 1);
        return des;
    }

    /**
     * 检测图片中的图形，并高亮显示
     * @param base64Str 原图像的base64码
     * @return 处理后的图像
     */
    @RequestMapping("/detect_shapes")
    public ImageVO detectShapes(@RequestParam String base64Str){
        return imageService.detectShapes(regBase64Str(base64Str));
    }

    /**
     * 存储图像
     * @param imgName 图像名
     * @param base64Str 图像的base64码
     * @return 处理结果
     */
    @RequestMapping("/save_img")
    public ResultMessage saveImg(@RequestParam String imgName, @RequestParam String base64Str){
        return imageService.saveImg(imgName, regBase64Str(base64Str));
    }

    /**
     * 修改原图像
     * @param imgName 图像名
     * @param base64Str 图像的base64码
     * @return 处理结果
     */
    @RequestMapping("/modify_img")
    public ResultMessage modifyImg(@RequestParam String imgName, @RequestParam String base64Str){
        return imageService.modifyImg(imgName, regBase64Str(base64Str));
    }

    /**
     * 通过图像名查找图像
     * @param imgName 图像名
     * @return 符合条件的图像
     */
    @RequestMapping("/get_img_by_name")
    public ImageVO getImgByName(@RequestParam String imgName){
        return imageService.getImgByName(imgName);
    }

    /**
     * 获得存储的所有图像名
     * @return 图像名集合
     */
    @RequestMapping("/get_all_imgnames")
    public List<String> getAllImgNames(){
        return imageService.getAllImgNames();
    }
}
