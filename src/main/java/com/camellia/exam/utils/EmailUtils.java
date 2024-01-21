package com.camellia.exam.utils;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.camellia.exam.constant.EmailConstant;
import com.camellia.exam.properties.EmailProperties;

import java.util.Random;

/**
 * @Author: 阿庆
 * @Date: 2024/1/17 20:10
 * 邮箱工具包
 */

public class EmailUtils {


    public static void send(EmailProperties emailProperties,String userAccount,Integer code) {

        MailAccount account = new MailAccount();
        account.setHost(emailProperties.getHost());
        account.setPort(emailProperties.getPort());
        account.setAuth(true);
        account.setUser(emailProperties.getUsername());
        account.setFrom(emailProperties.getUsername());
        account.setPass(emailProperties.getPassword());


        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.FILE));
        Template template = engine.getTemplate(EmailConstant.EMAIL_TEMPLATE);


        MailUtil.send(account,userAccount, EmailConstant.EMAIL_OBJECT,template.render(Dict.create().set("code",code.toString())),true);
    }

    // 随机生成六位数的验证码
    static Integer counter = 0;

    public static Integer rangeCode() {
        // 保证极短时间内生成的code也不一样

        if (counter > 99999 ) {
            counter = 0;
        }

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

        return code;
    }
}
