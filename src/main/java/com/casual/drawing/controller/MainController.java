package com.casual.drawing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 王川源
 * 主控制器，负责页面跳转
 */
@Controller
public class MainController {

    /**
     * 跳转至主页面
     * @return 主页面
     */
    @RequestMapping("/")
    public String getIndex(){
        return "drawing-board";
    }
}
