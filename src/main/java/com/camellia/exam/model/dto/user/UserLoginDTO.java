package com.camellia.exam.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 阿庆
 * @Date: 2024/1/16 16:55
 */
@Data
@ApiModel(description = "用户登录时传递的数据模型")
public class UserLoginDTO implements Serializable {

    @ApiModelProperty("账号")
    private String userAccount;

    @ApiModelProperty("密码")
    private String userPassword;

}
