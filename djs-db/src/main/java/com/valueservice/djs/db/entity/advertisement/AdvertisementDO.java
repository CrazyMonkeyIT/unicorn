package com.valueservice.djs.db.entity.advertisement;

import java.util.Date;

public class AdvertisementDO {
    private Integer advertisementId;

    private Integer advertisementTypeId;

    private Integer lecturerId;

    private Integer roomId;

    private String advertisementUrl;

    private String advertisementImgPath;

    private String advertisementTitle;

    private String advertisementDesc;

    private Integer status;

    private Date invalidDate;

    private Date createTime;

    private Date updateTime;

    private String invalidDateStr;

    public String getInvalidDateStr() {
        return invalidDateStr;
    }

    public void setInvalidDateStr(String invalidDateStr) {
        this.invalidDateStr = invalidDateStr;
    }

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

    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getAdvertisementUrl() {
        return advertisementUrl;
    }

    public void setAdvertisementUrl(String advertisementUrl) {
        this.advertisementUrl = advertisementUrl == null ? null : advertisementUrl.trim();
    }

    public String getAdvertisementImgPath() {
        return advertisementImgPath;
    }

    public void setAdvertisementImgPath(String advertisementImgPath) {
        this.advertisementImgPath = advertisementImgPath == null ? null : advertisementImgPath.trim();
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
}