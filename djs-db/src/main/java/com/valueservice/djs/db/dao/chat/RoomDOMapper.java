package com.valueservice.djs.db.dao.chat;

import com.valueservice.djs.db.entity.chat.RoomDO;

public interface RoomDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoomDO record);

    int insertSelective(RoomDO record);

    RoomDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoomDO record);

    int updateByPrimaryKeyWithBLOBs(RoomDO record);

    int updateByPrimaryKey(RoomDO record);
}