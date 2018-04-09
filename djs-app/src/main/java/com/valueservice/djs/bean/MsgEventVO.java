package com.valueservice.djs.bean;

import java.util.Date;

public class MsgEventVO extends MsgTypeBaseVO{
    private Long id;

    private Long roomId;

    private String eventType;

    private Long executor;

    private Long beexecuted;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    public Long getExecutor() {
        return executor;
    }

    public void setExecutor(Long executor) {
        this.executor = executor;
    }

    public Long getBeexecuted() {
        return beexecuted;
    }

    public void setBeexecuted(Long beexecuted) {
        this.beexecuted = beexecuted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}