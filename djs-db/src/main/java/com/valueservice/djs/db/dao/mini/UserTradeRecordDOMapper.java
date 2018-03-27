package com.valueservice.djs.db.dao.mini;

import com.valueservice.djs.db.entity.mini.UserTradeRecordDO;

public interface UserTradeRecordDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserTradeRecordDO record);

    UserTradeRecordDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTradeRecordDO record);

}