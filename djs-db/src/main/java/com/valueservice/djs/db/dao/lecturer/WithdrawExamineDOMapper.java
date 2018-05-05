package com.valueservice.djs.db.dao.lecturer;

import com.valueservice.djs.db.entity.lecturer.WithdrawExamineDO;

import java.util.List;

public interface WithdrawExamineDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(WithdrawExamineDO record);

    WithdrawExamineDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WithdrawExamineDO record);

    List<WithdrawExamineDO> selectByList();
}