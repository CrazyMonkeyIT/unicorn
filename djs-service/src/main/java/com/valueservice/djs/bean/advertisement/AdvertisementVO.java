package com.valueservice.djs.bean.advertisement;

public class AdvertisementVO {
    private Integer advertisementId;

    private Integer advertisementTypeId;

    private String advertisementUrl;

    private String advertisementImgPath;

    private String advertisementTitle;

    private String advertisementDesc;

    private Integer status;

    private String invalidDateStr;

    //关联信息
    private Integer lecturerId;
    private Integer roomId;

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
        this.advertisementUrl = advertisementUrl;
    }

    public String getAdvertisementImgPath() {
        return advertisementImgPath;
    }

    public void setAdvertisementImgPath(String advertisementImgPath) {
        this.advertisementImgPath = advertisementImgPath;
    }

    public String getAdvertisementTitle() {
        return advertisementTitle;
    }

    public void setAdvertisementTitle(String advertisementTitle) {
        this.advertisementTitle = advertisementTitle;
    }

    public String getAdvertisementDesc() {
        return advertisementDesc;
    }

    public void setAdvertisementDesc(String advertisementDesc) {
        this.advertisementDesc = advertisementDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInvalidDateStr() {
        return invalidDateStr;
    }

    public void setInvalidDateStr(String invalidDateStr) {
        this.invalidDateStr = invalidDateStr;
    }
}
