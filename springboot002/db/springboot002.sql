DROP TABLE IF EXISTS `consultation`; CREATE TABLE `consultation` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`sender`  bigint(20) NOT NULL COMMENT '发送者id' ,
`sender_name`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '发送者昵称' ,
`receiver`  bigint(20) NOT NULL COMMENT '接收者id' ,
`receiver_name`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '接收者昵称' ,
`send_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间' ,
`msg`  varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '消息内容' ,
`read_status`  int(11) NOT NULL COMMENT '读取状态' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
CHARSET=utf8mb4 COLLATE=utf8mb4_bin
COMMENT='问诊表'
AUTO_INCREMENT=1637562662544
ROW_FORMAT=DYNAMIC
;DROP TABLE IF EXISTS `storeup`; CREATE TABLE `storeup` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`addtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`userid`  bigint(20) NOT NULL COMMENT '用户id' ,
`refid`  bigint(20) NULL DEFAULT NULL COMMENT '收藏id' ,
`tablename`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '表名' ,
`name`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '收藏名称' ,
`picture`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '收藏图片' ,
`type`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '1' COMMENT '类型(1:收藏,21:赞,22:踩)' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
CHARSET=utf8mb4 COLLATE=utf8mb4_bin
COMMENT='收藏表'
AUTO_INCREMENT=1637562662544
ROW_FORMAT=DYNAMIC
;DROP TABLE IF EXISTS `news`; CREATE TABLE `news` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`addtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`title`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '标题' ,
`introduction`  longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '简介' ,
`picture`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '系统公告图片' ,
`content`  longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '内容' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
CHARSET=utf8mb4 COLLATE=utf8mb4_bin
COMMENT='系统公告'
ROW_FORMAT=DYNAMIC
;DROP TABLE IF EXISTS `config`;CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '轮播图名称',
  `value` varchar(100) DEFAULT NULL COMMENT '轮播图地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='轮播图';INSERT INTO `config` VALUES(null,'genImg/1765966211692.jpg','http://oss.fuqixuan.top/genImg/1765966211692.jpg'),(null,'genImg/1765966203138.jpg','http://oss.fuqixuan.top/genImg/1765966203138.jpg');DROP TABLE IF EXISTS `token`; CREATE TABLE `token` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`userid`  bigint(20) NOT NULL COMMENT '用户id' ,
`username`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名' ,
`tablename`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '表名' ,
`role`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色' ,
`token`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码' ,
`addtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间' ,
`expiratedtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
CHARSET=utf8mb4 COLLATE=utf8mb4_bin
COMMENT='token表'
AUTO_INCREMENT=11
ROW_FORMAT=DYNAMIC
;
DROP TABLE IF EXISTS `users`; CREATE TABLE `users` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`username`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名' ,
`password`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码' ,
`role`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '管理员' COMMENT '角色' ,
`addtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
CHARSET=utf8mb4 COLLATE=utf8mb4_bin
COMMENT='用户表'
AUTO_INCREMENT=2
ROW_FORMAT=DYNAMIC
;
INSERT INTO `users` VALUES ('1', 'admin', 'admin', '管理员', '2025-03-20 10:41:27');DROP TABLE IF EXISTS `yonghu`; CREATE TABLE yonghu( id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',addtime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,zhanghao  varchar(200) COMMENT '账号',touxiang  varchar(200) COMMENT '头像',mima  varchar(200) COMMENT '密码',xingbie  varchar(200) COMMENT '性别',lianxifangshi  varchar(200) COMMENT '联系方式',nicheng  varchar(200) COMMENT '昵称',PRIMARY KEY  (id))ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin
COMMENT='用户';DROP TABLE IF EXISTS `chongwuyisheng`; CREATE TABLE chongwuyisheng( id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',addtime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,zhanghao  varchar(200) COMMENT '账号',touxiang  varchar(200) COMMENT '头像',mima  varchar(200) COMMENT '密码',xingbie  varchar(200) COMMENT '性别',lianxifangshi  varchar(200) COMMENT '联系方式',nicheng  varchar(200) COMMENT '昵称',PRIMARY KEY  (id))ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin
COMMENT='宠物医生';DROP TABLE IF EXISTS `Discusschongwuguanli`; CREATE TABLE Discusschongwuguanli (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`addtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`refid`  bigint(20) NOT NULL COMMENT '关联表id' ,
`userid`  bigint(20) NOT NULL COMMENT '用户id' ,
`nickname` varchar(200)   DEFAULT NULL COMMENT '用户名' ,
`content` longtext   COMMENT '评论内容' ,
`reply` longtext  COMMENT '回复内容',
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
CHARSET=utf8mb4 COLLATE=utf8mb4_bin
COMMENT='宠物管理评论表'
;
DROP TABLE IF EXISTS `chongwuguanli`; CREATE TABLE chongwuguanli( id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',addtime  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,chongwumingcheng  varchar(200) COMMENT '宠物名称',tupian  varchar(200) COMMENT '图片',xingbie  varchar(200) COMMENT '性别',xihao  varchar(200) COMMENT '喜好',xiangqing  varchar(200) COMMENT '详情', userid  bigint(20) COMMENT '用户id',clicktime datetime  NULL DEFAULT NULL  COMMENT '最近点击时间',clicknum int(11)  NULL DEFAULT 0 COMMENT '点击次数',thumbsupnum int(11)  NULL DEFAULT 0  COMMENT '赞',crazilynum int(11)  NULL DEFAULT 0 COMMENT '踩',PRIMARY KEY  (id))ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin
COMMENT='宠物管理';DROP TABLE IF EXISTS `Discussxuexiguanli`; CREATE TABLE Discussxuexiguanli (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`addtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`refid`  bigint(20) NOT NULL COMMENT '关联表id' ,
`userid`  bigint(20) NOT NULL COMMENT '用户id' ,
`nickname` varchar(200)   DEFAULT NULL COMMENT '用户名' ,
`content` longtext   COMMENT '评论内容' ,
`reply` longtext  COMMENT '回复内容',
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
CHARSET=utf8mb4 COLLATE=utf8mb4_bin
COMMENT='学习管理评论表'
;
DROP TABLE IF EXISTS `xuexiguanli`; CREATE TABLE xuexiguanli( id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',addtime  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,biaoti  varchar(200) COMMENT '标题',tupian  varchar(200) COMMENT '图片',xiangqing  longtext COMMENT '详情',shangchuanshijian  date COMMENT '上传时间',clicktime datetime  NULL DEFAULT NULL  COMMENT '最近点击时间',clicknum int(11)  NULL DEFAULT 0 COMMENT '点击次数',thumbsupnum int(11)  NULL DEFAULT 0  COMMENT '赞',crazilynum int(11)  NULL DEFAULT 0 COMMENT '踩',sfsh  varchar(200)  DEFAULT '否' COMMENT '是否审核',shhf  longtext COMMENT '审核回复',PRIMARY KEY  (id))ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin
COMMENT='学习管理';DROP TABLE IF EXISTS `jibingshangchuan`; CREATE TABLE jibingshangchuan( id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',addtime  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,chongwumingcheng  varchar(200) COMMENT '宠物名称',bingqingmiaoshu  varchar(200) COMMENT '病情描述',tupianshangchuan  varchar(200) COMMENT '图片上传',buchong  varchar(200) COMMENT '补充',fabushijian  date COMMENT '发布时间', userid  bigint(20) COMMENT '用户id',sfsh  varchar(200)  DEFAULT '否' COMMENT '是否审核',shhf  longtext COMMENT '审核回复',PRIMARY KEY  (id))ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin
COMMENT='疾病上传';DROP TABLE IF EXISTS `zhenduan`; CREATE TABLE zhenduan( id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',addtime  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,chongwumingcheng  varchar(200) COMMENT '宠物名称',zhengzhuang  longtext COMMENT '症状',zhiliaojianyi  longtext COMMENT '治疗建议',zhenduanshijian  date COMMENT '诊断时间',zhenduanren  varchar(200) COMMENT '诊断人',lianxifangshi  varchar(200) COMMENT '联系方式', userid  bigint(20) COMMENT '用户id',PRIMARY KEY  (id))ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin
COMMENT='诊断';DROP TABLE IF EXISTS `Discusszixun`; CREATE TABLE Discusszixun (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`addtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`refid`  bigint(20) NOT NULL COMMENT '关联表id' ,
`userid`  bigint(20) NOT NULL COMMENT '用户id' ,
`nickname` varchar(200)   DEFAULT NULL COMMENT '用户名' ,
`content` longtext   COMMENT '评论内容' ,
`reply` longtext  COMMENT '回复内容',
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
CHARSET=utf8mb4 COLLATE=utf8mb4_bin
COMMENT='咨询评论表'
;
DROP TABLE IF EXISTS `zixun`; CREATE TABLE zixun( id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',addtime  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,chongwuyisheng  varchar(200) COMMENT '宠物医生',touxiang  varchar(200) COMMENT '头像',xingbie  varchar(200) COMMENT '性别',lianxifangshi  varchar(200) COMMENT '联系方式',xiangqing  longtext COMMENT '详情', userid  bigint(20) COMMENT '用户id',PRIMARY KEY  (id))ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin
COMMENT='咨询';

INSERT INTO `consultation` (`sender`, `sender_name`, `receiver`, `receiver_name`, `send_time`, `msg`, `read_status`) VALUES (1, '张伟', 2, '李娜', '2025-12-17 09:15:23', '医生您好，我最近三天一直咳嗽，有少量黄痰，没有发烧，需要吃什么药吗？', 1);
INSERT INTO `consultation` (`sender`, `sender_name`, `receiver`, `receiver_name`, `send_time`, `msg`, `read_status`) VALUES (2, '李娜', 1, '张伟', '2025-12-17 09:18:05', '张先生您好，根据您的描述，建议先服用一些止咳化痰的药物，如氨溴索。如果三天后症状没有缓解，建议来医院做进一步检查。', 1);
INSERT INTO `consultation` (`sender`, `sender_name`, `receiver`, `receiver_name`, `send_time`, `msg`, `read_status`) VALUES (3, '王芳', 4, '赵明', '2025-12-17 14:30:47', '赵医生，我体检报告显示血脂偏高，平时饮食需要注意什么？', 0);
INSERT INTO `consultation` (`sender`, `sender_name`, `receiver`, `receiver_name`, `send_time`, `msg`, `read_status`) VALUES (1, '张伟', 2, '李娜', '2025-12-17 16:05:12', '谢谢李医生，药已经买了。另外我还有点鼻塞，可以一起吃感冒药吗？', 1);
INSERT INTO `consultation` (`sender`, `sender_name`, `receiver`, `receiver_name`, `send_time`, `msg`, `read_status`) VALUES (4, '赵明', 3, '王芳', '2025-12-17 17:42:31', '王女士您好，建议低脂饮食，少吃动物内脏和油炸食品，多吃蔬菜水果，适当运动。一个月后可以复查血脂。', 1);
INSERT INTO `storeup` (`addtime`, `userid`, `refid`, `tablename`, `name`, `picture`, `type`) VALUES ('2025-12-17 09:15:23', 1, 101, 'goods', '经典款男士商务衬衫', '/upload/images/shirt.jpg', '1');
INSERT INTO `storeup` (`addtime`, `userid`, `refid`, `tablename`, `name`, `picture`, `type`) VALUES ('2025-12-17 14:30:45', 2, 205, 'news', '冬季新品发布会精彩回顾', '/upload/images/news_cover.jpg', '21');
INSERT INTO `storeup` (`addtime`, `userid`, `refid`, `tablename`, `name`, `picture`, `type`) VALUES ('2025-12-17 11:05:18', 3, 78, 'video', '如何快速掌握Python编程', '/upload/images/python_tutorial.jpg', '1');
INSERT INTO `storeup` (`addtime`, `userid`, `refid`, `tablename`, `name`, `picture`, `type`) VALUES ('2025-12-17 16:42:37', 4, 312, 'article', '健康饮食：一日三餐的科学搭配', '/upload/images/healthy_food.jpg', '22');
INSERT INTO `storeup` (`addtime`, `userid`, `refid`, `tablename`, `name`, `picture`, `type`) VALUES ('2025-12-17 20:18:09', 5, 56, 'course', '零基础入门摄影全攻略', '/upload/images/photography_course.jpg', '1');
INSERT INTO `news` (`addtime`, `title`, `introduction`, `picture`, `content`) VALUES ('2025-12-17 09:15:23', '关于2025年春节放假安排的通知', '根据国家法定节假日安排，结合公司实际情况，现将2025年春节放假安排及相关事宜通知如下。', 'http://localhost:8080/springboot002/upload/公告_9.png', '全体员工：\n经公司研究决定，2025年春节放假安排为：2月15日（星期六）至2月23日（星期日），共9天。2月24日（星期一）正式上班。\n请各部门妥善安排好工作，在放假前做好安全检查，关好门窗、电源。节日期间注意出行安全，预祝大家新春愉快！');
INSERT INTO `news` (`addtime`, `title`, `introduction`, `picture`, `content`) VALUES ('2025-12-17 14:30:05', '公司2025年度优秀员工评选活动启动', '为表彰先进，树立榜样，公司正式启动2025年度优秀员工评选活动，具体评选细则请见内文。', 'http://localhost:8080/springboot002/upload/公告_34.png', '各部门：\n2025年度优秀员工评选活动现已开始。评选标准包括：工作业绩、团队协作、创新能力、敬业精神等。\n请各部门于12月25日前将推荐名单及材料报送至人力资源部。公司将组织评审委员会进行综合评议，最终结果将于年终表彰大会上公布并给予奖励。');
INSERT INTO `news` (`addtime`, `title`, `introduction`, `picture`, `content`) VALUES ('2025-12-17 11:05:47', '新版本办公系统上线及使用培训通知', '为提高办公效率，新版协同办公系统将于2026年1月1日正式上线，相关培训安排已确定。', 'http://localhost:8080/springboot002/upload/公告_29.png', '各位同事：\n新版协同办公系统（V3.0）已完成开发和测试，定于2026年1月1日零点切换上线。新系统整合了流程审批、项目管理、即时通讯等功能。\n为帮助大家快速掌握，信息部将于12月20日、21日下午14:00-17:00在第三会议室组织两场培训，请各部门派代表参加。');
INSERT INTO `news` (`addtime`, `title`, `introduction`, `picture`, `content`) VALUES ('2025-12-17 16:45:12', '关于加强冬季消防安全管理的提醒', '冬季天干物燥，是火灾高发期。为确保公司及员工生命财产安全，特发布消防安全管理提醒。', 'http://localhost:8080/springboot002/upload/公告_10.png', '安全通告：\n近期气候干燥，用电负荷增大，火灾风险升高。请各部门及各位同事务必做到：\n1. 下班前检查办公设备电源是否关闭。\n2. 不在办公区域使用大功率违规电器。\n3. 保持消防通道畅通，勿堆放杂物。\n4. 熟悉本楼层消防器材位置及疏散路线。\n总务部将于本周进行消防安全抽查。');
INSERT INTO `news` (`addtime`, `title`, `introduction`, `picture`, `content`) VALUES ('2025-12-17 10:20:38', '2025年第四季度团建活动征集意见', '为丰富员工业余生活，增强团队凝聚力，现就2025年第四季度团建活动形式及地点向大家征集意见。', 'http://localhost:8080/springboot002/upload/公告_6.png', '各位同事：\n计划于12月下旬组织第四季度团队建设活动。现有几个备选方案：A. 郊区温泉度假；B. 室内主题拓展；C. 公益环保徒步。\n请大家于12月20日前通过内部邮件或向部门负责人反馈您的意向。公司将根据多数意见确定最终方案并发布具体通知。');
INSERT INTO `config` (`name`, `value`) VALUES ('首页主推活动', 'http://localhost:8080/springboot002/upload/轮播图_31.png');
INSERT INTO `config` (`name`, `value`) VALUES ('新品上市', 'http://localhost:8080/springboot002/upload/轮播图_5.png');
INSERT INTO `config` (`name`, `value`) VALUES ('限时优惠', 'http://localhost:8080/springboot002/upload/轮播图_16.png');
INSERT INTO `config` (`name`, `value`) VALUES ('会员专享', 'http://localhost:8080/springboot002/upload/轮播图_3.png');
INSERT INTO `config` (`name`, `value`) VALUES ('年终大促', 'http://localhost:8080/springboot002/upload/轮播图_8.png');
INSERT INTO `token` (`userid`, `username`, `tablename`, `role`, `token`, `addtime`, `expiratedtime`) VALUES (1, '张伟', 'yonghu', '用户', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsImV4cCI6MTczNDQ0ODAwMH0.abc123def456', '2025-12-17 09:15:22', '2025-12-17 10:15:22');
INSERT INTO `token` (`userid`, `username`, `tablename`, `role`, `token`, `addtime`, `expiratedtime`) VALUES (2, '李娜', 'yonghu', '用户', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsImV4cCI6MTczNDQ1MjAwMH0.ghi789jkl012', '2025-12-17 14:30:45', '2025-12-17 15:30:45');
INSERT INTO `token` (`userid`, `username`, `tablename`, `role`, `token`, `addtime`, `expiratedtime`) VALUES (3, '王芳', 'yonghu', '用户', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsImV4cCI6MTczNDQ1NjAwMH0.mno345pqr678', '2025-12-17 11:05:18', '2025-12-17 12:05:18');
INSERT INTO `token` (`userid`, `username`, `tablename`, `role`, `token`, `addtime`, `expiratedtime`) VALUES (4, '刘洋', 'yonghu', '用户', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjQsImV4cCI6MTczNDQ2MDAwMH0.stu901vwx234', '2025-12-17 16:45:33', '2025-12-17 17:45:33');
INSERT INTO `token` (`userid`, `username`, `tablename`, `role`, `token`, `addtime`, `expiratedtime`) VALUES (5, '陈静', 'yonghu', '用户', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjUsImV4cCI6MTczNDQ2NDAwMH0.yza567bcd890', '2025-12-17 20:20:07', '2025-12-17 21:20:07');
INSERT INTO `users` (`username`, `password`, `role`, `addtime`) VALUES ('admin', 'e10adc3949ba59abbe56e057f20f883e', '超级管理员', '2025-12-17 09:15:23');
INSERT INTO `users` (`username`, `password`, `role`, `addtime`) VALUES ('zhangsan', '25d55ad283aa400af464c76d713c07ad', '内容编辑', '2025-12-17 11:30:45');
INSERT INTO `users` (`username`, `password`, `role`, `addtime`) VALUES ('lisi', 'e99a18c428cb38d5f260853678922e03', '普通用户', '2025-12-17 14:05:17');
INSERT INTO `users` (`username`, `password`, `role`, `addtime`) VALUES ('wangwu', '5f4dcc3b5aa765d61d8327deb882cf99', '审核员', '2025-12-17 16:48:32');
INSERT INTO `users` (`username`, `password`, `role`, `addtime`) VALUES ('zhaoliu', '96e79218965eb72c92a549dd5a330112', '普通用户', '2025-12-17 21:20:05');
INSERT INTO yonghu (addtime, zhanghao, touxiang, mima, xingbie, lianxifangshi, nicheng) VALUES ('2025-12-17 09:15:23', 'zhangsan', 'http://localhost:8080/springboot002/upload/头像_2.png', 'e10adc3949ba59abbe56e057f20f883e', '男', '13800138001', '张三');
INSERT INTO yonghu (addtime, zhanghao, touxiang, mima, xingbie, lianxifangshi, nicheng) VALUES ('2025-12-17 14:30:45', 'lisi', 'http://localhost:8080/springboot002/upload/头像_16.png', 'e10adc3949ba59abbe56e057f20f883e', '女', '13900139002', '李四');
INSERT INTO yonghu (addtime, zhanghao, touxiang, mima, xingbie, lianxifangshi, nicheng) VALUES ('2025-12-17 11:05:18', 'wangwu', 'http://localhost:8080/springboot002/upload/头像_18.png', 'e10adc3949ba59abbe56e057f20f883e', '男', '13600136003', '王五');
INSERT INTO yonghu (addtime, zhanghao, touxiang, mima, xingbie, lianxifangshi, nicheng) VALUES ('2025-12-17 16:45:32', 'zhaoliu', 'http://localhost:8080/springboot002/upload/头像_21.png', 'e10adc3949ba59abbe56e057f20f883e', '女', '13500135004', '赵六');
INSERT INTO yonghu (addtime, zhanghao, touxiang, mima, xingbie, lianxifangshi, nicheng) VALUES ('2025-12-17 20:20:59', 'sunqi', 'http://localhost:8080/springboot002/upload/头像_22.png', 'e10adc3949ba59abbe56e057f20f883e', '男', '13700137005', '孙七');
INSERT INTO chongwuyisheng (addtime, zhanghao, touxiang, mima, xingbie, lianxifangshi, nicheng) VALUES ('2025-12-17 09:15:23', 'dr_zhangwei', 'http://localhost:8080/springboot002/upload/头像_2.png', 'zhangwei123', '男', '13800138001', '张医生');
INSERT INTO chongwuyisheng (addtime, zhanghao, touxiang, mima, xingbie, lianxifangshi, nicheng) VALUES ('2025-12-17 10:30:45', 'dr_liming', 'http://localhost:8080/springboot002/upload/头像_16.png', 'liming456', '男', '13900139002', '李明');
INSERT INTO chongwuyisheng (addtime, zhanghao, touxiang, mima, xingbie, lianxifangshi, nicheng) VALUES ('2025-12-17 14:05:18', 'dr_wangfang', 'http://localhost:8080/springboot002/upload/头像_18.png', 'wangfang789', '女', '13600136003', '王芳医生');
INSERT INTO chongwuyisheng (addtime, zhanghao, touxiang, mima, xingbie, lianxifangshi, nicheng) VALUES ('2025-12-17 16:22:37', 'dr_liuyang', 'http://localhost:8080/springboot002/upload/头像_21.png', 'liuyang101', '男', '13500135004', '刘洋');
INSERT INTO chongwuyisheng (addtime, zhanghao, touxiang, mima, xingbie, lianxifangshi, nicheng) VALUES ('2025-12-17 20:48:59', 'dr_zhaoxin', 'http://localhost:8080/springboot002/upload/头像_22.png', 'zhaoxin202', '女', '13700137005', '赵欣');
INSERT INTO Discusschongwuguanli (addtime, refid, userid, nickname, content, reply) VALUES ('2025-12-17 09:15:23', 1, 1, '爱宠人士小王', '这只金毛犬的毛发护理得真好，请问平时都喂什么牌子的狗粮？', '谢谢夸奖！我们主要喂的是皇家大型犬成犬粮，偶尔会加一些煮熟的鸡胸肉和西兰花。');
INSERT INTO Discusschongwuguanli (addtime, refid, userid, nickname, content, reply) VALUES ('2025-12-17 14:30:45', 2, 2, '喵星人守护者', '猫咪绝育后需要注意哪些事项？伤口大概多久能愈合？', '术后需要戴好伊丽莎白圈防止舔舐伤口，保持环境清洁干燥。一般7-10天拆线，完全恢复需要2-3周。');
INSERT INTO Discusschongwuguanli (addtime, refid, userid, nickname, content, reply) VALUES ('2025-12-17 11:05:18', 3, 3, '家有萌宠', '小兔子可以洗澡吗？感觉它最近身上有点味道。', '幼兔和体质弱的兔子不建议洗澡。可以用宠物专用的干洗粉或者湿巾局部清洁。平时注意保持笼舍卫生。');
INSERT INTO Discusschongwuguanli (addtime, refid, userid, nickname, content, reply) VALUES ('2025-12-17 16:42:07', 4, 4, '宠物医生老张', '定期驱虫非常重要，尤其是春夏季节。大家一般多久给狗狗做一次驱虫？', '体外驱虫建议每月一次，体内驱虫每三个月一次。如果经常去草地玩耍，驱虫频率可以适当增加。');
INSERT INTO Discusschongwuguanli (addtime, refid, userid, nickname, content, reply) VALUES ('2025-12-17 20:18:56', 5, 5, '新手养狗指南', '三个月大的小狗到家后一直叫，晚上吵得睡不着，该怎么办？', '这是分离焦虑的表现。可以给它一个带有你气味的旧衣服，准备一个舒适的窝，晚上开一盏小夜灯，慢慢适应就好了。');
INSERT INTO chongwuguanli (addtime, chongwumingcheng, tupian, xingbie, xihao, xiangqing, userid, clicktime, clicknum, thumbsupnum, crazilynum) VALUES ('2025-12-17 09:15:23', '布偶猫', 'http://localhost:8080/springboot002/upload/头像_2.png', '雌性', '玩毛线球、晒太阳', '性格温顺，毛发柔软，蓝眼睛非常漂亮。', 1, '2025-12-17 14:30:45', 128, 45, 2);
INSERT INTO chongwuguanli (addtime, chongwumingcheng, tupian, xingbie, xihao, xiangqing, userid, clicktime, clicknum, thumbsupnum, crazilynum) VALUES ('2025-12-17 10:08:17', '金毛寻回犬', 'http://localhost:8080/springboot002/upload/头像_16.png', '雄性', '捡球、游泳、散步', '聪明友善，是优秀的家庭伴侣犬。', 2, '2025-12-17 16:22:18', 256, 89, 1);
INSERT INTO chongwuguanli (addtime, chongwumingcheng, tupian, xingbie, xihao, xiangqing, userid, clicktime, clicknum, thumbsupnum, crazilynum) VALUES ('2025-12-17 11:45:55', '仓鼠', 'http://localhost:8080/springboot002/upload/头像_18.png', '雄性', '跑轮、藏食物', '体型小巧，夜间活动，需要专门的笼子和垫料。', 3, '2025-12-17 20:05:33', 75, 32, 0);
INSERT INTO chongwuguanli (addtime, chongwumingcheng, tupian, xingbie, xihao, xiangqing, userid, clicktime, clicknum, thumbsupnum, crazilynum) VALUES ('2025-12-17 13:20:41', '柯基犬', 'http://localhost:8080/springboot002/upload/头像_21.png', '雌性', '短跑、吃零食', '腿短身长，性格活泼，拥有标志性的“蜜桃臀”。', 4, '2025-12-17 18:40:12', 312, 120, 5);
INSERT INTO chongwuguanli (addtime, chongwumingcheng, tupian, xingbie, xihao, xiangqing, userid, clicktime, clicknum, thumbsupnum, crazilynum) VALUES ('2025-12-17 15:33:09', '玄凤鹦鹉', 'http://localhost:8080/springboot002/upload/头像_22.png', '雄性', '吹口哨、梳理羽毛', '头上有冠羽，脸颊有橙色斑点，可以学习吹简单旋律。', 5, '2025-12-17 22:15:28', 94, 41, 3);
INSERT INTO Discussxuexiguanli (refid, userid, nickname, content, reply) VALUES (1, 101, '张三', '这篇关于时间管理的文章非常实用，特别是番茄工作法部分，让我工作效率提升了不少。', '感谢认可！可以尝试结合四象限法则，效果会更好。');
INSERT INTO Discussxuexiguanli (refid, userid, nickname, content, reply) VALUES (1, 102, '李四', '文章中的GTD流程图有点复杂，有没有更简化的入门步骤推荐？', '可以先从收集和整理两个步骤开始，熟练后再加入其他环节。');
INSERT INTO Discussxuexiguanli (refid, userid, nickname, content, reply) VALUES (2, 103, '王老师', '第三章的课后习题难度梯度设置得很好，能有效巩固知识点。', '是的，我们特意设计了基础、进阶和挑战三个层次。');
INSERT INTO Discussxuexiguanli (refid, userid, nickname, content, reply) VALUES (3, 104, '学霸小刘', '这个学习计划模板很全面，但每日任务量对新手是否有点多？建议出一个轻量版。', '好建议！我们正在规划一个“21天习惯养成”轻量计划模板。');
INSERT INTO Discussxuexiguanli (refid, userid, nickname, content, reply) VALUES (2, 105, '赵同学', '视频课程里关于记忆曲线的讲解非常清晰，终于明白为什么要定期复习了。', NULL);
INSERT INTO xuexiguanli (addtime, biaoti, tupian, xiangqing, shangchuanshijian, clicktime, clicknum, thumbsupnum, crazilynum, sfsh, shhf) VALUES ('2025-12-17 09:15:23', 'Java编程入门指南', 'http://localhost:8080/springboot002/upload/头像_2.png', '本指南详细介绍了Java语言的基础语法、面向对象编程核心概念以及开发环境的搭建，适合零基础学习者。', '2025-12-17', '2025-12-17 14:30:45', 156, 42, 3, '是', '内容详实，符合规范。');
INSERT INTO xuexiguanli (addtime, biaoti, tupian, xiangqing, shangchuanshijian, clicktime, clicknum, thumbsupnum, crazilynum, sfsh, shhf) VALUES ('2025-12-17 10:08:05', '数据库设计原理与实践', 'http://localhost:8080/springboot002/upload/头像_16.png', '深入讲解关系型数据库的设计范式、ER图绘制、SQL优化技巧以及常见数据库系统的应用场景。', '2025-12-17', '2025-12-17 16:22:18', 89, 28, 1, '是', '专业性强，案例丰富。');
INSERT INTO xuexiguanli (addtime, biaoti, tupian, xiangqing, shangchuanshijian, clicktime, clicknum, thumbsupnum, crazilynum, sfsh, shhf) VALUES ('2025-12-17 11:45:37', 'Web前端开发框架对比', 'http://localhost:8080/springboot002/upload/头像_18.png', '对比分析Vue.js、React和Angular三大主流前端框架的特点、适用场景及生态系统，帮助开发者做出技术选型。', '2025-12-17', '2025-12-17 20:05:12', 203, 67, 5, '是', '分析客观，具有参考价值。');
INSERT INTO xuexiguanli (addtime, biaoti, tupian, xiangqing, shangchuanshijian, clicktime, clicknum, thumbsupnum, crazilynum, sfsh, shhf) VALUES ('2025-12-17 13:20:51', 'Python数据分析实战', 'http://localhost:8080/springboot002/upload/头像_21.png', '使用Pandas、NumPy和Matplotlib等库，通过真实数据集演示数据清洗、分析和可视化的完整流程。', '2025-12-17', '2025-12-17 18:40:29', 312, 95, 2, '否', '待补充案例数据源说明。');
INSERT INTO xuexiguanli (addtime, biaoti, tupian, xiangqing, shangchuanshijian, clicktime, clicknum, thumbsupnum, crazilynum, sfsh, shhf) VALUES ('2025-12-17 15:55:14', '软件项目管理方法论', 'http://localhost:8080/springboot002/upload/头像_22.png', '系统介绍敏捷开发、瀑布模型等主流项目管理方法，涵盖需求管理、进度控制、团队协作等核心环节。', '2025-12-17', '2025-12-17 22:15:03', 78, 31, 0, '是', '结构清晰，理论与实践结合。');
INSERT INTO jibingshangchuan (chongwumingcheng, bingqingmiaoshu, tupianshangchuan, buchong, fabushijian, userid, sfsh, shhf) VALUES ('豆豆', '近期食欲不振，精神萎靡，鼻头干燥，偶尔有咳嗽症状，怀疑是呼吸道感染。', 'http://localhost:8080/springboot002/upload/头像_2.png', '已服用宠物感冒药三天，未见明显好转。', '2025-12-17', 1, '是', '经检查，确认为轻度上呼吸道感染，建议继续用药并注意保暖。');
INSERT INTO jibingshangchuan (chongwumingcheng, bingqingmiaoshu, tupianshangchuan, buchong, fabushijian, userid, sfsh, shhf) VALUES ('布丁', '发现右后腿不敢着地，走路跛行，触摸患处有疼痛反应，无外伤。', 'http://localhost:8080/springboot002/upload/头像_16.png', '从沙发上跳下后出现此症状。', '2025-12-17', 2, '是', 'X光片显示无骨折，疑似肌肉拉伤，建议限制活动，静养观察。');
INSERT INTO jibingshangchuan (chongwumingcheng, bingqingmiaoshu, tupianshangchuan, buchong, fabushijian, userid, sfsh, shhf) VALUES ('可乐', '频繁抓挠耳朵，耳道内有大量黑褐色分泌物，伴有异味。', 'http://localhost:8080/springboot002/upload/头像_18.png', '每周洗澡一次，但未专门清理过耳道。', '2025-12-17', 3, '否', NULL);
INSERT INTO jibingshangchuan (chongwumingcheng, bingqingmiaoshu, tupianshangchuan, buchong, fabushijian, userid, sfsh, shhf) VALUES ('糯米', '呕吐腹泻持续两天，呕吐物为未消化食物，粪便稀薄呈水样。', 'http://localhost:8080/springboot002/upload/头像_21.png', '发病前曾误食过少量巧克力。', '2025-12-17', 4, '是', '已排除巧克力中毒，诊断为急性肠胃炎，需禁食禁水12小时，后喂食流质食物。');
INSERT INTO jibingshangchuan (chongwumingcheng, bingqingmiaoshu, tupianshangchuan, buchong, fabushijian, userid, sfsh, shhf) VALUES ('元宝', '眼睛红肿，流泪增多，眼部分泌物呈黄绿色粘稠状。', 'http://localhost:8080/springboot002/upload/头像_22.png', '症状出现约一天，猫咪显得烦躁。', '2025-12-17', 5, '否', NULL);
INSERT INTO zhenduan (addtime, chongwumingcheng, zhengzhuang, zhiliaojianyi, zhenduanshijian, zhenduanren, lianxifangshi, userid) VALUES ('2025-12-17 09:15:30', '豆豆', '近三天食欲不振，精神萎靡，偶尔呕吐未消化食物，排便正常但偏软。', '建议进行血常规和粪便检查，排除肠胃炎或寄生虫感染。可先喂食易消化处方粮，少量多餐，并密切观察呕吐频率。', '2025-12-17', '张明医生', '13800138001', 1);
INSERT INTO zhenduan (addtime, chongwumingcheng, zhengzhuang, zhiliaojianyi, zhenduanshijian, zhenduanren, lianxifangshi, userid) VALUES ('2025-12-17 14:20:45', '布丁', '右后腿跛行，触摸患处有疼痛反应，拒绝跳跃和奔跑，无外伤。', '建议拍摄X光片，检查是否有骨折或韧带损伤。在确诊前限制活动，可使用宠物专用关节营养补充剂。', '2025-12-17', '李芳医生', '13912345678', 2);
INSERT INTO zhenduan (addtime, chongwumingcheng, zhengzhuang, zhiliaojianyi, zhenduanshijian, zhenduanren, lianxifangshi, userid) VALUES ('2025-12-17 11:05:10', '可乐', '频繁抓挠耳朵，耳道内有大量黑褐色分泌物，伴有异味，摇头。', '诊断为耳螨感染可能性大。建议进行耳道分泌物镜检，确诊后使用专用耳药水清洗并上药，每日一次，连续一周。', '2025-12-17', '王伟医生', '13698765432', 3);
INSERT INTO zhenduan (addtime, chongwumingcheng, zhengzhuang, zhiliaojianyi, zhenduanshijian, zhenduanren, lianxifangshi, userid) VALUES ('2025-12-17 16:45:22', '雪球', '打喷嚏，流黄色脓鼻涕，眼部分泌物增多，体温测量为39.8℃。', '疑似上呼吸道感染。建议进行病毒筛查（猫鼻支/杯状病毒），对症使用抗生素和抗病毒药物，并注意保暖和营养支持。', '2025-12-17', '赵敏医生', '13555556666', 4);
INSERT INTO zhenduan (addtime, chongwumingcheng, zhengzhuang, zhiliaojianyi, zhenduanshijian, zhenduanren, lianxifangshi, userid) VALUES ('2025-12-17 10:30:05', '旺财', '皮肤出现圆形脱毛区域，皮屑增多，患处皮肤发红，宠物有舔舐行为。', '疑似真菌性皮肤病（如犬小孢子菌感染）。建议进行伍德氏灯检查和皮肤刮片镜检，确诊后使用抗真菌喷剂和外用药膏，并佩戴伊丽莎白圈。', '2025-12-17', '刘强医生', '13700001111', 5);
INSERT INTO Discusszixun (addtime, refid, userid, nickname, content, reply) VALUES ('2025-12-17 09:15:23', 1, 1, '张伟', '医生您好，我最近感觉胸闷气短，尤其是在运动后，请问这可能是什么原因？需要做哪些检查？', '您好，根据您的描述，胸闷气短可能与心肺功能有关。建议您先到心内科或呼吸科就诊，可能需要做心电图、心脏彩超和肺功能检查。请避免剧烈运动，注意休息。');
INSERT INTO Discusszixun (addtime, refid, userid, nickname, content, reply) VALUES ('2025-12-17 11:30:45', 2, 2, '李娜', '请问贵院皮肤科周末有专家门诊吗？我想预约治疗面部痤疮。', '您好，我院皮肤科周六上午有专家门诊，可以通过医院官方APP或小程序进行预约。治疗痤疮需要根据具体类型制定方案，建议提前预约并带上既往病历。');
INSERT INTO Discusszixun (addtime, refid, userid, nickname, content, reply) VALUES ('2025-12-17 14:05:18', 3, 3, '王明', '体检报告显示谷丙转氨酶偏高，数值是65U/L，请问这个严重吗？平时需要注意什么？', '您好，谷丙转氨酶轻度升高可能与脂肪肝、饮酒、熬夜或药物影响有关。建议您戒酒、清淡饮食、规律作息，一个月后复查肝功能。如果持续升高，需进一步检查。');
INSERT INTO Discusszixun (addtime, refid, userid, nickname, content, reply) VALUES ('2025-12-17 16:45:02', 4, 4, '赵静', '孩子3岁，最近咳嗽有痰，晚上咳得厉害，吃什么药比较好？需要去医院吗？', '您好，幼儿咳嗽有痰建议先带宝宝到儿科就诊，明确是细菌感染还是病毒感染，切勿自行用药。如果伴有发烧、呼吸急促，请及时就医。夜间可以适当抬高枕头缓解。');
INSERT INTO Discusszixun (addtime, refid, userid, nickname, content, reply) VALUES ('2025-12-17 20:20:37', 5, 5, '刘洋', '我想咨询一下，膝关节疼痛，上下楼梯时加重，是应该看骨科还是康复科？', '您好，膝关节疼痛建议先看骨科，排除结构性损伤如半月板、韧带问题。如果检查无大碍，或处于慢性恢复期，可以转诊康复科进行理疗和功能锻炼。平时注意减少爬楼和负重。');
INSERT INTO zixun (chongwuyisheng, touxiang, xingbie, lianxifangshi, xiangqing, userid, addtime) VALUES ('张明远', 'http://localhost:8080/springboot002/upload/头像_2.png', '男', '13812345678', '资深宠物医生，擅长犬猫内科疾病诊断与治疗，拥有10年临床经验，对待宠物耐心细致。', 1, '2025-12-17 09:15:23');
INSERT INTO zixun (chongwuyisheng, touxiang, xingbie, lianxifangshi, xiangqing, userid, addtime) VALUES ('李思雨', 'http://localhost:8080/springboot002/upload/头像_16.png', '女', '15987654321', '主攻小动物外科手术，尤其在骨科和眼科方面有深入研究，手术成功率高。', 2, '2025-12-17 11:30:45');
INSERT INTO zixun (chongwuyisheng, touxiang, xingbie, lianxifangshi, xiangqing, userid, addtime) VALUES ('王建国', 'http://localhost:8080/springboot002/upload/头像_18.png', '男', '13755556666', '宠物行为学专家，能有效解决犬猫的焦虑、攻击性等行为问题，并提供训练建议。', 3, '2025-12-17 14:05:17');
INSERT INTO zixun (chongwuyisheng, touxiang, xingbie, lianxifangshi, xiangqing, userid, addtime) VALUES ('赵晓雯', 'http://localhost:8080/springboot002/upload/头像_21.png', '女', '18800001111', '擅长异宠（如兔子、仓鼠、鸟类）的诊疗与护理，对稀有宠物疾病有丰富经验。', 4, '2025-12-17 16:48:32');
INSERT INTO zixun (chongwuyisheng, touxiang, xingbie, lianxifangshi, xiangqing, userid, addtime) VALUES ('陈浩', 'http://localhost:8080/springboot002/upload/头像_22.png', '男', '18612349876', '宠物营养师兼全科医生，能为不同年龄、品种的宠物制定科学的饮食和健康管理方案。', 5, '2025-12-17 20:20:59');