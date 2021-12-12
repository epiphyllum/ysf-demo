package com.koolyun.ysf.ysfdemo.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (TYsfPacket)表实体类
 *
 * @author makejava
 * @since 2021-12-10 17:11:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TYsfPacket implements Serializable{
    
    private Long id;
    //银联流水号
    private String ysfId;
    //支付平台流水号
    private String txnId;
    //请求报文
    private String request;
    //应答报文
    private String response;
    //云闪付通知报文
    private String notify;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;


    }

