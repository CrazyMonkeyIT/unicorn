package com.valueservice.djs.enums;

/**
 * @Desc:直播间相关枚举定义
 * @Author: Bill
 * @Date: created in 16:00 2018/4/1
 * @Modified by:
 */
public class ChatEnum {

    /**
     * 房间类型
     */
    public enum RoomType {
        VIP(0, "VIP"),//VIP
        ROADSHOW(1, "road show"); //路演

        private int roomCode;
        private String roomDesc;

        RoomType(int roomCode, String roomDesc) {
            this.roomCode = roomCode;
            this.roomDesc = roomDesc;
        }

        public int getRoomCode() {
            return roomCode;
        }

        public void setRoomCode(int roomCode) {
            this.roomCode = roomCode;
        }

        public String getRoomDesc() {
            return roomDesc;
        }

        public void setRoomDesc(String roomDesc) {
            this.roomDesc = roomDesc;
        }
    }

    /**
     * 房间状态
     */
    public enum RoomStatus {
        LIVING(0, "living"),//正常直播中
        LIVESTARTED(-1, "live broadcast"),//直播未开始
        LIVEEND(1, "live end"),//直播结束
        DISABLE_SENDMSG(-2,"disable sendMsg");//禁言直播中

        private int roomStatusCode;
        private String roomStatusDesc;

        RoomStatus(int roomStatusCode, String roomStatusDesc) {
            this.roomStatusCode = roomStatusCode;
            this.roomStatusDesc = roomStatusDesc;
        }

        public int getRoomStatusCode() {
            return roomStatusCode;
        }

        public void setRoomStatusCode(int roomStatusCode) {
            this.roomStatusCode = roomStatusCode;
        }

        public String getRoomStatusDesc() {
            return roomStatusDesc;
        }

        public void setRoomStatusDesc(String roomStatusDesc) {
            this.roomStatusDesc = roomStatusDesc;
        }
    }

    /**
     * socket类型
     */
    public enum socketType{
        msg,event
    }

    /**
     * 消息事件类型
     */
    public enum EventType{
        QUIT("quit","退出房间"),
        INTO("into","进入房间"),
        DISABLE_SENDMSG("disable sendMsg","房间禁言"),
        ENABLE_SENDMSG("enable sendMsg","解除禁言"),
        KICK_OUT("kick out","提出房间");

        private String eventCode;
        private String eventDesc;

        EventType(String eventCode,String eventDesc){
            this.eventCode = eventCode;
            this.eventDesc = eventDesc;
        }

        public String getEventCode() {
            return eventCode;
        }

        public void setEventCode(String eventCode) {
            this.eventCode = eventCode;
        }

        public String getEventDesc() {
            return eventDesc;
        }

        public void setEventDesc(String eventDesc) {
            this.eventDesc = eventDesc;
        }
    }
}
