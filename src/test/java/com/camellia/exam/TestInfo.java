package com.camellia.exam;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @Author: 阿庆
 * @Date: 2024/1/17 10:21

 */


public class TestInfo {

    @Test
    void test1() {
        Date date = new Date();
        LocalDateTime now = LocalDateTime.now();
        System.out.println("d: " + date);
        System.out.println("n: "+now.atZone(ZoneId.systemDefault()));
    }
}
