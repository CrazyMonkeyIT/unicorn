package com.valueservice.djs.db.dao.lecturer;

import com.valueservice.djs.db.entity.lecturer.LecturerRegisterDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LecturerRegisterDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(LecturerRegisterDO record);

    LecturerRegisterDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LecturerRegisterDO record);

    List<LecturerRegisterDO> selectList(@Param("lecturerName")String lecturerName,@Param("phone")String phone);

}