package com.valueservice.djs.db.entity.system;

import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 13:55 2018/4/9
 * @Modified by:
 */
public class FileParsingRepBean {
    private String filePath;
    private transient String actualFilePath;
    private List<SplitFileBean> splitFileList;
    private Long insertId;

    public String getFilePath() {
        return filePath;
    }

    public String getActualFilePath() {
        return actualFilePath;
    }

    public void setActualFilePath(String actualFilePath) {
        this.actualFilePath = actualFilePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<SplitFileBean> getSplitFileList() {
        return splitFileList;
    }

    public Long getInsertId() {
        return insertId;
    }

    public void setInsertId(Long insertId) {
        this.insertId = insertId;
    }

    public void setSplitFileList(List<SplitFileBean> splitFileList) {
        this.splitFileList = splitFileList;
    }
}
