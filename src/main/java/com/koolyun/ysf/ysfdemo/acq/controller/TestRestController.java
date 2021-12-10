package com.koolyun.ysf.ysfdemo.acq.controller;

import com.koolyun.ysf.ysfdemo.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testRest/api/")
public class TestRestController {

    @GetMapping("/ex")
    public int ex() throws Exception {
        throw new Exception("错误");
    }

    @GetMapping(value = "/str")
    public String str() {
        return "hello world";
    }

    @GetMapping("/int")
    public int intValue() {
        return 10;
    }

    @GetMapping("/result")
    public Result<?> result() {
        return Result.ok("hello world result");
    }
}
