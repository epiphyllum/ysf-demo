package com.koolyun.ysf.ysfdemo.cups.contoller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cups/api")
public class CUPSController {

    /**
     * 签约接口
     */
    @RequestMapping("/contract")
    public void contract() {
        return;
    }

    /**
     * 协议收款
     */
    @RequestMapping("/pay")
    public void pay() {
        return;
    }

    /**
     * 交易查询接口
     */
    @RequestMapping("/status")
    public void status() {
        return;
    }

    /**
     * 查询一键绑卡支持列表
     */
    @PostMapping("/bankList")
    public void bankList() {
        return;
    }
}
