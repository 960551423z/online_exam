package com.camellia.exam.model.dto.user;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 阿庆
 * @Date: 2024/1/17 9:43
 * 管理员后台添加时需要的封装类
 */

@Data
public class UserDTO implements Serializable {

    // 用户id
    private Long id;

    // 用户账号
    private String userAccount;

    // 用户密码
    private String userPassword;

    // 用户角色
    private String userRole;

    // 用户姓名
    private String userName;

    // 用户性别
    private String userSex;

    // 用户年级
    private String userGrade;

    // 用户头像
    private String userAvatar;

    // 用户输入的验证码
    private Integer code;


}
