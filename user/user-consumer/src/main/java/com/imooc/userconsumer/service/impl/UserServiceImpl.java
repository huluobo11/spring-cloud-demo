package com.imooc.userconsumer.service.impl;

import com.imooc.userconsumer.dataobject.UserInfo;
import com.imooc.userconsumer.repository.UserRepository;
import com.imooc.userconsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional(propagation = Propagation.NEVER)
    public UserInfo findByOpenid(String openid) {
        return userRepository.findByOpenid(openid);
    }
}
