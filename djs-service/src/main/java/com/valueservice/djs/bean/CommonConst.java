package com.valueservice.djs.bean;


public class CommonConst {

    public static int DEFAULT_PAGE_NUM = 1;
    public static int DEFAULT_PAGE_SIZE = 10;

    public static int VALID_VALUE = 1;
    public static int INVALID_VALUE = 0;

    /**
     * 小程序的appid
     */
    public static final String MINI_PROGRAM_APPID = "wx99b01206c5360b49";

    /**
     * 小程序的app secret
     */
    public static final String MINI_PROGRAM_APPSECRET = "";

    /**
     * 小程序的grantType
     */
    public static final String MINI_PORGRAM_GRANT_TYPE = "authorization_code";

    /**
     * 小程序验证授权的URL
     */
    public static final String MINI_PROGRAM_AUTH_URL = "https://api.weixin.qq.com/sns/jscode2session";
}
