package com.koolyun.ysf.ysfdemo.acq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 调用云闪付接口
 */
@Service
@Slf4j
public class YsfService {

    @Autowired
    private RestTemplate restTemplate;


    @Value("ysf.host")
    private String ysfHost;

    /**
     * 像银联发起签约
     */
    public void contract() {
        log.info("尝试向云闪付发起签约...");
//        ResponseEntity<Integer> i = restTemplate.postForEntity(ysfHost + "/contract", null, Integer.class, 1);
    }


    /**
     * 云闪付 - 银行卡签约
     */
    public void cardContract() {
    }

    /**
     * 发起协议扣款
     */
    public void pay() {
        log.info("尝试向云闪付发起协议扣款...");
//        ResponseEntity<Integer> i = restTemplate.postForEntity(ysfHost + "/contract", null, Integer.class, 1);
    }

    /**
     * 一键绑卡银行列表
     */
    public void bankList() {
        return;
    }
}
