package com.valueservice.djs.db.entity.lecturer;

import java.util.Date;

public class LecturerDO {
    private Integer id;

    private String lecturerName;

    private String openId;

    private String phone;

    private Integer gradeId;

    private String gradeName;

    private String headPhotoFile;

    private String isChief;

    private Integer status;

    private String statusDesc;

    private String company;

    private String position;

    private String liveNumber;

    private String liveHours;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName == null ? null : lecturerName.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getIsChief() {
        return isChief;
    }

    public void setIsChief(String isChief) {
        this.isChief = isChief == null ? null : isChief.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc == null ? null : statusDesc.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getLiveNumber() {
        return liveNumber;
    }

    public void setLiveNumber(String liveNumber) {
        this.liveNumber = liveNumber == null ? null : liveNumber.trim();
    }

    public String getLiveHours() {
        return liveHours;
    }

    public void setLiveHours(String liveHours) {
        this.liveHours = liveHours == null ? null : liveHours.trim();
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

    public String getHeadPhotoFile() {
        return headPhotoFile;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public void setHeadPhotoFile(String headPhotoFile) {
        this.headPhotoFile = headPhotoFile;
    }
}