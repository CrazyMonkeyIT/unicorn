package com.valueservice.djs.db.dao.chat;

import com.valueservice.djs.db.entity.chat.RoomContentDO;

public interface RoomContentDOMapper {
    int insertSelective(RoomContentDO record);

    RoomContentDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoomContentDO record);
}