package com.koolyun.ysf.ysfdemo.acq.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@Slf4j
public class AcqService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 创建商户订单
     */
    public void createMchtOrder() {
    }

    /**
     * 实名认证发送短信 - 我们自己发
     */
    public String authSendSMS() {
        String uuid = UUID.randomUUID().toString();
        redisUtil.set(uuid, "111111");
        return uuid;
    }

    /**
     * 实名认证接口调用:  mobile, id_no, name
     */
    public boolean authVerify() {
        return true;
    }

    /**
     *  redis保存交易上下文
     */
    public void saveTxnContext(String txnKey) {
    }

    /**
     * 获取交易上下文
     */
    public void getTxnContext(String txnKey) {
    }
}
