package com.valueservice.djs.db.dao.payment;

import com.valueservice.djs.db.entity.payment.EnterprisePayDO;

public interface EnterprisePayDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(EnterprisePayDO record);

    EnterprisePayDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EnterprisePayDO record);

}