package com.valueservice.djs.db.dao.chat;

import com.valueservice.djs.db.entity.chat.RoomDO;

import java.util.List;

public interface RoomDOMapper {
    int insertSelective(RoomDO record);

    RoomDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoomDO record);

    List<RoomDO> selectAll();
}