package com.farmershao.www.stockmember.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AjaxFilter
 *
 * @author Shao Yu
 * @since 2018/10/23 16:48
 **/
@WebFilter(servletNames={"dispatcherServlet"})
public class AjaxFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        System.out.println("AjaxFilter");
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");//设置允许哪些域名应用进行ajax访问
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");
        chain.doFilter(request, response);//调用后续的Filter
    }

    @Override
    public void destroy() {

    }
}
