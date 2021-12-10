package com.koolyun.ysf.ysfdemo.acq.service;

import lombok.SneakyThrows;
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
     * 像银联发起云闪付协议扣款签约  - 同步返回的
     */
    public void contract() {
        log.info("尝试向云闪付发起签约...");
//        ResponseEntity<Integer> i = restTemplate.postForEntity(ysfHost + "/contract", null, Integer.class, 1);
    }


    /**
     * 云闪付 - 银行卡签约
     */
    public String cardContract() {
        log.info("尝试向云闪付发起绑卡申请， 获取sn....");
        return "snbankcard";
    }

    /**
     * 发起协议扣款
     */
    public void pay() {
        log.info("尝试向云闪付发起协议扣款...");
//        ResponseEntity<Integer> i = restTemplate.postForEntity(ysfHost + "/contract", null, Integer.class, 1);
        new Thread(new Runnable() {
            @Override
            @SneakyThrows
            public void run() {
                Thread.sleep(3000);
//                restTemplate.postForEntity("/")    // 告诉通联扣款成功
            }
        }).start();
    }

    /**
     * 一键绑卡银行列表
     */
    public void bankList() {
        log.info("向云闪付获取一键绑卡银行支持列表..");
        return;
    }
}
