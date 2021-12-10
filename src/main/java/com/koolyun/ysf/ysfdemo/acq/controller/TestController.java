package com.koolyun.ysf.ysfdemo.acq.controller;

import cn.hutool.extra.pinyin.PinyinUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koolyun.ysf.ysfdemo.common.MyException;
import com.koolyun.ysf.ysfdemo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/normal")
    public String normal() throws MyException {
        return "500";
    }

    @GetMapping("/normal/2")
    public ModelAndView normal2() throws MyException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("500.html");
        return mav;
    }

    @GetMapping("/rest")
    @ResponseBody
    public String rest() {
        return "rest in html";
    }

    @GetMapping("/myex")
    public String myException() throws MyException {
        throw new MyException("hello");
    }

    @GetMapping("/ex")
    public String exception() throws Exception {
        String fl = PinyinUtil.getFirstLetter("中国", "");
        System.out.println(fl);
        throw new Exception("hello");
    }

    @GetMapping("/runex/{txnKey}")
    @ResponseBody
    public String runex(@RequestParam Map<String, Object> params, @PathVariable("txnKey") String txnKey) throws JsonProcessingException {
        params.put(txnKey, Result.ok("123"));
        return objectMapper.writeValueAsString(params);
    }
}
