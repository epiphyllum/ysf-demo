package com.koolyun.ysf.ysfdemo.acq.controller;

import com.koolyun.ysf.ysfdemo.acq.bean.BankBean;
import com.koolyun.ysf.ysfdemo.acq.dto.EntranceDTO;
import com.koolyun.ysf.ysfdemo.acq.dto.UserInfoDTO;
import com.koolyun.ysf.ysfdemo.acq.service.AcqService;
import com.koolyun.ysf.ysfdemo.acq.service.YsfService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/acq/api")
public class AcqController {

    @Autowired
    private YsfService ysfService;

    @Autowired
    private AcqService acqService;

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
    public void tryContract(@Valid UserInfoDTO userInfoDTO,@RequestParam String txnKey) {
        //判断用户信息是否是商户提供,如果是商户提供则不需要进行实名认证.
        EntranceDTO txn = acqService.getTxn(txnKey);

        //为0是商户未实名
        if(txn.getUserInfo().getIsReal()==0){
            // 1.  先实名判定
            boolean authenticationFlag = acqService.authVerify(userInfoDTO);
            if(!authenticationFlag){
                //实名认证失败!
                return;
            }
        }

        // 尝试发起到云闪付签约
        String contractId = ysfService.contract(txn.getUserInfo());


        // 如果签约不成功 - 需要让vue导航到一键绑卡界面组件  - { success = false, data = { bankList = [] }}
        if(StringUtils.isEmpty(contractId)){
            //银行支持列表
            List<BankBean> list = ysfService.bankList();
            return;
        }
        // 如果签约成功 - 直接发起扣款, 展示扣款中组件
        Boolean payFlag = ysfService.pay(txn);
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

