package com.camellia.exam.model.dto.subject;

import lombok.Data;

/**
 * @Author: 阿庆
 * @Date: 2024/1/20 14:01
 * 封装前端返回给后端的类型
 */

@Data
public class SubjectDTO {

    /**
     * 学科名
     */
    private String subName;

    /**
     * 学科图片
     */
    private String imgUrl;

    /**
     * 学科的年级
     */
    private String grade;



}
