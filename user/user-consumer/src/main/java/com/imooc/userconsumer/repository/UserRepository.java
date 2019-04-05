package com.imooc.userconsumer.repository;

import com.imooc.userconsumer.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openid);
}
