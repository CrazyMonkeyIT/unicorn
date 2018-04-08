package com.valueservice.djs.db.entity.chat;

import java.util.Date;

public class RoomDO {
    private Long id;

    private Long creatorId;//创建者ID

    private String name;//房间名称

    private Integer type;//房间內型  0:VIP 1:路演

    private Integer count;//房间总人数

    private Integer status;//房间状态 0:直播中 -1:直播未开始 1:直播结束

    private Long roomPrice;//房间价格

    private Date prepareLiveBeginTime;//预计直播开始时间

    private Date prepareLiveEndTime;//预计直播结束时间

    private Date actualLiveBeginTime;//实际直播开始时间

    private Date actualLiveEndTime;//实际直播结束时间

    private Date createTime;//记录创建时间

    private Integer subjectId;//专题ID

    private Integer lecturerId;//讲师ID

    private String roomDesc;//房间描述

    private Long coursewareId;//上传课件id

    private String roomPosterPath;

    //非数据库字端
    private String lecturerName;

    private String headPhotoFile;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCoursewareId() {
        return coursewareId;
    }

    public void setCoursewareId(Long coursewareId) {
        this.coursewareId = coursewareId;
    }

    public String getRoomPosterPath() {
        return roomPosterPath;
    }

    public void setRoomPosterPath(String roomPosterPath) {
        this.roomPosterPath = roomPosterPath;
    }

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


    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
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
}