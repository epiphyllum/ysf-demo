package com.koolyun.ysf.ysfdemo.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (TMOrder)表实体类
 *
 * @author makejava
 * @since 2021-12-10 17:03:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TMOrder implements  Serializable{
    
    private Long id;
    //订单号
    private String orderNo;
    //描述
    private String description;
    //订单金额
    private String amount;
    //订单支付状态 0:  处理中， 1: 成功, 2:失败
    private Integer status;
    //用户id
    private String userId;
    //注册手机
    private String userPhone;
    //扣款协议号
    private String contractId;
    //商户号
    private String mchtNo;
    //用户姓名
    private String name;
    //身份证号
    private String idNo;
    //实名手机号
    private String mobile;
    //是否实名
    private Integer isReal;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    }

