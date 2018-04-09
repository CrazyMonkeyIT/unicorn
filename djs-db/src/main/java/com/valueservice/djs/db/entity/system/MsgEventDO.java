package com.valueservice.djs.db.entity.system;

import java.util.Date;

public class MsgEventDO {
    private Long id;

    private Integer roomId;

    private String eventType;

    private String executor;

    private String beexecuted;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor == null ? null : executor.trim();
    }

    public String getBeexecuted() {
        return beexecuted;
    }

    public void setBeexecuted(String beexecuted) {
        this.beexecuted = beexecuted == null ? null : beexecuted.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}