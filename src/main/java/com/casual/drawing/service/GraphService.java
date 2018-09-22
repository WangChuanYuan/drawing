package com.casual.drawing.service;

import com.casual.drawing.entity.Image;

public interface GraphService {

    Image detectShape(String base64Str);
}
