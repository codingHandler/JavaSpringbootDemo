package com.ch.hotelDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: ch
 * @date: 2022/6/4 17:08
 * @description: TODO
 */
@Controller
public class PageController {
    @RequestMapping("/adminIndex")
    private String adminIndexPage(){
        return "adminIndex";
    }
}
