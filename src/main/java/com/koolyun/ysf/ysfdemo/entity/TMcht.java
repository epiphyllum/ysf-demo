package com.koolyun.ysf.ysfdemo.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (TMcht)表实体类
 *
 * @author makejava
 * @since 2021-12-10 17:07:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TMcht extends Model<TMcht> implements  Serializable{
    
    private Long id;
    //商户号
    private String mchtNo;
    //商户公钥
    private String publicKey;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    }

