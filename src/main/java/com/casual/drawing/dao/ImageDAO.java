package com.casual.drawing.dao;

import com.casual.drawing.po.ImagePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageDAO extends JpaRepository<ImagePO, String> {

    @Query("select i.imgName from ImagePO i order by i.time desc")
    List<String> getAllImgNames();
}
