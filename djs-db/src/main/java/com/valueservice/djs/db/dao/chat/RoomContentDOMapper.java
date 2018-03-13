package com.valueservice.djs.db.dao.chat;

import com.valueservice.djs.db.entity.chat.RoomContentDO;

public interface RoomContentDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoomContentDO record);

    int insertSelective(RoomContentDO record);

    RoomContentDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoomContentDO record);

    int updateByPrimaryKeyWithBLOBs(RoomContentDO record);

    int updateByPrimaryKey(RoomContentDO record);
}