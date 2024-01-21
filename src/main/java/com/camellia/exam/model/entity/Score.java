package com.camellia.exam.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName score
 */
@TableName(value ="score")
@Data
public class Score implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userid;

    /**
     * 试卷id
     */
    private Long examPaperId;

    /**
     * 最终分数
     */
    private Integer result;

    /**
     * 电脑自动判题分数
     */
    private Integer autoResult;

    /**
     * 手动判题分数
     */
    private Integer manualResult;

    /**
     * 用时时长（分钟）
     */
    private Integer useTime;

    /**
     * 批改完成时间
     */
    private Integer finishTime;

    /**
     * 0未交，1已交
     */
    private Integer status;


}