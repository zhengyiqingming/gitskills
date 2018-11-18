package com.baizhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    // 模板文件 + 动态数据(HttpServletRequest) ---> 文本输出(响应给浏览器)
    @RequestMapping("/index")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("index"); // /index.ftl

        modelAndView.addObject("name","zs");

        return modelAndView;
    }
}
