package com.valueservice.djs.service.room;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.bean.CheckUserPermissionResult;
import com.valueservice.djs.bean.live.HomeLiveVO;
import com.valueservice.djs.bean.live.HomeRoadShowVO;
import com.valueservice.djs.db.dao.chat.RoomDOMapper;
import com.valueservice.djs.db.dao.chat.RoomUserDOMapper;
import com.valueservice.djs.db.dao.mini.MiniUserDOMapper;
import com.valueservice.djs.db.entity.chat.MsgEventDO;
import com.valueservice.djs.db.entity.chat.RoomDO;
import com.valueservice.djs.db.entity.chat.RoomUserDO;
import com.valueservice.djs.db.entity.mini.MiniUserDO;
import com.valueservice.djs.db.entity.system.UserInfoDO;
import com.valueservice.djs.enums.ChatEnum;
import com.valueservice.djs.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class RoomService {

    @Resource
    private RoomDOMapper roomDOMapper;

    @Resource
    private RoomUserDOMapper roomUserDOMapper;

    @Resource
	private MiniUserDOMapper miniUserDOMapper;
    @Resource
    private MsgEvevtService msgEvevtService;
    @Resource
    private SimpMessagingTemplate messageingTemplate;




    public List<RoomDO> selectAll(Integer status,String name){
        return roomDOMapper.selectRoomInfo(status,name);
    }

    public List<HomeLiveVO> selectLiveRoom(){
        List<HomeLiveVO> homeLiveVOList = new ArrayList<>();
        List<RoomDO> roomDOList = roomDOMapper.selectLiveRoom();
        for(RoomDO roomDO : roomDOList){
            HomeLiveVO homeLiveVO = new HomeLiveVO();
            homeLiveVO.setRoomId(roomDO.getId().intValue());
            homeLiveVO.setRoomName(roomDO.getName());
            homeLiveVO.setRoomDesc(roomDO.getRoomDesc());
            homeLiveVO.setRoomPersonCount(roomDO.getCount());
            homeLiveVO.setLecturerId(roomDO.getLecturerId());
            homeLiveVO.setLecturerName(roomDO.getLecturerName());
            homeLiveVO.setHeadPhotoFile(roomDO.getHeadPhotoFile());
            homeLiveVOList.add(homeLiveVO);
        }
        return homeLiveVOList;
    }

    /**
     * 获取直播房间信息
     * @param type  房间类型 0：vip 1：路演
     * @return
     */
    public List<RoomDO> selectByType(Integer type){
        return roomDOMapper.selectByType(type);
    }

    /**
     * 验证用户输入的邀请码是否正确
     * 正确：添加用户邀请码记录并返回 true
     * 错误：返回false
     * @param roomId
     * @param inviteCode
     * @return
     */
    public Boolean checkInvite(Integer roomId,String inviteCode,Integer userId){
        RoomDO room = roomDOMapper.selectByPrimaryKey(roomId);
        if(StringUtils.equals(inviteCode,room.getInviteCode())){
            RoomUserDO roomUserDO = new RoomUserDO();
            roomUserDO.setActive(1);
            roomUserDO.setUserId(userId);
            roomUserDO.setRoomId(roomId);
            roomUserDO.setPayType(0);
            roomUserDO.setCreateTime(new Date());
            roomUserDOMapper.insertSelective(roomUserDO);
            return true;
        }
        return false;
    }

    /**
     * 分页查询直播间信息
     * @param page
     * @param size
     * @return
     */
    public PageInfo<RoomDO> findAll(String page,String size,Integer status,String name){
        page = (page==null || StringUtils.isBlank(page))?"1":page;
        size = (size==null || StringUtils.isBlank(size))?"10":size;
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(size));
        if(status != null && status == 999){
            status = null;
        }
        List<RoomDO> list = roomDOMapper.selectRoomInfo(status,name);
        return new PageInfo<>(list);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateRoom(RoomDO roomDO, UserInfoDO userInfoDO){
        Long roomId = roomDO.getId();
        roomDOMapper.updateByPrimaryKeySelective(roomDO);

        MsgEventDO msgEventDO = new MsgEventDO();
        msgEventDO.setEventType(ChatEnum.EventType.FORCE_CLOSE_ROOM.getEventCode());
        msgEventDO.setExecutor(userInfoDO.getUserId());
        msgEventDO.setBeexecuted(roomId);
        msgEventDO.setRoomId(roomId);
        msgEventDO.setCreateTime(new Date());
        msgEvevtService.saveMsgEvent(msgEventDO);

        //发送消息
        String destination = "/topic/notifications/%s";
        messageingTemplate.convertAndSend(String.format(destination, roomId),
                JSON.toJSONString(msgEventDO));
    }

    /**
     * 查询单个直播间信息
     * @param roomId
     * @return
     */
    public RoomDO getRoomInfo(Integer roomId){
        return roomDOMapper.selectByPrimaryKey(roomId);
    }

    public CheckUserPermissionResult checkUserPermission(CheckUserPermissionResult param){
        RoomUserDO roomUser = roomUserDOMapper.selectByRoomUser(param.getRoomId(),param.getUserId());
        param.setResult(true);

        //如果进入房间的人就是房主，则不再做其他的判断
        RoomDO room = roomDOMapper.selectByPrimaryKey(param.getRoomId());
        param.setRoomPostPic(room.getRoomPosterPath());
        if(room.getCreatorId() == param.getUserId().longValue()){
            param.setRoomOwner(true);
            return param;
        }

        param.setVipRoom(room.getType() == 0);

        if(param.isVipRoom()){
            MiniUserDO miniUser = miniUserDOMapper.selectByPrimaryKey(param.getUserId());
            if(miniUser.getIsVip() == 1 && miniUser.getVipInvalidTime() != null
                    && new Date().getTime() < miniUser.getVipInvalidTime().getTime()){
                param.setVip(true);
            }
        }

        if(roomUser == null){
            param.setPayInviteCode(false);
            param.setHasPayRoom(false);
        }else{
            param.setPayInviteCode(roomUser.getPayType() == 0);
            param.setHasPayRoom(roomUser.getPayType() == 1);
        }


        return param;
    }


    public List<RoomDO> selectByLecturerId(Integer lecturerId){
        return roomDOMapper.selectByLecturerId(lecturerId);
    }
    /**
     * 保存房间信息
     * @return
     */
    public BaseResult updateRoomInfo(RoomDO room, Long creatorId){
        BaseResult result = new BaseResult();
        if(Objects.isNull(room.getId())) {
            room.setCreatorId(creatorId);
            room.setStatus(ChatEnum.RoomStatus.LIVESTARTED.getRoomStatusCode());
            room.setCreateTime(DateUtil.currentTimestamp());
            roomDOMapper.insertSelective(room);
        }else{
            roomDOMapper.updateByPrimaryKey(room);
        }
        result.setResult(true);
        return result;
    }
}
