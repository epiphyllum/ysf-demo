package com.koolyun.ysf.ysfdemo.acq.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koolyun.ysf.ysfdemo.acq.dto.EntranceDTO;
import com.koolyun.ysf.ysfdemo.acq.dto.UserInfoDTO;
import com.koolyun.ysf.ysfdemo.acq.service.AcqService;
import com.koolyun.ysf.ysfdemo.common.RedisUtil;
import com.koolyun.ysf.ysfdemo.acq.service.YsfService;
import com.koolyun.ysf.ysfdemo.entity.TMchtContract;
import com.koolyun.ysf.ysfdemo.service.TMchtContractService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller("acqIndexController")
@RequestMapping("/acq")
@Slf4j
public class IndexController {

    @Data
    static class SignDTO {
    }


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ObjectMapper objectMapper;
    /**
     * vue app入口
     * mcht_no,   商户号
     * <p>
     * order_no,  订单号
     * amount,   订单金额
     * <p>
     * 用户信息， 需要加密
     * userinfo = {
     * user_id,    用户id(hash)
     * name,  用户姓名
     * id_no,      身份证号
     * mobile,    手机号
     * is_real,    是否实名
     * },
     * <p>
     * contract_id,  扣款协议号
     * <p>
     * signature,  签名信息
     * <p>
     * 1. 验证签名
     * <p>
     * 2. 渲染页面， 页面里放一个json
     * <p>
     * 展示三个组件:
     * 1. 付款中  +  付款成功  +  付款失败
     * 2. 错误界面
     * 3. 签约界面
     *
     * @return
     */

    @Autowired
    private YsfService ysfService;
    @Autowired
    private AcqService acqService;

    @Autowired
    private TMchtContractService tMchtContractService;

    /**
     * 单页面应用入口
     *
     *
     *
     * @param entranceDTO
     * txnkey 生成规则:   mcht_no + "-"  + order_no
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@Valid EntranceDTO entranceDTO) {
        ModelAndView mav = new ModelAndView();
        // 1> 验证签名
        // 签名错误
        boolean sigok = acqService.verify(entranceDTO);
        if (!sigok) {
            mav.addObject("component", "err");
            mav.addObject("msg", "签名错误");
            return mav;
        }
        // 创建交易,入库
        acqService.createMchtOrder(entranceDTO);
        String txnKey = entranceDTO.getMchtNo() + "-"  + entranceDTO.getOrderNo();
        // 带协议号, 直接向银联发起后台扣款
        String contractid = entranceDTO.getContractId();
        if (!StringUtils.isEmpty(contractid)) {
            // 发起协议收款
            Boolean payFlag = ysfService.pay(entranceDTO);
            // 1. 协议扣款发起成功,
            if (payFlag) {
                //mav.addObject("component", "paying");
                mav.setViewName("/acq/paySuccess");
                return mav;
            }
            // 2. 协议扣款发起失败
            if (!payFlag) {
                //mav.addObject("component", "err");
                mav.setViewName("/acq/payFail");
                mav.addObject("msg", "发起收款失败");
                return mav;
            }
        }
        //当扣款协议号为空时,redis中保存上下文
        acqService.saveTxn(txnKey,entranceDTO);
        // 不带协议号, 则直尝试发起签约, 让vue app展示签约界面
        // String userinfo = (String) params.remove("userinfo");
        mav.addObject("component", "sign");
        SignDTO signDTO = new SignDTO();
        mav.addObject("data", signDTO);
        return mav;
    }

    /**
     * 从银联跳回
     */
    @GetMapping("/resume")
    public ModelAndView resume(@RequestParam("txnKey") String txnKey) {
        ModelAndView mav = new ModelAndView();

        //从银联跳回去redis中查询订单信息+数据库存查询签约协议号

        // 取出上下文
        EntranceDTO  entranceDTO = acqService.getTxn(txnKey);
        //   尝试签约 + 扣款，
        String contractId = "";
        //isReal=1:商户已经实名,否则通过user_id从数据库中获取实名信息.
        if(entranceDTO.getUserInfo().getIsReal()==1){
            contractId = ysfService.contract(entranceDTO.getUserInfo());
        }else {
            //从数据库中获取userInfo
            TMchtContract byUserId = tMchtContractService.getOne(new QueryWrapper<TMchtContract>()
                    .eq("user_id", entranceDTO.getUserInfo().getUserId()));
            UserInfoDTO userInfoDTO = new UserInfoDTO();
            BeanUtil.copyProperties(byUserId,userInfoDTO);
            userInfoDTO.setIsReal(1); //已认证
            contractId = ysfService.contract(userInfoDTO);
        }

        //签约成功的情况下发起扣款
        if(!StringUtils.isEmpty(contractId)){
            entranceDTO.setContractId(contractId);
            //发起协议扣款
            Boolean payFlag = ysfService.pay(entranceDTO);
            if(!payFlag){
                //扣款失败页面
                mav.setViewName("/acq/payFail");
                return  mav;
            }
        }
        //返回扣款成功页面
        mav.setViewName("/acq/paySuccess");
        return mav;
    }


}
