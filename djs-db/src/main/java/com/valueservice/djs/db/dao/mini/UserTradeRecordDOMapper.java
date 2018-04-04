package com.valueservice.djs.db.dao.mini;

import com.valueservice.djs.db.entity.mini.UserTradeRecordDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserTradeRecordDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserTradeRecordDO record);

    UserTradeRecordDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTradeRecordDO record);

    List<UserTradeRecordDO> selectListByOpenId(@Param("openId")String openId, @Param("tradeType")Integer tradeType);
}