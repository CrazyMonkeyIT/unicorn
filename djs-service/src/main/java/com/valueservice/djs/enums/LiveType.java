package com.valueservice.djs.enums;

public enum LiveType {

    ORDINARY("ORDINARY", "普通"),
    SPECIAL("SPECIAL", "专场"),
    VIP("VIP", "VIP");

    private String liveCode;
    private String liveDesc;

    private LiveType(String liveCode, String liveDesc){
        this.liveCode = liveCode;
        this.liveDesc = liveDesc;
    }

    public String getLiveCode() {
        return liveCode;
    }

    public void setLiveCode(String liveCode) {
        this.liveCode = liveCode;
    }

    public String getLiveDesc() {
        return liveDesc;
    }

    public void setLiveDesc(String liveDesc) {
        this.liveDesc = liveDesc;
    }
}
