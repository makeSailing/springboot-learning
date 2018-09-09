#### 建表语句

```java
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT '' COMMENT '登录名',
  `real_name` varchar(10) DEFAULT '' COMMENT '真实姓名',
  `park_code` char(8) DEFAULT '' COMMENT '园区编号',
  `park_name` varchar(20) DEFAULT '' COMMENT '园区',
  `pwd` varchar(20) DEFAULT '' COMMENT '密码',
  `state` tinyint(4) DEFAULT '0' COMMENT '状态，0-无效，1-有效',
  `role` tinyint(4) DEFAULT '0' COMMENT '角色，0-园区管理员，1-系统管理员',
  `last_date` timestamp NULL DEFAULT NULL COMMENT '最近一次访问时间',
  `last_ip` int(10) unsigned DEFAULT '0' COMMENT '最后一次登录的IP',
  `login_count` int(6) DEFAULT '0' COMMENT '登录次数',
  `date_add` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '添加时间',
  `date_upd` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

