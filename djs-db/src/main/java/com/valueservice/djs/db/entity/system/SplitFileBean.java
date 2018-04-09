package com.valueservice.djs.db.entity.system;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 13:56 2018/4/9
 * @Modified by:
 */
public class SplitFileBean {
    private String splitFilePath;
    private transient String actualSplitFilePath;
    private Boolean isForeshow;

    public String getSplitFilePath() {
        return splitFilePath;
    }

    public void setSplitFilePath(String splitFilePath) {
        this.splitFilePath = splitFilePath;
    }

    public String getActualSplitFilePath() {
        return actualSplitFilePath;
    }

    public void setActualSplitFilePath(String actualSplitFilePath) {
        this.actualSplitFilePath = actualSplitFilePath;
    }

    public Boolean getForeshow() {
        return isForeshow;
    }

    public void setForeshow(Boolean foreshow) {
        isForeshow = foreshow;
    }
}
