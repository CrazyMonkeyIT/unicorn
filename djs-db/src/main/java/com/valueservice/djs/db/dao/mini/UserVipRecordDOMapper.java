package com.valueservice.djs.db.dao.mini;

import com.valueservice.djs.db.entity.mini.UserVipRecordDO;

public interface UserVipRecordDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserVipRecordDO record);

    UserVipRecordDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserVipRecordDO record);

}