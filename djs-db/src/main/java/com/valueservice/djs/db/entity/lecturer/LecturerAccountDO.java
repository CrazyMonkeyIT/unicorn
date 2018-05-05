package com.valueservice.djs.db.entity.lecturer;

import java.math.BigDecimal;
import java.util.Date;

public class LecturerAccountDO {
    private Integer id;

    private Integer lecturerId;

    private Integer paymentRatio;

    private BigDecimal totalIncome;

    private BigDecimal withdrawCash;

    private String withdrawSwitch;

    private String withdrawOffDesc;

    private String realName;

    private String bankCode;

    private String bankCardNo;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }

    public Integer getPaymentRatio() {
        return paymentRatio;
    }

    public void setPaymentRatio(Integer paymentRatio) {
        this.paymentRatio = paymentRatio;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getWithdrawCash() {
        return withdrawCash;
    }

    public void setWithdrawCash(BigDecimal withdrawCash) {
        this.withdrawCash = withdrawCash;
    }

    public String getWithdrawSwitch() {
        return withdrawSwitch;
    }

    public void setWithdrawSwitch(String withdrawSwitch) {
        this.withdrawSwitch = withdrawSwitch == null ? null : withdrawSwitch.trim();
    }

    public String getWithdrawOffDesc() {
        return withdrawOffDesc;
    }

    public void setWithdrawOffDesc(String withdrawOffDesc) {
        this.withdrawOffDesc = withdrawOffDesc == null ? null : withdrawOffDesc.trim();
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }
}