package com.camellia.exam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 阿庆
 * @Date: 2024/1/20 15:59
 * 返回给前端的
 */
@Data
@ApiModel(description = "学科查询返回的数据格式")
public class SubjectVO {
    @ApiModelProperty("主键值")
    private Long id;

    @ApiModelProperty("学科名")
    private String subName;

    @ApiModelProperty("学科图片")
    private String imgUrl;

    @ApiModelProperty("年级")
    private String grade;
}
