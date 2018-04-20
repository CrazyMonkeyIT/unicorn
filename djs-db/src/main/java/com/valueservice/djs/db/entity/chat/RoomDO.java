package com.valueservice.djs.db.entity.chat;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class RoomDO {
    private Long id;

    private Long creatorId;//创建者ID

    private String name;//房间名称

    private Integer type;//房间內型  0:VIP 1:路演

    private Integer count;//房间总人数

    private Integer status;//房间状态 0:直播中 -1:直播未开始 -2:禁言中 1:直播结束

    private BigDecimal roomPrice;//房间价格

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date prepareLiveBeginTime;//预计直播开始时间

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date prepareLiveEndTime;//预计直播结束时间

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date actualLiveBeginTime;//实际直播开始时间

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date actualLiveEndTime;//实际直播结束时间

    private Date createTime;//记录创建时间

    private Integer subjectId;//专题ID

    private Integer lecturerId;//讲师ID

    private String roomDesc;//房间描述

    private String roomPosterPath; //海报图片

    private String heraldPath;  //预告图片

    private String inviteCode; //邀请码

    //非数据库字端
    private String lecturerName;
    //主题名称
    private String subjectName;
    //公司
    private String company;
    //讲师头像地址
    private String headPhotoFile;
    //路演时间
    private String roadShowTimeStr;
    //房主的openId
    private String creatorOpenId;

    private String prepareLiveBeginTimeStr;//预计直播开始时间

    private String prepareLiveEndTimeStr;//预计直播结束时间

    private String actualLiveBeginTimeStr;//实际直播开始时间

    private String actualLiveEndTimeStr;//实际直播结束时间

    public String getPrepareLiveBeginTimeStr() {
        return prepareLiveBeginTimeStr;
    }

    public void setPrepareLiveBeginTimeStr(String prepareLiveBeginTimeStr) {
        this.prepareLiveBeginTimeStr = prepareLiveBeginTimeStr;
    }

    public String getPrepareLiveEndTimeStr() {
        return prepareLiveEndTimeStr;
    }

    public void setPrepareLiveEndTimeStr(String prepareLiveEndTimeStr) {
        this.prepareLiveEndTimeStr = prepareLiveEndTimeStr;
    }

    public String getActualLiveBeginTimeStr() {
        return actualLiveBeginTimeStr;
    }

    public void setActualLiveBeginTimeStr(String actualLiveBeginTimeStr) {
        this.actualLiveBeginTimeStr = actualLiveBeginTimeStr;
    }

    public String getActualLiveEndTimeStr() {
        return actualLiveEndTimeStr;
    }

    public void setActualLiveEndTimeStr(String actualLiveEndTimeStr) {
        this.actualLiveEndTimeStr = actualLiveEndTimeStr;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRoadShowTimeStr() {
        return roadShowTimeStr;
    }

    public void setRoadShowTimeStr(String roadShowTimeStr) {
        this.roadShowTimeStr = roadShowTimeStr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
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

    public String getHeraldPath() {
        return heraldPath;
    }

    public void setHeraldPath(String heraldPath) {
        this.heraldPath = heraldPath;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getCreatorOpenId() {
        return creatorOpenId;
    }

    public void setCreatorOpenId(String creatorOpenId) {
        this.creatorOpenId = creatorOpenId;
    }
}