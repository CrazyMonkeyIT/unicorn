package com.valueservice.djs.bean.chief;

/**
 * Created by lee on 2018/3/29.
 */
public class ChiefListVO {

    //讲师ID
    private Integer lecturerId;
    //姓名
    private String lecturerName;
    //公司
    private String company;
    //职位
    private String position;
    //讲师头像
    private String headPhotoFile;
    //直播场次
    private String liveNumber;
    //讲师等级
    private String gradeName;
    //讲师简介
    private String introduction;

    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHeadPhotoFile() {
        return headPhotoFile;
    }

    public void setHeadPhotoFile(String headPhotoFile) {
        this.headPhotoFile = headPhotoFile;
    }

    public String getLiveNumber() {
        return liveNumber;
    }

    public void setLiveNumber(String liveNumber) {
        this.liveNumber = liveNumber;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}
