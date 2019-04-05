package com.imooc.userconsumer.service;

import com.imooc.userconsumer.dataobject.UserInfo;

public interface UserService {
    /**
     * 根据openiod 查询用户
     * @param openid
     */
    UserInfo findByOpenid(String openid);
}
