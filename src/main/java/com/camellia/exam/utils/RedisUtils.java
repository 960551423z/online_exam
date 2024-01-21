package com.camellia.exam.utils;

import com.camellia.exam.constant.EmailConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 阿庆
 * @Date: 2024/1/19 11:46
 * redis 工具类, 封装对字符串相关操作
 */

public class RedisUtils {



    public static void set(RedisTemplate<String,Object> redisTemplate,String email, Integer code) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(email,code, EmailConstant.EMAIL_EXPIRATION,TimeUnit.MINUTES);
    }


    // 判断是否有验证码
    public static Object get(RedisTemplate<String,Object> redisTemplate,String email) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(email);
    }



}
