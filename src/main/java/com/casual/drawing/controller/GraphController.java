package com.casual.drawing.controller;

import com.casual.drawing.entity.Image;
import com.casual.drawing.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GraphController {

    @Autowired
    public GraphService graphService;

    @RequestMapping("/detectShape")
    public Image detectShape(@RequestParam String base64Str){
        return graphService.detectShape(base64Str);
    }

}
