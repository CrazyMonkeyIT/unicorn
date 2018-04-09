package com.valueservice.djs.db.entity.system;

import java.util.Date;
import java.util.List;

public class UpfileRecordDO {

    private Long id;

    private String actualFilePath;

    private String httpFilePath;

    private List<SplitFileBean> splitFiles;

    private Integer creatorId;

    private Date createTime;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActualFilePath() {
        return actualFilePath;
    }

    public void setActualFilePath(String actualFilePath) {
        this.actualFilePath = actualFilePath == null ? null : actualFilePath.trim();
    }

    public String getHttpFilePath() {
        return httpFilePath;
    }

    public void setHttpFilePath(String httpFilePath) {
        this.httpFilePath = httpFilePath == null ? null : httpFilePath.trim();
    }

    public List<SplitFileBean> getSplitFiles() {
        return splitFiles;
    }

    public void setSplitFiles(List<SplitFileBean> splitFiles) {
        this.splitFiles = splitFiles;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}