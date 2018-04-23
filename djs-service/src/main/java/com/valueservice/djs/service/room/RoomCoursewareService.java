package com.valueservice.djs.service.room;

import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.db.dao.chat.RoomCoursewareDOMapper;
import com.valueservice.djs.db.dao.system.UpfileRecordDOMapper;
import com.valueservice.djs.db.entity.chat.RoomCoursewareDO;
import com.valueservice.djs.db.entity.system.UpfileRecordDO;
import com.valueservice.djs.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class RoomCoursewareService {

    @Resource
    RoomCoursewareDOMapper roomCoursewareDOMapper;

    @Resource
    private UpfileRecordDOMapper upfileRecordDOMapper;
    /**
     * 更新房间课件
     * @param record
     * @return
     */
    public BaseResult update(RoomCoursewareDO record){
        BaseResult result = new BaseResult();
        if(Objects.isNull(record.getId())){
            record.setCreateTime(DateUtil.currentTimestamp());
            roomCoursewareDOMapper.insertSelective(record);
        }else{
            roomCoursewareDOMapper.updateByPrimaryKeySelective(record);
        }
        result.setResult(true);
        result.setObj(record.getId());
        return result;
    }

    public RoomCoursewareDO selectByPrimaryKey(Long id){
        return roomCoursewareDOMapper.selectByPrimaryKey(id);
    }

    /**
     * 保存课件到附件表
     * @param actualFilePath
     * @param splitFiles
     * @param userId
     * @return
     */
    public BaseResult saveCourseware(String actualFilePath, String splitFiles, Integer userId){
        BaseResult result = new BaseResult();
        UpfileRecordDO record = new UpfileRecordDO();
        record.setActualFilePath(actualFilePath);
        record.setCreatorId(userId);
        record.setSplitFiles(splitFiles);
        record.setCreateTime(DateUtil.currentTimestamp());
        upfileRecordDOMapper.insertSelective(record);

        result.setResult(true);
        result.setObj(record.getId());
        return  result;
    }

    public RoomCoursewareDO getRoomCourseware(Long roomId){
        List<RoomCoursewareDO> roomCoursewares = roomCoursewareDOMapper
                .selectRoomCourseware(roomId);
        if(roomCoursewares.size() > 0){
            return roomCoursewares.get(0);
        }
        else{
            return null;
        }
    }

}
