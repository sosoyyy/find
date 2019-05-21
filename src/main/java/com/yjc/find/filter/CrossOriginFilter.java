package com.yjc.find.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * iframe,ajax跨域（前后端分离）
 */
@WebFilter(urlPatterns = "/*")
public class CrossOriginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;

        //iframe跨域
        //response.setHeader("X-Frame-Options", "ALLOW-FROM http://localhost:8080/find/html");
        //响应数据跨域,*表示所有地址都可访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        //请求方法跨域
        response.setHeader("Access-Control-Allow-Methods", "POST,PUT,GET,DELETE");
        //在86400秒(一天)内，不需要再发送预检验请求
        response.setHeader("Access-Control-Max-Age", "86400");
        //允许跨域请求包含content-type头
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,authorization");

        filterChain.doFilter(request,response);
    }

}

