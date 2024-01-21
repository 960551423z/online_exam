package com.camellia.exam.model.dto.classdto;

import lombok.Data;

/**
 * @Author: 阿庆
 * @Date: 2024/1/20 19:43
 * 分页查询所需要的参数
 */
@Data
public class ClassPageDTO {

    // 学科
    private String className;

    //页码
    private int page;

    //每页显示记录数
    private int pageSize;
}
