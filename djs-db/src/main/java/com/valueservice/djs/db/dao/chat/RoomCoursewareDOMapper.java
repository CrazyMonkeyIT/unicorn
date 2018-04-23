package com.valueservice.djs.db.dao.chat;

import com.valueservice.djs.db.entity.chat.RoomCoursewareDO;

import java.util.List;

public interface RoomCoursewareDOMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(RoomCoursewareDO record);

    RoomCoursewareDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoomCoursewareDO record);

    List<RoomCoursewareDO> selectRoomCourseware(Long roomId);

}