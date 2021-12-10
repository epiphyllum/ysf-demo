package com.koolyun.ysf.ysfdemo.acq.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderRequestDTO {
    private String mchtno;
    private Integer amount;  // 分为单位
    private String orderno; // 订单号
    private String userInfo; // 加密信息
}
