package com.farmershao.www.stockmember.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * TestImportConfiguration
 *
 * @author Shao Yu
 * @since 2018/9/19 17:28
 **/
@PropertySource("classpath:config/my-config.properties")
@Configuration
public class TestImportConfiguration {

    @Value("${test.import.code}")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
