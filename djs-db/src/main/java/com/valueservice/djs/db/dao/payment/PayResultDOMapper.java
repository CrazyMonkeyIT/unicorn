package com.valueservice.djs.db.dao.payment;

import com.valueservice.djs.db.entity.payment.PayResultDO;
import org.apache.ibatis.annotations.Param;

public interface PayResultDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(PayResultDO record);

    PayResultDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayResultDO record);

    PayResultDO selectByUnifiedInfoId(@Param("unifiedInfoId")Integer unifiedInfoId);
}