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

/**
 * @author 王川源
 * 图像相关的Service实现
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDAO imageDAO;

    /**
     * 检测图片中的图形，并高亮显示
     * @param base64Str 原图像的base64码
     * @return 处理后的图像
     */
    @Override
    public ImageVO detectShapes(String base64Str) {
        return ShapeDetector.detectShapesInImg(base64Str);
    }

    /**
     * 存储图像
     * @param imgName 图像名
     * @param base64Str 图像的base64码
     * @return 处理结果
     */
    @Override
    public ResultMessage saveImg(String imgName, String base64Str) {
        if (imageDAO.existsById(imgName))
            return ResultMessage.FAILURE;
        else {
            imageDAO.saveAndFlush(new ImagePO(imgName, base64Str, LocalDateTime.now()));
            return ResultMessage.SUCCESS;
        }
    }

    /**
     * 修改原图像
     * @param imgName 图像名
     * @param base64Str 图像的base64码
     * @return 处理结果
     */
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

    /**
     * 通过图像名查找图像
     * @param imgName 图像名
     * @return 符合条件的图像
     */
    @Override
    public ImageVO getImgByName(String imgName) {
        if (imageDAO.existsById(imgName)) {
            ImagePO imagePO = imageDAO.getOne(imgName);
            return new ImageVO(imagePO.getBase64Str());
        } else return null;
    }

    /**
     * 获得存储的所有图像名
     * @return 图像名集合
     */
    @Override
    public List<String> getAllImgNames() {
        return imageDAO.getAllImgNames();
    }
}
