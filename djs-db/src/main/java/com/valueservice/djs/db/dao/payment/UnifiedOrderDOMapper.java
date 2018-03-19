package com.valueservice.djs.db.dao.payment;

import com.valueservice.djs.db.entity.payment.UnifiedOrderDO;
import org.apache.ibatis.annotations.Param;

public interface UnifiedOrderDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UnifiedOrderDO record);

    UnifiedOrderDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UnifiedOrderDO record);

    UnifiedOrderDO selectByOutTradeNo(@Param("outTradeNo")String outTradeNo);
}