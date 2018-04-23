package com.valueservice.djs.controller.room;

import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.bean.CheckInviteCodeVo;
import com.valueservice.djs.bean.CheckUserPermissionResult;
import com.valueservice.djs.controller.BaseController;
import com.valueservice.djs.db.entity.chat.*;
import com.valueservice.djs.db.entity.mini.UserVipDO;
import com.valueservice.djs.enums.ChatEnum;
import com.valueservice.djs.service.mini.UserVipService;
import com.valueservice.djs.service.room.RoomContentService;
import com.valueservice.djs.service.room.RoomService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
public class RoomController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Resource
    private RoomContentService roomContentService;
    @Resource
    private RoomService roomService;
    @Resource
	private UserVipService userVipService;

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
    public CheckUserPermissionResult checkUserPermission(@RequestBody CheckUserPermissionResult result){

        return roomService.checkUserPermission(result);
    }

    @RequestMapping(value = "/minigram/allVip", method = RequestMethod.POST)
	@ResponseBody
	public List<UserVipDO> selectAllVip(){
        	return userVipService.selectAllVipList();
    }

    /**
     * 获取所有直播间信息
     */
    @GetMapping("/room/get/info")
    public String findAll(
            @RequestParam(required = false) String page,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) Integer parStatus,
            @RequestParam(required = false) String parStrName,
            ModelMap modelMap){

        PageInfo<RoomDO> pageInfo = null;
        try{
            pageInfo = roomService.findAll(page,size,parStatus,parStrName);
        }catch(Exception e){
            logger.error("获取用户列表异常",e);
        }
        modelMap.addAttribute("page",pageInfo);
        modelMap.addAttribute("parStrName",parStrName);
        modelMap.addAttribute("parStatus",parStatus==null?999:parStatus);
        return "room/list";
    }


    /**
     * 修改直播间信息&保存事件发送记录
     * @param roomId
     * @return
     */
    @PostMapping("/room/update/{roomId}")
    @ResponseBody
    public Boolean updateRoom(@PathVariable Long roomId){
        //强制关闭直播间，发送消息
        try {
            RoomDO roomDO = new RoomDO();
            roomDO.setId(roomId);
            roomDO.setStatus(ChatEnum.RoomStatus.LIVEEND.getRoomStatusCode());
            roomDO.setActualLiveEndTime(new Date());
            roomService.updateRoom(roomDO,getCurrentUser());
        } catch (MessagingException e) {
            logger.error("",e);
            return false;
        }
        return true;
    }

    @GetMapping("/room/get/{roomId}")
    @ResponseBody
    public RoomDO findByRoomId(@PathVariable Integer roomId){
        return roomService.getRoomInfo(roomId);
    }

    @PostMapping("/minigram/get/{roomId}")
    @ResponseBody
    public RoomDO getRoom(@PathVariable Integer roomId){
        return roomService.getRoomInfo(roomId);
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
     * 保存直播房间 信息
     * @param room
     * @return
     */
    @RequestMapping("/minigram/saveRoomInfo")
    public @ResponseBody BaseResult saveRoomInfo(@RequestBody RoomDO room){
        BaseResult result = new BaseResult();
        try{
            result = roomService.updateRoomInfo(room, 1L);
        }catch(Exception ex){
            logger.error("保存直播房间异常",ex);
        }
        return result;
    }

    /**
     * 获取该讲师的直播间列表
     * @param lecturerId 讲师ID
     * @return
     */
    @RequestMapping("/minigram/getLecturerRooms")
    public @ResponseBody List<RoomDO> getLecturerRooms(Integer lecturerId){
        List<RoomDO> list = roomService.selectByLecturerId(lecturerId);
        return list;
    }
}
