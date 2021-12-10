-- 商户表
drop table if exists t_mcht;
create table t_mcht
(
    id          bigint(20) unsigned not null auto_increment,
    mcht_no     varchar(32)         not null comment '商户号',
    public_key  varchar(2048)       not null comment '商户公钥',
    create_time datetime            not null default current_timestamp() comment '创建时间',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb;

-- 商户交易报文
drop table if exists t_mcht_order;
create table t_mcht_order
(
    id          bigint(20) unsigned not null auto_increment,
    mcht_no     varchar(32) comment '商户号',
    order_no    varchar(64) comment '商户订单号',
    amount      long comment '订单金额， 单位(分)',
    description varchar(128) comment '订单描述',
    user_id     varchar(64) comment '用户ID',
    user_phone  varchar(20) comment '用户手机号',

    name        varchar(64) comment '姓名',
    id_no       varchar(20) comment '身份证号',
    mobile      varchar(20) comment '实名手机号',
    contract_id varchar(128) comment '协议号',

    txn_id      varchar(64) comment '支付公司流水号',
    yfs_id      varchar(64) comment '银联流水号',
    status      smallint            not null default 0 comment '0: 处理中, 1: 成功, 2: 失败',

    create_time datetime            not null default current_timestamp() comment '创建时间',
    update_time datetime comment '更新时间',
    primary key (id)

) engine = innodb;


-- 协议表
drop table if exists t_mcht_contract;
create table t_mcht_contract
(
    id          bigint(20) unsigned not null auto_increment,
    mcht_no     varchar(32) comment '商户号',
    user_id     varchar(64) comment '用户ID(hash)',
    name        varchar(64) comment '姓名',
    id_no       varchar(20) comment '身份证号',
    mobile      varchar(20) comment '签约手机号',

    contract_id varchar(128) comment '协议号',

    create_time datetime            not null default current_timestamp() comment '创建时间',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb;

--

-- 商户接口交互报文记录
drop table if exists t_mcht_packet;
create table t_mcht_packet
(
    id          bigint(20) unsigned not null auto_increment,
    mcht_no     varchar(32) comment '商户号',
    order_no    varchar(64) comment '商户订单号',
    txn_id      varchar(64) comment '支付平台订单号',
    request     varchar(1024) comment '商户请求报文',
    response    varchar(1024) comment '商户应答报文',
    notify      varchar(1024) comment '商户通知报文',
    create_time datetime            not null default current_timestamp() comment '创建时间',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb;


-- 银联交易流水
drop table if exists t_ysf_packet;
create table t_ysf_packet
(
    id          bigint(20) unsigned not null auto_increment,
    ysf_id      varchar(64) comment '银联流水号',
    txn_id      varchar(64) comment '支付平台流水号',
    request     varchar(2048) comment '请求报文',
    response    varchar(2048) comment '应答报文',
    notify      varchar(2048) comment '云闪付通知报文',
    create_time datetime            not null default current_timestamp() comment '创建时间',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb;

