package com.camellia.exam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: 阿庆
 * @Date: 2024/1/21 15:38
 */

@Data
@ApiModel(description = "题目分页查询返回的数据格")
public class QuestionVO {
    /**
     * id
     */
    @ApiModelProperty("主键值")
    private Long id;

    /**
     * 题目
     */
    @ApiModelProperty("题目")
    private String title;

    /**
     * 学科名
     */
    @ApiModelProperty("学科")
    private String subjectName;


    /**
     * 题目类型: 0单选，1判断，2多选，3简答
     */
    @ApiModelProperty("题目类型")
    private Integer quesType;

    /**
     * 题目难度
     */
    @ApiModelProperty("题目难度")
    private Integer degree;

    /**
     * 题目分值
     */
    @ApiModelProperty("题目分值")
    private Integer score;

}
