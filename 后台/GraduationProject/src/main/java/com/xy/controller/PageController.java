package com.xy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author GuoZhengYing
 * @ClassName PageController
 * @Description TDDO
 * @Date 2019/6/4 10:01
 * @Version 1.0
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }
}
