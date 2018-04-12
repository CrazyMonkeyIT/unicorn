package com.valueservice.djs.db.dao.chat;

import com.valueservice.djs.db.entity.chat.RoomUserDO;

public interface RoomUserDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoomUserDO record);

    int insertSelective(RoomUserDO record);

    RoomUserDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoomUserDO record);

    int updateByPrimaryKey(RoomUserDO record);
}