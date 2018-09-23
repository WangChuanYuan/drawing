package com.casual.drawing.service;

import com.casual.drawing.vo.ImageVO;
import com.casual.drawing.vo.ResultMessage;

import java.util.List;

public interface ImageService {

    ImageVO detectShapes(String base64Str);

    ResultMessage saveImg(String imgName, String base64Str);

    ResultMessage modifyImg(String imgName, String base64Str);

    ImageVO getImgByName(String imgName);

    List<String> getAllImgNames();
}
