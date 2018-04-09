package com.valueservice.djs.controller.chat;

import com.valueservice.djs.bean.CheckUserPermissionResult;
import com.valueservice.djs.db.entity.chat.RoomContentDO;
import com.valueservice.djs.bean.RoomContentVO;
import com.valueservice.djs.service.chat.RoomContentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ChatController {

    @Resource
    private RoomContentService roomContentService;

    /**
     * 根据房间号获取房间的历史聊天记录
     * @param
     * @return
     */
    @RequestMapping(value = "/minigram/roomHistory", method = RequestMethod.POST)
    @ResponseBody
    public List<RoomContentDO> queryChatHistory(@RequestBody RoomContentDO roomContent){
        //Long roomId = params.get("roomId") != null?Long.parseLong(params.get("roomId").toString()):0L;
        return roomContentService.queryContentsByRoomId(roomContent.getRoomid(),roomContent.getId());
    }

    @RequestMapping(value = "/minigram/checkUserPermission", method = RequestMethod.POST)
    @ResponseBody
    public CheckUserPermissionResult checkUserPermission(CheckUserPermissionResult result){

        return null;
    }
}
