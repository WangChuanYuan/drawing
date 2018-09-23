package com.casual.drawing.controller;

import com.casual.drawing.vo.ImageVO;
import com.casual.drawing.vo.ResultMessage;
import com.casual.drawing.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageController {

    @Autowired
    public ImageService imageService;

    private String regBase64Str(String src){
        String des = src == null ? "" : src.replace(" ", "+").substring(src.indexOf(",") + 1);
        return des;
    }

    @RequestMapping("/detect_shapes")
    public ImageVO detectShapes(@RequestParam String base64Str){
        return imageService.detectShapes(regBase64Str(base64Str));
    }

    @RequestMapping("/save_img")
    public ResultMessage saveImg(@RequestParam String imgName, @RequestParam String base64Str){
        return imageService.saveImg(imgName, regBase64Str(base64Str));
    }

    @RequestMapping("/modify_img")
    public ResultMessage modifyImg(@RequestParam String imgName, @RequestParam String base64Str){
        return imageService.modifyImg(imgName, regBase64Str(base64Str));
    }

    @RequestMapping("/get_img_by_name")
    public ImageVO getImgByName(@RequestParam String imgName){
        return imageService.getImgByName(imgName);
    }

    @RequestMapping("/get_all_imgnames")
    public List<String> getAllImgNames(){
        return imageService.getAllImgNames();
    }
}
