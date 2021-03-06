package com.valueservice.djs.db.dao.chat;

import com.valueservice.djs.db.entity.chat.RoomContentDO;
import com.valueservice.djs.db.entity.chat.RoomContentShow;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface RoomContentDOMapper {
    int insert(RoomContentDO record);

    int insertSelective(RoomContentDO record);

    RoomContentDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoomContentDO record);

    List<RoomContentShow> selectContentShowByRoom(@Param("roomId") Integer roomId, @Param("id")Integer id);
}