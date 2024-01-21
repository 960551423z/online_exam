package com.camellia.exam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 阿庆
 * @Date: 2024/1/20 16:27
 * 用户分页查询返回给前端信息
 */
@Data
@ApiModel(description = "用户分页查询返回的数据格式")
public class UserVO {
    @ApiModelProperty("主键值")
    private Long id;

    @ApiModelProperty("账号")
    private String userAccount;

    @ApiModelProperty("用户昵称")
    private String userName;

    @ApiModelProperty("用户头像")
    private String userAvatar;

    @ApiModelProperty("用户性别")
    private String userSex;

    @ApiModelProperty("用户角色")
    private String userRole;
}
