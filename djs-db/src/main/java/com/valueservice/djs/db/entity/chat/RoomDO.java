package com.valueservice.djs.db.entity.chat;

import java.util.Date;

public class RoomDO {
    private Long id;

    private Long creatorId;

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

    private String members;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members == null ? null : members.trim();
    }
}