package com.valueservice.djs.db.entity.chat;

import java.util.Date;

public class RoomDO {
    private Integer id;

    private Integer creatorId;

    private String name;

    private Integer type;

    private Integer count;

    private Integer status;

    private Long roomPrice;

    private Date prepareLiveBeginTime;

    private Date prepareLiveEndTime;

    private Date actualLiveBeginTime;

    private Date actualLiveEndTime;

    private Date createTime;

    private Integer subjectId;

    private Integer lecturerId;

    private String roomDesc;

    private String courseware;

    private String members;

    //非数据库字端
    private String lecturerName;

    private String headPhotoFile;

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getHeadPhotoFile() {
        return headPhotoFile;
    }

    public void setHeadPhotoFile(String headPhotoFile) {
        this.headPhotoFile = headPhotoFile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Long roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Date getPrepareLiveBeginTime() {
        return prepareLiveBeginTime;
    }

    public void setPrepareLiveBeginTime(Date prepareLiveBeginTime) {
        this.prepareLiveBeginTime = prepareLiveBeginTime;
    }

    public Date getPrepareLiveEndTime() {
        return prepareLiveEndTime;
    }

    public void setPrepareLiveEndTime(Date prepareLiveEndTime) {
        this.prepareLiveEndTime = prepareLiveEndTime;
    }

    public Date getActualLiveBeginTime() {
        return actualLiveBeginTime;
    }

    public void setActualLiveBeginTime(Date actualLiveBeginTime) {
        this.actualLiveBeginTime = actualLiveBeginTime;
    }

    public Date getActualLiveEndTime() {
        return actualLiveEndTime;
    }

    public void setActualLiveEndTime(Date actualLiveEndTime) {
        this.actualLiveEndTime = actualLiveEndTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc == null ? null : roomDesc.trim();
    }

    public String getCourseware() {
        return courseware;
    }

    public void setCourseware(String courseware) {
        this.courseware = courseware == null ? null : courseware.trim();
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members == null ? null : members.trim();
    }
}