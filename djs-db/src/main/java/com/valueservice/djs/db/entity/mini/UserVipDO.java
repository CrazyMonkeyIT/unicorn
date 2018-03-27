package com.valueservice.djs.db.entity.mini;

public class UserVipDO {
    private Integer id;

    private String vipName;

    private Integer validDay;

    private Double openMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName == null ? null : vipName.trim();
    }

    public Integer getValidDay() {
        return validDay;
    }

    public void setValidDay(Integer validDay) {
        this.validDay = validDay;
    }

    public Double getOpenMoney() {
        return openMoney;
    }

    public void setOpenMoney(Double openMoney) {
        this.openMoney = openMoney;
    }
}