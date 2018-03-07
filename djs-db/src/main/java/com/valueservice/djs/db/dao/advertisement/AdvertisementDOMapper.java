package com.valueservice.djs.db.dao.advertisement;

import com.valueservice.djs.db.entity.advertisement.AdvertisementDO;

public interface AdvertisementDOMapper {
    int deleteByPrimaryKey(Integer advertisementId);

    int insert(AdvertisementDO record);

    int insertSelective(AdvertisementDO record);

    AdvertisementDO selectByPrimaryKey(Integer advertisementId);

    int updateByPrimaryKeySelective(AdvertisementDO record);

    int updateByPrimaryKeyWithBLOBs(AdvertisementDO record);

    int updateByPrimaryKey(AdvertisementDO record);
}