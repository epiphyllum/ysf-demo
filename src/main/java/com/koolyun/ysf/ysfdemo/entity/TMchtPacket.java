package com.koolyun.ysf.ysfdemo.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (TMchtPacket)表实体类
 *
 * @author makejava
 * @since 2021-12-10 17:11:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TMchtPacket implements Serializable {
    
    private Long id;
    //商户号
    private String mchtNo;
    //商户订单号
    private String orderNo;
    //支付平台订单号
    private String txnId;
    //商户请求报文
    private String request;
    //商户应答报文
    private String response;
    //商户通知报文
    private String notify;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;


    }

