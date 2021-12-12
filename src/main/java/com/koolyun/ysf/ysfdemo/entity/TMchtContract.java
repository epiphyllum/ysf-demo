package com.koolyun.ysf.ysfdemo.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (TMchtContract)表实体类
 *
 * @author makejava
 * @since 2021-12-10 17:08:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TMchtContract implements Serializable{
    
    private Long id;
    //商户号
    private String mchtNo;
    //用户ID(hash)
    private String userId;
    //姓名
    private String name;
    //身份证号
    private String idNo;
    //签约手机号
    private String mobile;
    //协议号
    private String contractId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    }

