package com.koolyun.ysf.ysfdemo.acq.controller;

import com.koolyun.ysf.ysfdemo.acq.service.YsfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acq/api")
public class AcqController {

    @Autowired
    private YsfService ysfService;

    /**
     * 接收商户 查询交易结果
     * @return
     */
    @RequestMapping("/status")
    public int queryPay() {
        return 1;
    }


    /**
     * 尝试签约
     */
    @PostMapping("/tryContract")
    public void tryContract() {
        // 1.  先实名判定


        // 尝试发起到云闪付签约
        ysfService.contract();

        // 如果签约成功 - 直接发起扣款, 展示扣款中组件
        ysfService.pay();

        // 如果签约不成功 - 需要让vue导航到一键绑卡界面组件  - { success = false, data = { bankList = [] }}
        ysfService.bankList();
        return;
    }


    /**
     *  接收云闪付通知
     */
    @RequestMapping("/notify")
    public int notification() {
        return 1;
    }


}

