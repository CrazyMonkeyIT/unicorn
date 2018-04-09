package com.valueservice.djs.controller.socket;

import com.alibaba.fastjson.JSON;
import com.valueservice.djs.bean.MsgEventVO;
import com.valueservice.djs.bean.MsgTypeBaseVO;
import com.valueservice.djs.db.entity.chat.MsgEventDO;
import com.valueservice.djs.db.entity.chat.RoomContentDO;
import com.valueservice.djs.bean.RoomContentVO;
import com.valueservice.djs.enums.ChatEnum;
import com.valueservice.djs.service.chat.MsgEvevtService;
import com.valueservice.djs.service.chat.RoomContentService;
import com.valueservice.djs.util.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Controller
public class ChatSocketController {


    private static final Logger logger = LoggerFactory.getLogger(ChatSocketController.class);

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
        RoomContentVO contentVO = msgTypeBaseVO.getRoomContentVO();
        MsgEventVO msgEventVO = msgTypeBaseVO.getMsgEventVO();
        Integer roomId = null;
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
        }
        messageingTemplate.convertAndSend(String.format(destination, roomId),
                JSON.toJSONString(Objects.isNull(contentVO)?msgEventVO:contentVO));
    }

    @RequestMapping("/chatDemo")
    public String chatDemo(){
        return "chatDemo";
    }
}
