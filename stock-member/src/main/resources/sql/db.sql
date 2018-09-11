CREATE TABLE `stock_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar (10) NOT NULL COMMENT '昵称',
  `photo` varchar (100) NOT NULL COMMENT '头像',
  `registry_time` datetime NOT NULL COMMENT '注册时间',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `id_card` varchar (18) DEFAULT NULL COMMENT '身份证号码',
  `name` varchar (20) DEFAULT NULL COMMENT '用户姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;