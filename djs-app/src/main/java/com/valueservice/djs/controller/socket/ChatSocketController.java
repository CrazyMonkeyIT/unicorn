package com.valueservice.djs.controller.socket;

import com.alibaba.fastjson.JSON;
import com.valueservice.djs.db.entity.chat.RoomContentDO;
import com.valueservice.djs.db.entity.chat.RoomContentShow;
import com.valueservice.djs.service.chat.RoomContentService;
import com.valueservice.djs.util.BeanUtils;
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
public class ChatSocketController {


    private static final Logger logger = LoggerFactory.getLogger(ChatSocketController.class);

    @Resource
    private SimpMessagingTemplate messageingTemplate;

    @Resource
    private RoomContentService roomContentService;

    @MessageMapping("/chat")
    public void handleChat(@RequestBody RoomContentShow contentVO){
        //直接持久化到MySQL,后期如果用户量增大,使用异步消息存储在缓存中再持久化到MySQL

        //TODO 消息考虑事件  退出房间 进入房间  房间禁言  踢人....
        String destination = "/topic/notifications/%s";
        try {
            RoomContentDO roomContentDO = new RoomContentDO();
            BeanUtils.copyNotNullFields(contentVO,roomContentDO);
            roomContentDO.setCreateTime(new Date());
            roomContentService.saveMessage(roomContentDO);
            messageingTemplate.convertAndSend(String.format(destination, contentVO.getRoomid()),
                    JSON.toJSONString(roomContentDO));
        }catch (Exception e){
            messageingTemplate.convertAndSend(String.format(destination, contentVO.getRoomid()),
                    "null");
            logger.error("消息持久化失败！",e);
        }

    }

    @RequestMapping("/chatDemo")
    public String chatDemo(){
        return "chatDemo";
    }
}
