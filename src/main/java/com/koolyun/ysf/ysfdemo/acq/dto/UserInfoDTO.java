package com.koolyun.ysf.ysfdemo.acq.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class ContractDTO {

    @NotBlank(message = "用姓名不能为空!")
    private String name; //用户姓名
    @NotBlank(message = "身份证号码不能为空!")
    private String idCard; //身份证号码
    @NotBlank(message = "手机号不能为空!")
    private String phoneNo; //手机号
}
