package com.camellia.exam;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.camellia.exam.properties.EmailProperties;
import com.camellia.exam.properties.JwtProperties;
import com.camellia.exam.utils.EmailUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

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

    @Test
    void test2() {
        // 保证极短时间内生成的code也不一样
        Integer counter = 0;

        // 生成当前时间戳
        long timestamp = System.currentTimeMillis();

        // 将时间戳和计数器组合成种子
        long seed = timestamp + counter;

        // 将种子传递给Random类
        Random random = new Random(seed);

        // 生成六位数验证码
        int code = random.nextInt(900000) + 100000;

        // 增加计数器
        counter++;

        System.out.println("生成的六位数验证码为: " + code);
    }


    @Autowired
    private EmailProperties emailProperties;
    @Autowired
    private JwtProperties jwtProperties;
    @Test
    void test3() {
        MailAccount account = new MailAccount();
        System.out.println(jwtProperties);
        account.setHost(emailProperties.getHost());
        account.setPort(emailProperties.getPort());
        account.setAuth(true);
        account.setFrom(emailProperties.getUsername());
        account.setUser(emailProperties.getPassword());

        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.FILE));
        Template template = engine.getTemplate("mail.html");
        String code = EmailUtils.rangeCode().toString();

        MailUtil.send("960551423@qq.com","验证码",template.render(Dict.create().set("code",code)),true);
    }


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Test
    void test4() {

    }
}
