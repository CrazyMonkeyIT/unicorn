package com.valueservice.djs.service.chat;

import com.valueservice.djs.db.dao.chat.MsgEventDOMapper;
import com.valueservice.djs.db.dao.chat.RoomDOMapper;
import com.valueservice.djs.db.entity.chat.MsgEventDO;
import com.valueservice.djs.enums.ChatEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 20:31 2018/4/9
 * @Modified by:
 */
@Service
public class MsgEvevtService {

    @Resource
    private MsgEventDOMapper msgEventDOMapper;

    @Resource
    private RoomDOMapper roomDOMapper;

    /**
     * 保存事件类消息,房间禁言维护房间状态
     * @param msgEventDO
     */
    @Transactional
    public void saveMsgEvent(MsgEventDO msgEventDO){
        msgEventDOMapper.insertSelective(msgEventDO);
        //处理事件
        if(msgEventDO.getEventType().equals(ChatEnum.EventType.DISABLE_SENDMSG)){
            roomDOMapper.updateStatus(msgEventDO.getRoomId(),
                    ChatEnum.RoomStatus.DISABLE_SENDMSG.getRoomStatusCode());
        }
        if(msgEventDO.getEventType().equals(ChatEnum.EventType.ENABLE_SENDMSG)){
            roomDOMapper.updateStatus(msgEventDO.getRoomId(),
                    ChatEnum.RoomStatus.LIVING.getRoomStatusCode());
        }
    }
}
