package com.camellia.exam.model.dto.questions;

import lombok.Data;

/**
 * @Author: 阿庆
 * @Date: 2024/1/21 15:15
 * 封装分页相关参数
 */

@Data
public class QuestionsPageDTO {

    // 题目
    private String title;

    // 学科名
    private Long subjectId;

    // 分值
    private Integer score;

    // 难度
    private Integer degree;

    // 题目类型
    private Integer quesType;


    //页码
    private int page;

    //每页显示记录数
    private int pageSize;
}
