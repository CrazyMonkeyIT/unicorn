package com.valueservice.djs.db.dao.chat;

import com.valueservice.djs.db.entity.chat.RoomUserDO;
import org.apache.ibatis.annotations.Param;

public interface RoomUserDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoomUserDO record);

    int insertSelective(RoomUserDO record);

    RoomUserDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoomUserDO record);

    int updateByPrimaryKey(RoomUserDO record);

    RoomUserDO selectByRoomUser(@Param("roomId") Integer roomId,@Param("userId")Integer userId);
}