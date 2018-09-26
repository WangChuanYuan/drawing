package com.casual.drawing.service;

import com.casual.drawing.DrawingApplication;
import com.casual.drawing.vo.ResultMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @author 王川源
 * 图片服务测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DrawingApplication.class)
@WebAppConfiguration
@Transactional
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @Test
    @Rollback
    public void saveImg() {
        assertEquals(ResultMessage.SUCCESS, imageService.saveImg("a", ""));
    }

    @Test
    @Rollback
    public void modifyImg() {
        assertEquals(ResultMessage.FAILURE, imageService.modifyImg("a", ""));
        imageService.saveImg("a", "");
        assertEquals(ResultMessage.SUCCESS, imageService.modifyImg("a", "1"));
    }

    @Test
    @Rollback
    public void getImgByName() {
        imageService.saveImg("a", "123");
        assertEquals("123", imageService.getImgByName("a").getBase64Str());
    }

    @Test
    @Rollback
    public void getAllImgNames() {
        int oldSize = imageService.getAllImgNames().size();
        imageService.saveImg("a", "");
        assertEquals(oldSize + 1, imageService.getAllImgNames().size());
    }
}