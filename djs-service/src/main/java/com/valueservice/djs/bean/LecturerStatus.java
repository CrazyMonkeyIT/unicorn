package com.valueservice.djs.bean;

/**
 * 讲师状态
 * @author shawn
 * @date 2018-03-06
 */
public enum LecturerStatus {

    NORMAL(0,"正常"),

    LIVE(1,"直播中"),

    FROZEN(2,"冻结");

    private String desc;

    private int code;

    LecturerStatus(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
