package com.valueservice.djs.db.dao.system;

import com.valueservice.djs.db.entity.system.UserInfoDO;

public interface UserInfoDOMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(UserInfoDO record);

    int insertSelective(UserInfoDO record);

    UserInfoDO selectByPrimaryKey(Long userId);

    UserInfoDO findByLoginName(String loginName);

    int updateByPrimaryKeySelective(UserInfoDO record);

    int updateByPrimaryKey(UserInfoDO record);
}