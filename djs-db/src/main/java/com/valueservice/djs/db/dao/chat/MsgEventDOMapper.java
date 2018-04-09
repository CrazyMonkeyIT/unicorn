package com.valueservice.djs.db.dao.chat;

import com.valueservice.djs.db.entity.chat.MsgEventDO;

public interface MsgEventDOMapper {
    int insertSelective(MsgEventDO record);

    MsgEventDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgEventDO record);
}