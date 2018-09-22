package com.casual.drawing.service;

import com.casual.drawing.entity.Image;
import com.casual.drawing.util.ShapeDetector;
import org.springframework.stereotype.Service;

@Service
public class GraphServiceImpl implements GraphService {

    @Override
    public Image detectShape(String base64Str) {
        return ShapeDetector.detectImg(base64Str);
    }
}
