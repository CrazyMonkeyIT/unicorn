package com.valueservice.djs.db.dao.system;

import com.valueservice.djs.db.entity.system.UpfileRecordDO;

public interface UpfileRecordDOMapper {
    int insertSelective(UpfileRecordDO record);

    UpfileRecordDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UpfileRecordDO record);
}