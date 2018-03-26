package com.valueservice.djs.db.dao.mini;

import com.valueservice.djs.db.entity.mini.UserVipDO;

import java.util.List;

public interface UserVipDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserVipDO record);

    UserVipDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserVipDO record);

    List<UserVipDO> selectAllList();

}