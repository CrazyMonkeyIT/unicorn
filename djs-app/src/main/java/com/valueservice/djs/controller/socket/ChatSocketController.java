package com.valueservice.djs.controller.socket;

import com.valueservice.djs.bean.ChatModel;
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

    @MessageMapping("/chat")
    public void handleChat(@RequestBody ChatModel chatModel){
        String destination = "/topic/notifications/%s";
        messageingTemplate.convertAndSend(String.format(destination, chatModel.getRoomId()),
                chatModel.getUserName()+ ":"+chatModel.getContent());
    }

    @RequestMapping("/chatDemo")
    public String chatDemo(){
        return "chatDemo";
    }
}
