package com.valueservice.djs.bean;

import java.util.Date;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 20:11 2018/4/9
 * @Modified by:
 */
public class MsgTypeBaseVO {

    private String chatType; //msg | event

//    private MsgEventVO msgEventVO;
//
//    private RoomContentVO roomContentVO;


    private String eventType;

    private Long executor;

    private Long beexecuted;

    private Date createTime;

    private Integer roomid;

    private Integer userId;

    private Integer roomId;

    private String type;

    private Integer duration;

    private String url;

    private Integer active;

    private String openId;//用户openId

    private String avatarUrl;//用户头像链接

    private String content;//聊天内容

    private String nickName;//用户昵称

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

//    public MsgEventVO getMsgEventVO() {
//        return msgEventVO;
//    }
//
//    public void setMsgEventVO(MsgEventVO msgEventVO) {
//        this.msgEventVO = msgEventVO;
//    }
//
//    public RoomContentVO getRoomContentVO() {
//        return roomContentVO;
//    }
//
//    public void setRoomContentVO(RoomContentVO roomContentVO) {
//        this.roomContentVO = roomContentVO;
//    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
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

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}
