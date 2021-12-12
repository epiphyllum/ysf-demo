package com.koolyun.ysf.ysfdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.koolyun.ysf.ysfdemo.dao.TMchtDao;
import com.koolyun.ysf.ysfdemo.entity.TMcht;
import com.koolyun.ysf.ysfdemo.service.TMchtService;
import org.springframework.stereotype.Service;

/**
 * (TMcht)表服务实现类
 *
 * @author makejava
 * @since 2021-12-10 17:07:09
 */
@Service("tMchtService")
public class TMchtServiceImpl extends ServiceImpl<TMchtDao, TMcht> implements TMchtService {

}

