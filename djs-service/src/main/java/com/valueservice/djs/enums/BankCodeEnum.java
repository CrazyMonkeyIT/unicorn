package com.valueservice.djs.enums;

import java.util.*;

/**
 * 银行代码
 */
public enum BankCodeEnum {

    BANK1002(1002, "工商银行"),
    BANK1005(1005, "农业银行"),
    BANK1026(1026, "中国银行"),
    BANK1003(1003, "建设银行"),
    BANK1001(1001, "招商银行"),
    BANK1066(1066, "邮储银行"),
    BANK1020(1020, "交通银行"),
    BANK1004(1004, "浦发银行"),
    BANK1006(1006, "民生银行"),
    BANK1009(1009, "兴业银行"),
    BANK1010(1010, "平安银行"),
    BANK1021(1021, "中信银行"),
    BANK1025(1025, "华夏银行"),
    BANK1027(1027, "广发银行"),
    BANK1022(1022, "光大银行"),
    BANK1032(1032, "北京银行"),
    BANK1056(1056, "宁波银行");

    private Integer code;
    private String desc;

    private BankCodeEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static List<Map<String,Object>> getEnumsMap(){
        List<Map<String,Object>> list = new ArrayList<>();
        for (BankCodeEnum s : EnumSet.allOf(BankCodeEnum.class)) {
            Map<String,Object> map = new HashMap<>();
            map.put("code", s.getCode());
            map.put("desc", s.getDesc());
            list.add(map);
        }
        return list;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
