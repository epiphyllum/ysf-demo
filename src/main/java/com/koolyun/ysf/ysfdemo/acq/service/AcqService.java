package com.koolyun.ysf.ysfdemo.acq.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koolyun.ysf.ysfdemo.acq.dto.EntranceDTO;
import com.koolyun.ysf.ysfdemo.acq.dto.UserInfoDTO;
import com.koolyun.ysf.ysfdemo.common.RedisUtil;
import com.koolyun.ysf.ysfdemo.entity.TMOrder;
import com.koolyun.ysf.ysfdemo.entity.TMchtContract;
import com.koolyun.ysf.ysfdemo.service.TMOrderService;
import com.koolyun.ysf.ysfdemo.service.TMchtContractService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

@Service
@Slf4j
public class AcqService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TMOrderService tmOrderService;

    @Autowired
    private TMchtContractService tMchtContractService;

    /**
     * 创建商户订单
     */
    public void createMchtOrder(EntranceDTO entranceDTO) {
        TMOrder tmOrder = new TMOrder();
        //创建订单
        BeanUtil.copyProperties(entranceDTO,tmOrder);
        tmOrderService.save(tmOrder);
    }

    /**
     * 实名认证发送短信 - 我们自己发
     */
    public String authSendSMS() {
        String uuid = UUID.randomUUID().toString();
        redisUtil.set(uuid, "111111");
        return uuid;
    }

    /**
     * 实名认证接口调用:  mobile, id_no, name
     */
    public boolean authVerify(UserInfoDTO userInfoDTO) {
        if(StringUtils.isEmpty(userInfoDTO.getIdNo()) || StringUtils.isEmpty(userInfoDTO.getIdNo())){
            return  false;
        }
        if(userInfoDTO.getIdNo().trim().length() != 16 || userInfoDTO.getIdNo().trim().length() != 18){
            return false;
        }
        //实名认证完,将用户数据落库
        TMchtContract tMchtContract = new TMchtContract();
        BeanUtil.copyProperties(userInfoDTO,tMchtContract);
        UpdateWrapper<TMchtContract> updateWrapper = new UpdateWrapper<TMchtContract>();
        updateWrapper.set("name",userInfoDTO.getName());
        updateWrapper.set("id_no",userInfoDTO.getIdNo());
        updateWrapper.set("mobile",userInfoDTO.getMobile());
        updateWrapper.eq("user_id",userInfoDTO.getUserId());
        tMchtContractService.update(updateWrapper);
        return true;
    }

    /**
     *  redis保存交易上下文
     */
    public void saveTxn(String txnKey,EntranceDTO entranceDTO) {
        redisUtil.set(txnKey,entranceDTO);
    }

    /**
     * 获取交易上下文
     */
    public  EntranceDTO getTxn(String txnKey) {
        EntranceDTO entranceDTO = redisUtil.get(txnKey, new TypeReference<EntranceDTO>() {
        });
        return  entranceDTO;
    }

    public Boolean verify(EntranceDTO entranceDTO){
        TreeMap<String,String> map = new TreeMap<>();
        map.put("mchtNo",entranceDTO.getMchtNo());
        map.put("orderNo",entranceDTO.getOrderNo());
        map.put("amount",String.valueOf(entranceDTO.getAmount()));
        map.put("userInfo",entranceDTO.getUserInfo().toString());
        if(!StringUtils.isEmpty(entranceDTO.getContractId())){
            map.put("contractId",entranceDTO.getContractId());
        }
        String signStr = this.getSignStr(map);
        return this.doVerify(entranceDTO.getSignature(),signStr);
    }

    private Boolean doVerify(String sign,String signStr){
        //验签
        return true;
    }

    private String getSignStr(TreeMap<String, String> map) {
        StringBuilder sb = new StringBuilder();

        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if(!"amount".equals(key)){
                String value = map.get(key);
                sb.append(key + "=" + value);
            }else {
                Integer value = Integer.getInteger(map.get(key));
                sb.append(key + "=" + value);
            }
        }
        return sb.toString();
    }


}
