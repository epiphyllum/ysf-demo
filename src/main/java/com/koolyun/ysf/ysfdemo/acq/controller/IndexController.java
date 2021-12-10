package com.koolyun.ysf.ysfdemo.acq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koolyun.ysf.ysfdemo.acq.service.AcqService;
import com.koolyun.ysf.ysfdemo.acq.service.RedisUtil;
import com.koolyun.ysf.ysfdemo.acq.service.YsfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller("acqIndexController")
@RequestMapping("/acq")
@Slf4j
public class IndexController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ObjectMapper objectMapper;
    /**
     * vue app入口
     * mchtno,   商户号
     * <p>
     * orderno,  订单号
     * amount,   订单金额
     * <p>
     * 用户信息， 需要加密
     * userinfo = {
     * userid,    用户id(hash)
     * username,  用户姓名
     * idno,      身份证号
     * mobile,    手机号
     * isreal,    是否实名
     * },
     * <p>
     * contractid,  扣款协议号
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

    /**
     * 单页面应用入口
     *
     * @param params
     * @param txnKey
     * @return
     */
    @GetMapping("/index/:txnKey")
    public ModelAndView index(
            @RequestParam Map<String, String> params,
            @PathVariable("txnKey") String txnKey
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("acq/index.html");

        // 创建交易, redis保存上下文
        acqService.createMchtOrder();
        acqService.saveTxnContext(txnKey); // txnKey, txn;

        // 1. 移除签名字段
        String signature = (String) params.remove("signature");
        if (signature == null) {
            // vue - 展示: 错误信息
            mav.addObject("component", "err");
            mav.addObject("msg", "缺少签名");
            return mav;
        }

        // 1> 验证签名
        // 签名错误
        boolean sigok = false;
        if (!sigok) {
            mav.addObject("component", "err");
            mav.addObject("msg", "签名错误");
        }

        // 带协议号, 直接向银联发起后台扣款
        String contractid = (String) params.remove("contract_id");
        if (contractid != null) {

            // 发起协议收款
            ysfService.pay();

            // 1. 协议扣款发起成功,
            if (true) {
                mav.addObject("component", "paying");
                return mav;
            }

            // 2. 协议扣款发起失败
            if (true) {
                mav.addObject("component", "err");
                mav.addObject("msg", "发起收款失败");
                return mav;
            }
        }

        // 不带协议号, 则直尝试发起签约, 让vue app展示签约界面
        String userinfo = (String) params.remove("userinfo");
        mav.addObject("component", "sign");
        mav.addObject("data", userinfo);
        return mav;
    }

    /**
     * 从银联跳回
     */
    @GetMapping("/resume/{txnKey}")
    public ModelAndView resume(@PathVariable("txnKey") String txnKey) {
        ModelAndView mav = new ModelAndView();

        // 取出上下文
        Object txn = redisUtil.get(txnKey);

        mav.addObject("component", "sign");
        mav.addObject("data", "data");   //  不需要实名了！！！！！  因为已经实名过了。

        mav.setViewName("acq/index.html");
        return mav;
    }


}
