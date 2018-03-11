package com.valueservice.djs.db.dao.mini;

import com.valueservice.djs.db.entity.mini.MiniUserDO;

public interface MiniUserDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MiniUserDO record);

    int insertSelective(MiniUserDO record);

    MiniUserDO selectByPrimaryKey(Integer id);

    MiniUserDO selectByOpenid(String openId);

    int updateByPrimaryKeySelective(MiniUserDO record);

    int updateByPrimaryKey(MiniUserDO record);
}