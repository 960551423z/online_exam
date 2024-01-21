package com.camellia.exam.utils;

import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 阿庆
 * @Date: 2024/1/20 16:04
 * 返回给前端的信息进行脱敏
 */

public class ReturnInfoUtils {
    @SneakyThrows
    public static <T,V> List<V> convert(List<T> list, Class<V> clazz) {
        List<V> newList = new ArrayList<>();
        for (T o : list) {
            V newObj = clazz.newInstance();
            BeanUtils.copyProperties(o,newObj);
            newList.add(newObj);
        }
        return newList;
    }

}
