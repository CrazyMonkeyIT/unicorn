package com.valueservice.djs.db.entity.advertisement;

import java.util.Date;

public class AdvertisementDO {
    private Integer advertisementId;

    private Integer advertisementTypeId;

    private String advertisementUrl;

    private String advertisementTitle;

    private String advertisementDesc;

    private Integer status;

    private Date invalidDate;

    private Date createTime;

    private Date updateTime;

    private byte[] advertisementImg;

    public Integer getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(Integer advertisementId) {
        this.advertisementId = advertisementId;
    }

    public Integer getAdvertisementTypeId() {
        return advertisementTypeId;
    }

    public void setAdvertisementTypeId(Integer advertisementTypeId) {
        this.advertisementTypeId = advertisementTypeId;
    }

    public String getAdvertisementUrl() {
        return advertisementUrl;
    }

    public void setAdvertisementUrl(String advertisementUrl) {
        this.advertisementUrl = advertisementUrl == null ? null : advertisementUrl.trim();
    }

    public String getAdvertisementTitle() {
        return advertisementTitle;
    }

    public void setAdvertisementTitle(String advertisementTitle) {
        this.advertisementTitle = advertisementTitle == null ? null : advertisementTitle.trim();
    }

    public String getAdvertisementDesc() {
        return advertisementDesc;
    }

    public void setAdvertisementDesc(String advertisementDesc) {
        this.advertisementDesc = advertisementDesc == null ? null : advertisementDesc.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public byte[] getAdvertisementImg() {
        return advertisementImg;
    }

    public void setAdvertisementImg(byte[] advertisementImg) {
        this.advertisementImg = advertisementImg;
    }
}