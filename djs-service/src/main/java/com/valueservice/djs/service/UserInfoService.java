package com.valueservice.djs.service;

import com.valueservice.djs.db.dao.system.UserInfoDOMapper;
import com.valueservice.djs.db.entity.system.UserInfoDO;
import org.springframework.stereotype.Service;
import util.SHA256;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service
public class UserInfoService {

    @Resource
    private UserInfoDOMapper userInfoDOMapper;

    public UserInfoDO selectByLoginName(String loginName){
        return userInfoDOMapper.findByLoginName(loginName);
    }

    public boolean insertOrUpdate(UserInfoDO userInfoDO){
        int rows = 0;
        if(userInfoDO.getUserId() == null){
            userInfoDO.setLoginPwd(SHA256.encrypt("123456"));
            userInfoDO.setUserStatus(1);
            userInfoDO.setCreateTime(new Timestamp(System.currentTimeMillis()));
            rows = userInfoDOMapper.insertSelective(userInfoDO);
        }else {
            userInfoDO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            rows = userInfoDOMapper.updateByPrimaryKeySelective(userInfoDO);
        }
        return (rows > 0);
    }
}
