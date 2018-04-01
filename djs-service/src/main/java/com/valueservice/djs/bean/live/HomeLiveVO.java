package com.valueservice.djs.bean.live;

/**
 * Created by lee on 2018/4/1.
 */
public class HomeLiveVO {
    //房间ID
    private Integer roomId;
    //房间名称
    private String roomName;
    //房间人数
    private Integer roomPersonCount;
    //房间描述
    private String roomDesc;
    //讲师ID
    private Integer lecturerId;
    //讲师姓名
    private String lecturerName;
    //讲师头像
    private String headPhotoFile;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getRoomPersonCount() {
        return roomPersonCount;
    }

    public void setRoomPersonCount(Integer roomPersonCount) {
        this.roomPersonCount = roomPersonCount;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getHeadPhotoFile() {
        return headPhotoFile;
    }

    public void setHeadPhotoFile(String headPhotoFile) {
        this.headPhotoFile = headPhotoFile;
    }
}
