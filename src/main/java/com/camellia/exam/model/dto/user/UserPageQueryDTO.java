package com.camellia.exam.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 阿庆
 * @Date: 2024/1/17 11:04
 * 前端传过来的查询条件(包括页码和记录数)
 */

@Data
public class UserPageQueryDTO implements Serializable {
    // 用户账号
    private String userAccount;

    // 用户姓名
    private String userName;

    //页码
    private int page;

    //每页显示记录数
    private int pageSize;

}
