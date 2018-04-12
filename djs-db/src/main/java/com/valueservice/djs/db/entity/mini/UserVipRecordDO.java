package com.valueservice.djs.db.entity.mini;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class UserVipRecordDO {
    private Integer id;

    private Integer miniUserId;

    private Integer userVipId;

    private String openMoney;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMiniUserId() {
        return miniUserId;
    }

    public void setMiniUserId(Integer miniUserId) {
        this.miniUserId = miniUserId;
    }

    public Integer getUserVipId() {
        return userVipId;
    }

    public void setUserVipId(Integer userVipId) {
        this.userVipId = userVipId;
    }

    public String getOpenMoney() {
        return openMoney;
    }

    public void setOpenMoney(String openMoney) {
        this.openMoney = openMoney == null ? null : openMoney.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}