package com.farmershao.www.stockmember.configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DruidConfig ：druid的监控配置
 *
 *
 * @author Shao Yu
 * @since 2018/9/11 16:59
 **/
@Configuration
public class DruidConfig {

    /**
     * 配置监控服务器
     *
     * @return 返回监控注册的servlet对象
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //设置servlet 注册实体
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //设置ip白名单
        bean.addInitParameter("allow", "127.0.0.1");
        //设置ip黑名单， deny优先级大于allow
        bean.addInitParameter("deny", "192.168.10.55");
        //设置控制台管理用户
        bean.addInitParameter("loginUsername", "druid");
        bean.addInitParameter("loginPassword", "123456");
        //是否可以重置数据
        bean.addInitParameter("resetEnable", "false");
        return bean;
    }

    /**
     * 配置服务过滤器
     *
     * @return 返回过滤器配置对象
     */
    @Bean
    public FilterRegistrationBean statFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        bean.addUrlPatterns("/*");
        bean.addInitParameter("exclusions", "*.js,*.gif,*.jps,*.png,*.css,*.ico,/druid/*");
        return bean;
    }
}
