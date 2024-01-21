-- 创建库
create database if not exists my_db;

-- 切换库
use my_db;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           unique comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    userName     varchar(256)                           null comment '用户姓名',
    userSex      char(2)                                null comment '用户性别',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userStatus   bigint default 1                       comment '1启用，0禁用',
    userRole     varchar(256) default 'user'            not null comment '用户角色：teacher/admin/student',
    joinTime     datetime     default null              comment '加入班级时间',
    userGrade    varchar(20)  default null              comment '年级',
    updateUser   bigint       default null              comment '最后修改人ID',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除'
) comment '用户' collate = utf8mb4_unicode_ci;



# 学科表
create table subject (
                         id           bigint auto_increment comment 'id' primary key,
                         subName      varchar(100)                            null comment '学科名',
                         imgUrl       varchar(1024)                           null comment '学科图片',
                         grade        varchar(20)                             null comment '学科的年级',
                         updateId     bigint                                  null comment '修改人ID',
                         createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
                         updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
                         isDelete     tinyint      default 0                 not null comment '是否删除'
) comment '学科' collate = utf8mb4_unicode_ci;



# 试题表
create table questions(
                          id           bigint auto_increment comment 'id' primary key,
                          title        varchar(255)                          null comment '题目',
                          optionA      varchar(100)                          null comment '选项A',
                          optionB      varchar(100)                          null comment '选项B',
                          optionC      varchar(100)                          null comment '选项C',
                          optionD      varchar(100)                          null comment '选项D',
                          answer       varchar(100)                          null comment '答案',
                          parse        varchar(255)                          null comment '答案解析',
                          subjectId    bigint                                null comment '学科id',
                          examPaperId  bigint                                null comment '试卷id',
                          score        int                                   null comment '题目分值',
                          degree       int                                   null comment '题目难度',
                          createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
                          updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
                          isDelete     tinyint      default 0                 not null comment '是否删除',
                          foreign key(subjectId) references subject(id),
                          foreign key(examPaperId) references exam_paper(id)
) comment '试题' collate = utf8mb4_unicode_ci;



# 试卷表
create table exam_paper(
                           id           bigint auto_increment comment 'id' primary key,
                           title        varchar(100)                           null comment '试卷标题',
                           totalScore   int                                    null comment '试卷总分',
                           subjectID    bigint                                 null comment '学科id',
                           startTime    datetime                               null comment '考试开始时间',
                           endTime      datetime                               null comment '考试结束时间',
                           status       int                                    null comment '0为未批改，1批改完成，2考试未开始',
                           degree       tinyint                                null comment '考试难度（星级）',
                           createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
                           updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
                           isDelete     tinyint      default 0                 not null comment '是否删除',
                           foreign  key(subjectID) references subject(id)
) comment '试卷' collate = utf8mb4_unicode_ci;


# 分数表
create table score (
                       id           bigint auto_increment comment 'id' primary key,
                       userId       bigint                                 null comment '用户id',
                       examPaperId  bigint                                 null comment '试卷id',
                       result       int                                    null comment '最终分数',
                       autoResult   int                                    null comment '电脑自动判题分数',
                       manualResult int                                    null comment '手动判题分数',
                       useTime      int                                    null comment '用时时长（分钟）',
                       finishTime   int                                    null comment '批改完成时间',
                       status       int                                    null comment '0未交，1已交',
                       foreign key(userId) references user(id),
                       foreign key(examPaperId) references exam_paper(id)
) comment '分数' collate = utf8mb4_unicode_ci;


# 班级表
create table class (
                       id           bigint auto_increment comment 'id' primary key,
                       className    varchar(50)                           null comment '班级名称',
                       subjectId    bigint                                null comment '学科id',
                       userId       bigint                                null comment '教师id',
                       createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
                       updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
                       isDelete     tinyint      default 0                 not null comment '是否删除',
                       foreign key(subjectID) references subject(id),
                       foreign key(userId) references user(id)
) comment '班级' collate = utf8mb4_unicode_ci;