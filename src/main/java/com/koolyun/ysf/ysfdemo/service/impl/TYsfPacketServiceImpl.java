package com.koolyun.ysf.ysfdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.koolyun.ysf.ysfdemo.dao.TYsfPacketDao;
import com.koolyun.ysf.ysfdemo.entity.TYsfPacket;
import com.koolyun.ysf.ysfdemo.service.TYsfPacketService;
import org.springframework.stereotype.Service;

/**
 * (TYsfPacket)表服务实现类
 *
 * @author makejava
 * @since 2021-12-10 17:11:47
 */
@Service("tYsfPacketService")
public class TYsfPacketServiceImpl extends ServiceImpl<TYsfPacketDao, TYsfPacket> implements TYsfPacketService {

}

