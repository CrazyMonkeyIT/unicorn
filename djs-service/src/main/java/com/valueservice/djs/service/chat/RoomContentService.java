package com.valueservice.djs.service.chat;

import com.valueservice.djs.db.dao.chat.RoomContentDOMapper;
import com.valueservice.djs.db.dao.mini.MiniUserDOMapper;
import com.valueservice.djs.db.entity.chat.RoomContentDO;
import com.valueservice.djs.db.entity.chat.RoomContentShow;
import com.valueservice.djs.db.entity.mini.MiniUserDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

@Service
public class RoomContentService {

    @Resource
    private RoomContentDOMapper roomContentDOMapper;

    @Resource
    private MiniUserDOMapper miniUserDOMapper;


    @Transactional(rollbackFor = Exception.class)
    public void saveMessage(RoomContentDO roomContentDO) {
        roomContentDOMapper.insertSelective(roomContentDO);
    }
    public List<RoomContentShow> queryContentsByRoomId(Integer roomId,Integer id){
        return roomContentDOMapper.selectContentShowByRoom(roomId,id);
    }

    public void saveContent(RoomContentShow roomContentShow){
        MiniUserDO miniUserDO = miniUserDOMapper
                .selectByOpenId(roomContentShow.getOpenId());

        RoomContentDO contentDO = new RoomContentDO();
        contentDO.setRoomid(roomContentShow.getRoomid());
        contentDO.setType(roomContentShow.getType());
        contentDO.setUserId(miniUserDO.getId());
        contentDO.setDuration(roomContentShow.getDuration());
        roomContentDOMapper.insertSelective(contentDO);
    }

}
