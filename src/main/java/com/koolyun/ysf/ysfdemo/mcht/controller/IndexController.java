package com.koolyun.ysf.ysfdemo.mcht.controller;

import com.koolyun.ysf.ysfdemo.mcht.service.MchtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("mchtIndexController")
@RequestMapping("/mcht")
public class IndexController {

    @Autowired
    MchtService mchtService;

    /**
     * vue 入口页
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("mcht/index.html");
        return mav;
    }

}
