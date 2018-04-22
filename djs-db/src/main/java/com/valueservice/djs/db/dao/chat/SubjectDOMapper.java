package com.valueservice.djs.db.dao.chat;

import com.valueservice.djs.db.entity.chat.SubjectDO;

import java.util.List;

public interface SubjectDOMapper {
    int deleteByPrimaryKey(Integer subjectId);

    int insert(SubjectDO record);

    int insertSelective(SubjectDO record);

    SubjectDO selectByPrimaryKey(Integer subjectId);

    int updateByPrimaryKeySelective(SubjectDO record);

    int updateByPrimaryKey(SubjectDO record);

    List<SubjectDO> selectList();

    List<SubjectDO> selectValidList();

}