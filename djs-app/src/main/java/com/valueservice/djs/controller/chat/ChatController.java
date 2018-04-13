package com.valueservice.djs.controller.chat;

import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.bean.CheckInviteCodeVo;
import com.valueservice.djs.bean.CheckUserPermissionResult;
import com.valueservice.djs.db.entity.chat.RoomContentDO;
import com.valueservice.djs.db.entity.chat.RoomContentShow;
import com.valueservice.djs.db.entity.chat.RoomDO;
import com.valueservice.djs.db.entity.mini.MiniUserDO;
import com.valueservice.djs.service.mini.MiniUserService;
import com.valueservice.djs.service.room.RoomContentService;
import com.valueservice.djs.service.room.RoomService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@Controller
public class ChatController {


    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Resource
    private RoomContentService roomContentService;

    @Resource
    private MiniUserService miniUserService;

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

        MiniUserDO miniUser = miniUserService.selectByOpenId(result.getOpenId());

        return null;
    }


    /**
     * 验证用户输入的邀请码是否正确
     * 正确：添加用户邀请码记录并通知小程序进入直播间
     * 错误：提示用户邀请码不正确
     * @param checkInviteCodeVo
     * @return
     */
    @RequestMapping(value = "/minigram/checkInviteCode", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult checkInviteCode(@RequestBody CheckInviteCodeVo checkInviteCodeVo){
        BaseResult result = new BaseResult();
        if(StringUtils.isBlank(checkInviteCodeVo.getInviteCode())){
            result.setMessage("请输入邀请码");
            return result;
        }
        if(checkInviteCodeVo.getUserId() == null || checkInviteCodeVo.getUserId() == 0){
            result.setMessage("未获取到用户信息，请清空小程序缓存后再试试");
            return result;
        }
        if(checkInviteCodeVo.getRoomId() == null || checkInviteCodeVo.getRoomId()  == 0){
            result.setMessage("未获取到直播间信息，请清空小程序缓存后再试试");
            return result;
        }

        if(!roomService.checkInvite(checkInviteCodeVo.getRoomId(),checkInviteCodeVo.getInviteCode(),
                checkInviteCodeVo.getUserId())){
            result.setMessage("您输入的邀请码不正确，请校验后再试");
            return result;
        }else{
            result.setResult(true);
            result.setMessage("邀请码验证成功");
        }

        return result;
    }

    /**
     * 获取所有直播间信息
     */
    @GetMapping("/room/get/info")
    public String findAll(
            @RequestParam(required = false) String page,
            @RequestParam(required = false) String size,ModelMap modelMap){

        PageInfo<RoomDO> pageInfo = null;
        try{
            pageInfo = roomService.findAll(page,size);
        }catch(Exception e){
            logger.error("获取用户列表异常",e);
        }
        modelMap.addAttribute("page",pageInfo);
        return "room/list";
    }
}
