package com.farmershao.www.stockmember.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * MyConfiguration
 *
 * @author Shao Yu
 * @since 2018/9/19 9:35
 **/
@ConfigurationProperties(prefix = "my.test")
@Component
public class MyConfiguration {

    private List<String> names;

    private String sex;

    private int age;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
