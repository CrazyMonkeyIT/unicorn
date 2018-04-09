package com.valueservice.djs.service.chat;

import com.valueservice.djs.db.dao.chat.RoomContentDOMapper;
import com.valueservice.djs.db.entity.chat.RoomContentDO;
import com.valueservice.djs.db.entity.chat.RoomContentShow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

@Service
public class RoomContentService {

    @Resource
    private RoomContentDOMapper roomContentDOMapper;

    @Transactional(rollbackFor = Exception.class)
    public void saveMessage(RoomContentDO roomContentDO) {
        roomContentDOMapper.insertSelective(roomContentDO);
    }
    public List<RoomContentShow> queryContentsByRoomId(Integer roomId, Integer id){
        return roomContentDOMapper.selectContentShowByRoom(roomId,id);
    }
}
