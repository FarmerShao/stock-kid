package com.farmershao.www.stockmember.configuration;

import com.farmershao.www.stockmember.Interceptors.HelloInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * HelloMvcConfigureAdapter
 *
 * @author Shao Yu
 * @since 2018/10/23 10:33
 **/
@Component
public class HelloMvcConfigureAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HelloInterceptor())
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
