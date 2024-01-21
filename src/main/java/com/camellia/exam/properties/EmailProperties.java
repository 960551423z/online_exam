package com.camellia.exam.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 阿庆
 * @Date: 2024/1/17 20:16
 *
 */
@Component
@ConfigurationProperties(prefix = "email.info")
@Data
public class EmailProperties {
    // 邮箱发送的基本配置
    private String host;
    private Integer port;
    private String username;
    private String password;
}
