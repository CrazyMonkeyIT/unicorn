package com.valueservice.djs.controller.socket;

import com.alibaba.fastjson.JSON;
import com.valueservice.djs.bean.MsgTypeBaseVO;
import com.valueservice.djs.db.entity.chat.MsgEventDO;
import com.valueservice.djs.db.entity.chat.RoomContentDO;
import com.valueservice.djs.enums.ChatEnum;
import com.valueservice.djs.service.room.MsgEvevtService;
import com.valueservice.djs.service.room.RoomContentService;
import com.valueservice.djs.util.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import java.util.Date;

@Controller
public class SocketController {


    private static final Logger logger = LoggerFactory.getLogger(SocketController.class);

    @Resource
    private SimpMessagingTemplate messageingTemplate;

    @Resource
    private RoomContentService roomContentService;

    @Resource
    private MsgEvevtService msgEvevtService;

    @MessageMapping("/chat")
    public void chatHandle(@RequestBody MsgTypeBaseVO msgTypeBaseVO){
        //直接持久化到MySQL,后期如果用户量增大,使用异步消息存储在缓存中再持久化到MySQL
        String destination = "/topic/notifications/%s";
        String chatType = msgTypeBaseVO.getChatType();
        Long roomId = null;

        if(chatType.equals(ChatEnum.socketType.msg.toString())){
            RoomContentDO roomContentDO = new RoomContentDO();
            BeanUtils.copyNotNullFields(msgTypeBaseVO,roomContentDO);
            roomContentDO.setCreateTime(new Date());
            roomContentDO.setId(null);
            roomId = roomContentDO.getRoomid().longValue();
            roomContentService.saveMessage(roomContentDO);
        }else if(chatType.equals(ChatEnum.socketType.event.toString())){
            MsgEventDO msgEventDO = new MsgEventDO();
            BeanUtils.copyNotNullFields(msgTypeBaseVO,msgEventDO);
            roomId = msgEventDO.getRoomId();
            msgEvevtService.saveMsgEvent(msgEventDO);
        }


//        RoomContentVO contentVO = msgTypeBaseVO.getRoomContentVO();
//        MsgEventVO msgEventVO = msgTypeBaseVO.getMsgEventVO();
        /*Integer roomId = null;
        try {
            if(Objects.isNull(contentVO)
                    && Objects.isNull(msgEventVO)){
                throw new NullPointerException("this vo can't is null");
            }
            roomId = Objects.isNull(contentVO)?contentVO.getRoomid():msgEventVO.getRoomId();
            if(chatType.equals(ChatEnum.socketType.msg.toString())){
                RoomContentDO roomContentDO = new RoomContentDO();
                BeanUtils.copyNotNullFields(contentVO,roomContentDO);
                roomContentDO.setCreateTime(new Date());
                roomContentDO.setId(null);
                roomContentService.saveMessage(roomContentDO);
            }else if(chatType.equals(ChatEnum.socketType.event.toString())){
                MsgEventDO msgEventDO = new MsgEventDO();
                BeanUtils.copyNotNullFields(msgEventVO,msgEventDO);
                msgEvevtService.saveMsgEvent(msgEventDO);
            }
        } catch (Exception e) {
            messageingTemplate.convertAndSend(String.format(destination, roomId),
                    "null");
           logger.error("socket消息发送异常",e);
        }*/

        messageingTemplate.convertAndSend(String.format(destination, roomId),
                JSON.toJSONString(msgTypeBaseVO));
    }

    @RequestMapping("/chatDemo")
    public String chatDemo(){
        return "chatDemo";
    }
}
