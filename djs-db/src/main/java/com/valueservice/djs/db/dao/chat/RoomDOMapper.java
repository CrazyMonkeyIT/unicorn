package com.valueservice.djs.db.dao.chat;

import com.valueservice.djs.db.entity.chat.RoomDO;

import java.util.List;

public interface RoomDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoomDO record);

    int insertSelective(RoomDO record);

    RoomDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoomDO record);

    int updateByPrimaryKeyWithBLOBs(RoomDO record);

    int updateByPrimaryKey(RoomDO record);

    List<RoomDO> selectAll();

    List<RoomDO> selectLiveRoom();
}