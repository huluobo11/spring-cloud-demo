package com.imooc.config.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*", filterName = "myfilter")
//@Component
//@ServletComponentScan
public class BusRefreshFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        String url = httpServletRequest.getRequestURI();
        //只过滤/actuator/bus-refresh请求
        if (!url.endsWith("/bus-refresh") && !url.endsWith("/monitor")) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        //使用HttpServletRequest包装原始请求达到修改post请求中body内容的目的
        CustometRequestWrapper requestWrapper = new CustometRequestWrapper(httpServletRequest);
        chain.doFilter(requestWrapper, httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
