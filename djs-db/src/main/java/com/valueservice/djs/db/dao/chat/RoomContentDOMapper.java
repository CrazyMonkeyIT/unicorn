package com.valueservice.djs.db.dao.chat;

import com.valueservice.djs.db.entity.chat.RoomContentDO;
import com.valueservice.djs.db.entity.chat.RoomContentShow;

import java.util.List;

public interface RoomContentDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoomContentDO record);

    int insertSelective(RoomContentDO record);

    RoomContentDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoomContentDO record);

    int updateByPrimaryKey(RoomContentDO record);

    List<RoomContentShow> selectContentShowByRoom(Integer roomid);
}