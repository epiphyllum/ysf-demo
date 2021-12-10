package com.koolyun.ysf.ysfdemo.acq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @GetMapping("/ex")
    public int ex() throws Exception {
        return 10;
    }

    @GetMapping("/str")
    public String str() {
        return "hello world";
    }
}
