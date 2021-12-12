package com.koolyun.ysf.ysfdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.koolyun.ysf.ysfdemo.dao.TMOrderDao;
import com.koolyun.ysf.ysfdemo.entity.TMOrder;
import com.koolyun.ysf.ysfdemo.service.TMOrderService;
import org.springframework.stereotype.Service;

/**
 * (TMOrder)表服务实现类
 *
 * @author makejava
 * @since 2021-12-10 17:03:38
 */
@Service("tMOrderService")
public class TMOrderServiceImpl extends ServiceImpl<TMOrderDao, TMOrder> implements TMOrderService {

}

