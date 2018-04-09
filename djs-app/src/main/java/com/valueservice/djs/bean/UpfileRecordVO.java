package com.valueservice.djs.bean;

import com.valueservice.djs.db.entity.system.SplitFileBean;

import java.util.Date;
import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 16:55 2018/4/9
 * @Modified by:
 */
public class UpfileRecordVO {

    private Long id;

    private transient String actualFilePath;

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
        this.actualFilePath = actualFilePath;
    }

    public String getHttpFilePath() {
        return httpFilePath;
    }

    public void setHttpFilePath(String httpFilePath) {
        this.httpFilePath = httpFilePath;
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
        this.remark = remark;
    }
}
