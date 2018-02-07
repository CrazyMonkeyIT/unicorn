package com.valueservice.djs.service;

import com.valueservice.djs.db.LoginUser;
import com.valueservice.djs.db.repository.LoginUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginUserService {

    @Resource
    private LoginUserRepository loginUserRepository;

    public LoginUser selectByLoginName(String loginName){
        return loginUserRepository.findByLoginName(loginName);
    }
}
