package com.koolyun.ysf.ysfdemo.acq.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.koolyun.ysf.ysfdemo.acq.bean.BankBean;
import com.koolyun.ysf.ysfdemo.acq.dto.EntranceDTO;
import com.koolyun.ysf.ysfdemo.acq.dto.UserInfoDTO;
import com.koolyun.ysf.ysfdemo.entity.TMchtContract;
import com.koolyun.ysf.ysfdemo.service.TMchtContractService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 调用云闪付接口
 */
@Service
@Slf4j
public class YsfService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TMchtContractService tMchtContractService;


    @Value("ysf.host")
    private String ysfHost;

    /**
     * 像银联发起云闪付协议扣款签约  - 同步返回的
     * @return
     */
    public String contract(UserInfoDTO userInfoDTO) {
        //发起协议
        log.info("尝试向云闪付发起签约...");
//        ResponseEntity<Integer> i = restTemplate.postForEntity(ysfHost + "/contract", null, Integer.class, 1);
        //签约成功以后将contractId存入到数据库.
        //假设银联返回的签约协议号
        String contractId = "89712322351";
        //签约成功协议号落库
        boolean updateFlag = tMchtContractService.update(new UpdateWrapper<TMchtContract>().set("contract_id", contractId)
                                                        .eq("user_id",userInfoDTO.getUserId()));
        if(!updateFlag){
            return null;
        }
        return contractId;
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
    public Boolean pay(EntranceDTO entranceDTO) {
        //签约协议号 + entranceDTO 数据向银联发送支付协议支付请求
        String contractId = entranceDTO.getContractId();
        final Boolean[] payFlag = { false };
        log.info("尝试向云闪付发起协议扣款...");
//        ResponseEntity<Integer> i = restTemplate.postForEntity(ysfHost + "/contract", null, Integer.class, 1);
        new Thread(new Runnable() {
            @Override
            @SneakyThrows
            public void run() {
                Thread.sleep(3000);
//                restTemplate.postForEntity("/")    // 告诉通联扣款成功
                payFlag[0] = true; //
            }
        }).start();
        return  payFlag[0];
    }

    /**
     * 一键绑卡银行列表
     */
    public List<BankBean> bankList() {
        log.info("向云闪付获取一键绑卡银行支持列表..");
        //请求银联..
        List<BankBean> bankList = new ArrayList<BankBean>();
        return bankList;
    }
}
