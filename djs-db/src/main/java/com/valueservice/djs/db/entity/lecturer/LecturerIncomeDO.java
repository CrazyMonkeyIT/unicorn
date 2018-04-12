package com.valueservice.djs.db.entity.lecturer;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

public class LecturerIncomeDO {
    private Integer id;

    private Integer lecturerId;

    private BigDecimal amount;

    private String incomeType;

    private String incomeSrouce;

    private Integer paymentRatio;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType == null ? null : incomeType.trim();
    }

    public String getIncomeSrouce() {
        return incomeSrouce;
    }

    public void setIncomeSrouce(String incomeSrouce) {
        this.incomeSrouce = incomeSrouce == null ? null : incomeSrouce.trim();
    }

    public Integer getPaymentRatio() {
        return paymentRatio;
    }

    public void setPaymentRatio(Integer paymentRatio) {
        this.paymentRatio = paymentRatio;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}