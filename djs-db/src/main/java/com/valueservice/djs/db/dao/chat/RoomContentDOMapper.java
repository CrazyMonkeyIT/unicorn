package com.valueservice.djs.db.dao.chat;

import com.valueservice.djs.db.entity.chat.RoomContentDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomContentDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoomContentDO record);

    int insertSelective(RoomContentDO record);

    RoomContentDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoomContentDO record);

    int updateByPrimaryKeyWithBLOBs(RoomContentDO record);

    int updateByPrimaryKey(RoomContentDO record);

    List selectContentShowByRoom(@Param("roomId") Integer roomId, @Param("id")Integer id);
}