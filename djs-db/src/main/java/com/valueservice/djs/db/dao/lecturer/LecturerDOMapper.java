package com.valueservice.djs.db.dao.lecturer;

import com.valueservice.djs.db.entity.lecturer.LecturerDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LecturerDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(LecturerDO record);

    LecturerDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LecturerDO record);

    List<LecturerDO> selectList(@Param("lecturerName")String lecturerName,@Param("phone")String phone);

    List<LecturerDO> selectAll();

}