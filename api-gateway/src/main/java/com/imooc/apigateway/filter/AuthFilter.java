package com.imooc.apigateway.filter;

import com.imooc.apigateway.util.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.util.Objects;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

@Component
public class AuthFilter extends ZuulFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
/*        /order/create 只能卖家访问,cookie中有openid
            /order/finish 只能买家访问0
            /product/list 都可以访问*/

        if ("/order/order/create".equals(request.getRequestURI())) {
            Cookie cookie = CookieUtil.get(request, "openid");
            if (Objects.isNull(cookie) || StringUtils.isEmpty(cookie.getValue())) {
                requestContext.setSendZuulResponse(Boolean.FALSE);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        }

        if ("/order/order/finish".equals(request.getRequestURI())) {
            Cookie cookie = CookieUtil.get(request, "toekn");
            if (Objects.isNull(cookie)
                    || StringUtils.isEmpty(cookie.getValue())
                    || StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format("token_%s", cookie.getValue())))) {
                requestContext.setSendZuulResponse(Boolean.FALSE);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        }

        return null;
    }
}
