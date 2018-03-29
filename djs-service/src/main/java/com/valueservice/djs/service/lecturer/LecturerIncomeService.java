package com.valueservice.djs.service.lecturer;

import com.valueservice.djs.db.dao.lecturer.LecturerIncomeDOMapper;
import com.valueservice.djs.db.entity.lecturer.LecturerIncomeDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 讲师收益
 */
@Service
public class LecturerIncomeService {

    @Resource
    LecturerIncomeDOMapper lecturerIncomeDOMapper;

    /**
     * 获取讲师收益列表
     * @param lecturerId
     * @return
     */
    public List<LecturerIncomeDO> selectListByLecturerId(Integer lecturerId){
        return lecturerIncomeDOMapper.selectListByLecturerId(lecturerId);
    }
}
