package com.valueservice.djs.db.dao.chat;

import com.valueservice.djs.db.entity.chat.RoomCoursewareDO;

public interface RoomCoursewareDOMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(RoomCoursewareDO record);

    RoomCoursewareDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoomCoursewareDO record);

}