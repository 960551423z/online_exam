package com.camellia.exam.model.dto.subject;

import lombok.Data;

/**
 * @Author: 阿庆
 * @Date: 2024/1/20 14:19
 * 学科分页查询相关的dto
 */
@Data
public class SubjectPageDTO {

    // 学科
    private String subName;

    // 年级
    private String grade;

    //页码
    private int page;

    //每页显示记录数
    private int pageSize;
}
