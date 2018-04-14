package com.valueservice.djs.db.entity.chat;

import java.util.Date;

public class RoomCoursewareDO {
    private Long id;

    private Long coursewareId;

    private String heraldPath;

    private Date createTime;

    private Date updateTime;

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

    public String getHeraldPath() {
        return heraldPath;
    }

    public void setHeraldPath(String heraldPath) {
        this.heraldPath = heraldPath == null ? null : heraldPath.trim();
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