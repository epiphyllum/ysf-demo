package com.koolyun.ysf.ysfdemo.acq.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class UserInfoDTO {
    @NotNull
    private Long userId; //用户Id
    @NotNull(message = "用姓名不能为空!")
    private String name; //用户姓名
    @NotNull(message = "身份证号码不能为空!")
    private String idNo; //身份证号码
    @NotNull(message = "手机号不能为空!")
    private String mobile; //手机号
    @NotNull
    private Integer isReal;// 0:为实名,1已实名

}
