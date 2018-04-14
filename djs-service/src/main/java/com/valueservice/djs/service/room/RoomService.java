package com.valueservice.djs.service.room;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.CheckUserPermissionResult;
import com.valueservice.djs.bean.live.HomeLiveVO;
import com.valueservice.djs.bean.live.HomeRoadShowVO;
import com.valueservice.djs.db.dao.chat.RoomDOMapper;
import com.valueservice.djs.db.dao.chat.RoomUserDOMapper;
import com.valueservice.djs.db.dao.mini.MiniUserDOMapper;
import com.valueservice.djs.db.entity.chat.RoomDO;
import com.valueservice.djs.db.entity.chat.RoomUserDO;
import com.valueservice.djs.db.entity.mini.MiniUserDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomService {

    @Resource
    private RoomDOMapper roomDOMapper;

    @Resource
    private RoomUserDOMapper roomUserDOMapper;

    @Resource
    private MiniUserDOMapper miniUserDOMapper;

    public List<RoomDO> selectAll(){
        return roomDOMapper.selectAll();
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

    public List<HomeRoadShowVO> selectRoadShowInfo(){
        List<HomeRoadShowVO> roadShowVOList = new ArrayList<>();
        List<RoomDO> roomDOList = roomDOMapper.selectRoadShowData();
        for(RoomDO roomDO : roomDOList){
            HomeRoadShowVO roadShowVO = new HomeRoadShowVO();
            roadShowVO.setRoomId(roomDO.getId().intValue());
            roadShowVO.setRoadShowName(roomDO.getName());
            roadShowVO.setRoadShowTime(roomDO.getRoadShowTimeStr());
            roadShowVO.setRoadShowDesc(roomDO.getRoomDesc());
            roadShowVO.setLecturerName(roomDO.getLecturerName());
            roadShowVO.setCompany(roomDO.getCompany());
            roadShowVO.setRoomPosterPath(roomDO.getRoomPosterPath());
            roadShowVO.setRoomPersonCount(roomDO.getCount());
            roadShowVOList.add(roadShowVO);
        }

        return roadShowVOList;
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

    public PageInfo<RoomDO> findAll(String page,String size){
        page = (page==null || StringUtils.isBlank(page))?"1":page;
        size = (size==null || StringUtils.isBlank(size))?"10":size;
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(size));
        List<RoomDO> list = roomDOMapper.selectAll();
        return new PageInfo<>(list);
    }

    public CheckUserPermissionResult checkUserPermission(CheckUserPermissionResult param){
        RoomUserDO roomUser = roomUserDOMapper.selectByRoomUser(param.getRoomId(),param.getUserId());
        param.setResult(true);

        //如果进入房间的人就是房主，则不再做其他的判断
        RoomDO room = roomDOMapper.selectByPrimaryKey(param.getRoomId());
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


}
