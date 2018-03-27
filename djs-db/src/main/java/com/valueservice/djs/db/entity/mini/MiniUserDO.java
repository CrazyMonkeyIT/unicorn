package com.valueservice.djs.db.entity.mini;

import java.util.Date;

public class MiniUserDO {
    private Integer id;

    private String nickName;

    private Integer gender;

    private String country;

    private String province;

    private String city;

    private String language;

    private String avatarUrl;

    private String openId;

    private String unionId;

    private Date createTime;

    private Date updateTime;

    private Integer active;

    private Integer isVip;

    private Date vipInvalidTime;

    private Integer vipInvalidDay;

    private Integer totalPayAmount;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public Date getVipInvalidTime() {
        return vipInvalidTime;
    }

    public void setVipInvalidTime(Date vipInvalidTime) {
        this.vipInvalidTime = vipInvalidTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getVipInvalidDay() {
        return vipInvalidDay;
    }

    public void setVipInvalidDay(Integer vipInvalidDay) {
        this.vipInvalidDay = vipInvalidDay;
    }

    public Integer getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(Integer totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }
}