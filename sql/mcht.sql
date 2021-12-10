-- 商户订单表
drop table if exists t_m_order;
create table t_m_order
(
    id          bigint(20) unsigned not null auto_increment,
    order_no    varchar(64) comment '订单号',
    description varchar(256) comment '描述',
    amount      long comment '订单金额',
    status      smallint comment '订单支付状态 0:  处理中， 1: 成功, 2:失败',

    user_id     varchar(64) comment '用户id',
    user_phone  varchar(15) comment '注册手机',
    contract_id varchar(64) comment '扣款协议号',
    mcht_no     varchar(32) comment '商户号',

    -- 实名信息
    name        varchar(64) comment '用户姓名',
    id_no       varchar(20) comment '身份证号',
    mobile      varchar(15) comment '实名手机号',
    is_real     smallint comment '是否实名',

    create_time datetime            not null default current_timestamp() comment '创建时间',
    update_time datetime comment '更新时间',

    primary key (id)
) engine = innodb;
