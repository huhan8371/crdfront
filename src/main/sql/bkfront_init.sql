CREATE DATABASE OptInfo;
USE OptInfo;

-- todo:mysql　Ver　5.7.12for Linux(x86_64)中一个表只能有一个TIMESTAMP
CREATE TABLE OptInfo(
  `tid` VARCHAR(12) NOT NULL COMMENT 'EFT终端号',
  `ipt_id` VARCHAR(6) NOT NULL COMMENT '加油站编号',
  `psam_tid` VARCHAR(12) NOT NULL COMMENT 'PSAM卡终端号',
  `station_name` VARCHAR(100) NOT NULL COMMENT '加油站点名称',
  `ip` VARCHAR(40) NOT NULL COMMENT 'IP地址',
  `port` int NOT NULL COMMENT '端口',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (tid),
  key idx_create_time(create_time)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='OPT信息表';

-- 初始化数据
INSERT into OptInfo(tid,psam_tid,station_name,ip,port,create_time)
VALUES
  ('200000038816','560000000001','b2e2cad4d6d0d0c432d5be','10.7.7.48',20002,'2016-06-15 00:00:00')


SHOW CREATE TABLE OptInfo\G;#显示表的创建信息