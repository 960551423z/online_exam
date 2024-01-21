package com.camellia.exam;

import com.camellia.exam.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ExamApplicationTests {

    @Test
    void contextLoads() {

    }


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;




    @Test
    void test1() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

        valueOperations.set("lisi",1214);
        Object lisi = valueOperations.get("lisi");
        System.out.println(lisi.getClass());
    }

}
