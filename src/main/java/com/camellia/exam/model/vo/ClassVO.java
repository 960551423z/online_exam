package com.camellia.exam.model.vo;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: 阿庆
 * @Date: 2024/1/20 19:51
 */
@Data
@ApiModel(description = "班级分页查询返回的数据格式")
public class ClassVO {
    @ApiModelProperty("主键值")
    private Long id;

    @ApiModelProperty("班级名")
    private String className;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
