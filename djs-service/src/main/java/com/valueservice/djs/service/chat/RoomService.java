package com.valueservice.djs.service.chat;

import com.valueservice.djs.bean.live.HomeLiveVO;
import com.valueservice.djs.bean.live.HomeRoadShowVO;
import com.valueservice.djs.db.dao.chat.RoomDOMapper;
import com.valueservice.djs.db.entity.chat.RoomDO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Resource
    private RoomDOMapper roomDOMapper;

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

}
