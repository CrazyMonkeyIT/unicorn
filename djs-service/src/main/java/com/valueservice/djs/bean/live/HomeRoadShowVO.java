package com.valueservice.djs.bean.live;

/**
 * Created by lee on 2018/4/6.
 */
public class HomeRoadShowVO {

    //房间ID
    private Integer roomId;
    //路演名称
    private String roadShowName;
    //路演时间
    private String roadShowTime;
    //路演描述
    private String roadShowDesc;
    //姓名
    private String lecturerName;
    //公司
    private String company;
    //海报地址
    private String roomPosterPath;
    //房间人数
    private Integer roomPersonCount;

    public Integer getRoomPersonCount() {
        return roomPersonCount;
    }

    public void setRoomPersonCount(Integer roomPersonCount) {
        this.roomPersonCount = roomPersonCount;
    }

    public String getRoomPosterPath() {
        return roomPosterPath;
    }

    public void setRoomPosterPath(String roomPosterPath) {
        this.roomPosterPath = roomPosterPath;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoadShowName() {
        return roadShowName;
    }

    public void setRoadShowName(String roadShowName) {
        this.roadShowName = roadShowName;
    }

    public String getRoadShowTime() {
        return roadShowTime;
    }

    public void setRoadShowTime(String roadShowTime) {
        this.roadShowTime = roadShowTime;
    }

    public String getRoadShowDesc() {
        return roadShowDesc;
    }

    public void setRoadShowDesc(String roadShowDesc) {
        this.roadShowDesc = roadShowDesc;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
