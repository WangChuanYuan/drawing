package com.casual.drawing.dao;

import com.casual.drawing.po.ImagePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 王川源
 * 图像相关的DAO接口
 */
public interface ImageDAO extends JpaRepository<ImagePO, String> {

    /**
     * 查找所有的图像名
     * @return 图像名集合
     */
    @Query("select i.imgName from ImagePO i order by i.time desc")
    List<String> getAllImgNames();
}
