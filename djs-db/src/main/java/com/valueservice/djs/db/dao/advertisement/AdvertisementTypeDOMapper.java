package com.valueservice.djs.db.dao.advertisement;

import com.valueservice.djs.db.entity.advertisement.AdvertisementTypeDO;

import java.util.List;

public interface AdvertisementTypeDOMapper {
    int deleteByPrimaryKey(Integer advertisementTypeId);

    int insert(AdvertisementTypeDO record);

    int insertSelective(AdvertisementTypeDO record);

    AdvertisementTypeDO selectByPrimaryKey(Integer advertisementTypeId);

    int updateByPrimaryKeySelective(AdvertisementTypeDO record);

    int updateByPrimaryKey(AdvertisementTypeDO record);

    List<AdvertisementTypeDO> selectAll();
}