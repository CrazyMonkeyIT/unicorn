package com.valueservice.djs.db.dao.lecturer;

import com.valueservice.djs.db.entity.lecturer.LecturerGradeDO;

import java.util.List;

public interface LecturerGradeDOMapper {
    int deleteByPrimaryKey(Integer lecturerGradeId);

    int insert(LecturerGradeDO record);

    int insertSelective(LecturerGradeDO record);

    LecturerGradeDO selectByPrimaryKey(Integer lecturerGradeId);

    int updateByPrimaryKeySelective(LecturerGradeDO record);

    int updateByPrimaryKey(LecturerGradeDO record);

    LecturerGradeDO selectByGradeName(String gradeName);

    List<LecturerGradeDO> selectAll();

}