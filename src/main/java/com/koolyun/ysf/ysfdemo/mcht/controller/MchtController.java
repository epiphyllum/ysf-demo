package com.koolyun.ysf.ysfdemo.mcht.controller;

import com.koolyun.ysf.ysfdemo.mcht.service.MchtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mcht/api")
public class MchtController {

    @Autowired
    private  MchtService mchtService;

    /**
     * 创建订单:  返回一个 payUrl
     */
    @PostMapping("/order")
    public String createOrder() {
        return mchtService.createOrder();   // 返回一个跳转链接给客户端
    }

    /**
     * 收到收单机构通知
     * @return
     */
    @RequestMapping("/notify")
    public int getNotified() {
        return 1;
    }

    /**
     * 获取支付跳转信息
     */
    @GetMapping("/:orderId")
    public void getAcqTxn() {
    }


    @GetMapping("/test")
    public String strTest() {
        return "hello world";
    }
}
