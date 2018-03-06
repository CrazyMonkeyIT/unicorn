package com.valueservice.djs.db.dao.lecturer;

import com.valueservice.djs.db.entity.lecturer.LecturerIncomeDO;

public interface LecturerIncomeDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LecturerIncomeDO record);

    int insertSelective(LecturerIncomeDO record);

    LecturerIncomeDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LecturerIncomeDO record);

    int updateByPrimaryKey(LecturerIncomeDO record);
}