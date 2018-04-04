package com.valueservice.djs.db.dao.lecturer;

import com.valueservice.djs.db.entity.lecturer.LecturerIncomeDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LecturerIncomeDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LecturerIncomeDO record);

    int insertSelective(LecturerIncomeDO record);

    LecturerIncomeDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LecturerIncomeDO record);

    int updateByPrimaryKey(LecturerIncomeDO record);

    List<LecturerIncomeDO> selectListByLecturerId(@Param("lecturerId")Integer lecturerId);
}