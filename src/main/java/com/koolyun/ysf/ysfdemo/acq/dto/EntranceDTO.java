package com.koolyun.ysf.ysfdemo.acq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EntranceDTO {
    @NotNull(message = "商户号不能为空!")
    private String mchtNo; //商户号

    @NotNull(message = "订单号不能为空!")
    private String orderNo; //订单号

    @NotNull(message = "订单金额不能为空!")
    private Integer amount; //订单金额

    @NotNull(message = "用户信息不能为空!")
    private UserInfoDTO userInfo; //用户信息

    private String contractId; //扣款协议号

    @NotNull(message = "签名信息不能为空!")
    private String signature; //签名信息
}
