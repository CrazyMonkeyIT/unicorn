package com.valueservice.djs.service.chat;

import com.valueservice.djs.db.dao.chat.RoomDOMapper;
import com.valueservice.djs.db.entity.chat.RoomDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoomService {

    @Resource
    private RoomDOMapper roomDOMapper;

    public List<RoomDO> selectAll(){
        return roomDOMapper.selectAll();
    }
}
