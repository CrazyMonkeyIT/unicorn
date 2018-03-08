package com.valueservice.djs.db.entity.advertisement;

public class AdvertisementTypeDO {
    private Integer advertisementTypeId;

    private String advertisementTypeDesc;

    public Integer getAdvertisementTypeId() {
        return advertisementTypeId;
    }

    public void setAdvertisementTypeId(Integer advertisementTypeId) {
        this.advertisementTypeId = advertisementTypeId;
    }

    public String getAdvertisementTypeDesc() {
        return advertisementTypeDesc;
    }

    public void setAdvertisementTypeDesc(String advertisementTypeDesc) {
        this.advertisementTypeDesc = advertisementTypeDesc == null ? null : advertisementTypeDesc.trim();
    }
}