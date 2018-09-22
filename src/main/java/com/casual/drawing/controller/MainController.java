package com.casual.drawing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 王川源
 * 主控制器，负责页面渲染
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String getIndex(){
        return "drawing-board";
    }
}
