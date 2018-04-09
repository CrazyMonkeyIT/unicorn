package com.valueservice.djs.db.dao.system;

import com.valueservice.djs.db.entity.system.MsgEventDO;

public interface MsgEventDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MsgEventDO record);

    int insertSelective(MsgEventDO record);

    MsgEventDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgEventDO record);

    int updateByPrimaryKey(MsgEventDO record);
}