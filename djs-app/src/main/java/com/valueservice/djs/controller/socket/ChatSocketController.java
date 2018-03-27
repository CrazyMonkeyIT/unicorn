package com.valueservice.djs.controller.socket;

import com.alibaba.druid.support.json.JSONUtils;
import com.valueservice.djs.db.entity.chat.RoomContentDO;
import com.valueservice.djs.service.chat.RoomContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class ChatSocketController {


    private static final Logger logger = LoggerFactory.getLogger(ChatSocketController.class);

    @Resource
    private SimpMessagingTemplate messageingTemplate;

    @Resource
    private RoomContentService roomContentService;

    @MessageMapping("/chat")
    public void handleChat(@RequestBody RoomContentDO roomContent){
        //直接持久化到MySQL,后期如果用户量增大,使用异步消息存储在缓存中再持久化到MySQL
        String destination = "/topic/notifications/%s";
        try {
            messageingTemplate.convertAndSend(String.format(destination, roomContent.getRoomid()),
                    JSONUtils.toJSONString(roomContent));
        }catch (Exception e){
            logger.error("消息持久化失败！",e);
        }
        messageingTemplate.convertAndSend(String.format(destination, roomContent.getRoomid()),
                "null");
    }

    @RequestMapping("/chatDemo")
    public String chatDemo(){
        return "chatDemo";
    }
}
