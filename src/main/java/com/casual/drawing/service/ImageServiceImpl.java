package com.casual.drawing.service;

import com.casual.drawing.dao.ImageDAO;
import com.casual.drawing.po.ImagePO;
import com.casual.drawing.vo.ImageVO;
import com.casual.drawing.vo.ResultMessage;
import com.casual.drawing.util.ShapeDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDAO imageDAO;

    @Override
    public ImageVO detectShapes(String base64Str) {
        return ShapeDetector.detectShapesInImg(base64Str);
    }

    @Override
    public ResultMessage saveImg(String imgName, String base64Str) {
        if (imageDAO.existsById(imgName))
            return ResultMessage.FAILURE;
        else {
            imageDAO.saveAndFlush(new ImagePO(imgName, base64Str, LocalDateTime.now()));
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage modifyImg(String imgName, String base64Str) {
        if (!imageDAO.existsById(imgName))
            return ResultMessage.FAILURE;
        else {
            ImagePO imagePO = imageDAO.getOne(imgName);
            imagePO.setBase64Str(base64Str);
            imagePO.setTime(LocalDateTime.now());
            imageDAO.saveAndFlush(imagePO);
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ImageVO getImgByName(String imgName) {
        if (imageDAO.existsById(imgName)) {
            ImagePO imagePO = imageDAO.getOne(imgName);
            return new ImageVO(imagePO.getBase64Str());
        } else return null;
    }

    @Override
    public List<String> getAllImgNames() {
        return imageDAO.getAllImgNames();
    }
}
