use homepage;
CREATE TABLE `B_BLOG` (
  `ID` varchar(50) not null comment '主键id',
#   `USER_ID` varchar (50) not null comment '博客持有者',
  `IMAGE_URL` varchar (255) not null comment '图片url',
  `DATE` date not null comment '时间',
  `TITLE` varchar (100) not null comment '标题',
  `TITLE_SECONDARY` varchar (100) not null comment '二级标题',
  `CONTENT` varchar (4000) not null comment '内容',
  `CREATOR_ID` varchar (50)  ,
  `CREATOR_NAME` varchar (150) comment '名字' ,
  `CREATE_TIME` datetime comment '时间' ,
  `UPDATOR_ID` varchar (50) ,
  `UPDATOR_NAME` varchar (150),
  `UPDATE_TIME` datetime ,
  `DELETOR_ID` varchar (50) ,
  `DELETOR_NAME` varchar (150),
  `DELETE_TIME` datetime ,
  `DELETE_FLAG` INTEGER default 0 comment '删除标记',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='博客基本信息';
