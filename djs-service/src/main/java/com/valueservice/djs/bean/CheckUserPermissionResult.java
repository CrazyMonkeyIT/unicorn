package com.valueservice.djs.bean;

public class CheckUserPermissionResult extends BaseResult{

    private String openId;//openid

    private Integer roomId;//房间id

    private boolean isVip;//是否VIP

    private boolean hasPayRoom;//是否支付房间费用

    private boolean payInviteCode;//是否填写过邀请码

    private String roomStatus;//房间状态  pre_live : 直接预告；live :直播中；after_live :直播结束

    private boolean isRoomOwner;//是否房主

    public boolean isRoomOwner() {
        return isRoomOwner;
    }

    public void setRoomOwner(boolean roomOwner) {
        isRoomOwner = roomOwner;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public boolean isHasPayRoom() {
        return hasPayRoom;
    }

    public void setHasPayRoom(boolean hasPayRoom) {
        this.hasPayRoom = hasPayRoom;
    }

    public boolean isPayInviteCode() {
        return payInviteCode;
    }

    public void setPayInviteCode(boolean payInviteCode) {
        this.payInviteCode = payInviteCode;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }





}
