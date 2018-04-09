package com.valueservice.djs.bean;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 20:11 2018/4/9
 * @Modified by:
 */
public class MsgTypeBaseVO {

    private String chatType; //msg | event

    private MsgEventVO msgEventVO;

    private RoomContentVO roomContentVO;


    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public MsgEventVO getMsgEventVO() {
        return msgEventVO;
    }

    public void setMsgEventVO(MsgEventVO msgEventVO) {
        this.msgEventVO = msgEventVO;
    }

    public RoomContentVO getRoomContentVO() {
        return roomContentVO;
    }

    public void setRoomContentVO(RoomContentVO roomContentVO) {
        this.roomContentVO = roomContentVO;
    }
}
