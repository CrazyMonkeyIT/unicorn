package com.valueservice.djs.db.entity.grade;

import java.util.Date;

public class LecturerGradeDO {
    private Integer lecturerGradeId;

    private String gradeName;

    private Integer paymentRatio;

    private Integer isValid;

    private Date createTime;

    private Date updateTime;

    public Integer getLecturerGradeId() {
        return lecturerGradeId;
    }

    public void setLecturerGradeId(Integer lecturerGradeId) {
        this.lecturerGradeId = lecturerGradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName == null ? null : gradeName.trim();
    }

    public Integer getPaymentRatio() {
        return paymentRatio;
    }

    public void setPaymentRatio(Integer paymentRatio) {
        this.paymentRatio = paymentRatio;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
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