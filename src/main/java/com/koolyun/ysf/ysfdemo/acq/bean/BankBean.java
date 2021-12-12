package com.koolyun.ysf.ysfdemo.acq.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankBean {
    private String bankName;    //支持银行名称
    private String bankCode;    //支持银行代码
    private Integer cloudPayCardAtrt; //1.借记卡,2.贷记卡
    private String bankLogoUrl; //绑卡 APP 展示绑卡银行 logo时的获取地址
    private String bindBankUrl; //绑卡 APP 访问绑卡银行拼接的请求地址
}
