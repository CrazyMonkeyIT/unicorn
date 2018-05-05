package com.valueservice.djs.db.dao.lecturer;

import com.valueservice.djs.db.entity.lecturer.LecturerAccountDO;
import org.apache.ibatis.annotations.Param;

public interface LecturerAccountDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(LecturerAccountDO record);

    LecturerAccountDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LecturerAccountDO record);

    LecturerAccountDO selectByLecturerId(@Param("lecturerId")Integer lecturerId);
}