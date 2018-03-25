package com.valueservice.djs.db.dao.mini;

import com.valueservice.djs.db.entity.mini.UserVipDO;

public interface UserVipDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserVipDO record);

    int insertSelective(UserVipDO record);

    UserVipDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserVipDO record);

    int updateByPrimaryKey(UserVipDO record);
}