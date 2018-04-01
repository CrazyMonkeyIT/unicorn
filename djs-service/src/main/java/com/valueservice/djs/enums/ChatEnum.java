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
        LIVING(0, "living"),//直播中
        LIVESTARTED(-1, "live broadcast"),//直播未开始
        LIVEEND(1, "live end");//直播结束

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
}
