package com.camellia.exam.model.dto.questions;

import lombok.Data;

import java.util.Date;

/**
 * @Author: 阿庆
 * @Date: 2024/1/21 11:03
 * 前端传过来的试题格式
 */
@Data
public class QuestionsDTO {
    /**
     * 题目
     */
    private String title;

    /**
     * 选项A
     */
    private String optionA;

    /**
     * 选项B
     */
    private String optionB;

    /**
     * 选项C
     */
    private String optionC;

    /**
     * 选项D
     */
    private String optionD;

    /**
     * 答案
     */
    private String answer;

    /**
     * 答案解析
     */
    private String parse;

    /**
     * 学科id
     */
    private Long subjectId;

    /**
     * 题目分值
     */
    private Integer score;

    /**
     * 题目难度
     */
    private Integer degree;

    /**
     * 题目类型: 0单选，1判断，2多选，3简答
     */
    private Integer quesType;


}
