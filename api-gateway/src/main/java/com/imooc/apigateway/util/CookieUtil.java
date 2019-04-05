package com.imooc.apigateway.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class CookieUtil {

    public static void set(HttpServletResponse response, String name, String value, Integer maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static Cookie get(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null ) {
            Cookie cookie = Arrays.stream(cookies).filter(it -> name.equals(it.getName())).findFirst().get();
            return cookie;
        }
        return null;
    }
}
