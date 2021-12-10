package com.koolyun.ysf.ysfdemo.cups.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/cups")
@Controller("cupsIndexController")
public class IndexController {

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("cups/index.html");
        return mav;
    }

    @GetMapping("/ex")
    public String exception() throws Exception {
        throw new Exception("hello world");
    }
}
