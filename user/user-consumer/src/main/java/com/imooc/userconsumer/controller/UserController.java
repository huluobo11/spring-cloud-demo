package com.imooc.userconsumer.controller;

import com.imooc.usercommon.enums.RoleEnum;
import com.imooc.usercommon.vo.Result;
import com.imooc.userconsumer.dataobject.UserInfo;
import com.imooc.userconsumer.service.UserService;
import com.imooc.userconsumer.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("login")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("buyer")
    public Result buyer(String openid, HttpServletResponse response) {
        // 查询数据库
        UserInfo userInfo = userService.findByOpenid(openid);
        if (Objects.isNull(userInfo)) {
            return Result.error("登录失败", null);
        }
        // 判断角色
        if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
            return Result.error("角色权限有误", null);
        }
        // 设置cookie
        CookieUtil.set(response, "openid", userInfo.getOpenid(), 3600);

        return Result.success();
    }

    @GetMapping("seller")
    public Result seller(String openid, HttpServletRequest request, HttpServletResponse response) {
        // 判断是否已经登录
        Cookie cookie = CookieUtil.get(request, "token");
        if (!Objects.isNull(cookie) && StringUtils.hasText(stringRedisTemplate.opsForValue().get(String.format("token_%s", cookie.getValue())))) {
            return Result.success();
        }
        // 查询数据库
        UserInfo userInfo = userService.findByOpenid(openid);
        if (Objects.isNull(userInfo)) {
            return Result.error("登录失败", null);
        }
        // 判断角色
        if (RoleEnum.SELLER.getCode() != userInfo.getRole()) {
            return Result.error("角色权限有误", null);
        }
        String token = UUID.randomUUID().toString();
        // 保存到reids中
        stringRedisTemplate.opsForValue().set(String.format("token_%s", token), userInfo.getOpenid(), 3600, TimeUnit.SECONDS);
        // 设置cookie
        CookieUtil.set(response, "token",  token, 3600);

        return Result.success();
    }
}
