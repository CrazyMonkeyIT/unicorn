package com.valueservice.djs.service.room;

import com.valueservice.djs.db.dao.chat.RoomUserDOMapper;
import com.valueservice.djs.db.entity.chat.RoomUserDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoomUserService {

    @Resource
    private RoomUserDOMapper roomUserDOMapper;

    /**
     * 通过房间号及用户id查询用户的房间购买记录
     * @param roomId
     * @param userId
     * @return
     */
    RoomUserDO selectByRoomUser(Integer roomId,Integer userId){
        return roomUserDOMapper.selectByRoomUser(roomId,userId);
    }
}
