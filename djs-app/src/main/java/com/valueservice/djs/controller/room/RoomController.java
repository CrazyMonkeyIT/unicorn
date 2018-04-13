package com.valueservice.djs.controller.room;

import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.CheckUserPermissionResult;
import com.valueservice.djs.controller.system.UserController;
import com.valueservice.djs.db.entity.chat.RoomContentDO;
import com.valueservice.djs.db.entity.chat.RoomContentShow;
import com.valueservice.djs.db.entity.chat.RoomDO;
import com.valueservice.djs.db.entity.system.UserInfoDO;
import com.valueservice.djs.service.room.RoomContentService;
import com.valueservice.djs.service.room.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@Controller
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);


    @Resource
    private RoomContentService roomContentService;

    @Resource
    private RoomService roomService;

    /**
     * 根据房间号获取房间的历史聊天记录
     * @param
     * @return
     */
    @RequestMapping(value = "/minigram/roomHistory", method = RequestMethod.POST)
    @ResponseBody
    public List<RoomContentShow> queryChatHistory(@RequestBody RoomContentDO roomContent){
        //Long roomId = params.get("roomId") != null?Long.parseLong(params.get("roomId").toString()):0L;
        return roomContentService.queryContentsByRoomId(roomContent.getRoomid(),roomContent.getId());
    }

    @RequestMapping(value = "/minigram/checkUserPermission", method = RequestMethod.POST)
    @ResponseBody
    public CheckUserPermissionResult checkUserPermission(CheckUserPermissionResult result){

        return null;
    }


}
