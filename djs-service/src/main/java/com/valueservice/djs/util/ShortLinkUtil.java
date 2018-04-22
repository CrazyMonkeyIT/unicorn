package com.valueservice.djs.util;

import com.alibaba.fastjson.JSONArray;

/**
 * 短链接生成
 */
public class ShortLinkUtil {

    public static String getShortLink(String sourceLink) throws Exception{
        String response = HttpClientUtil.doGet(String.format("%s%s","http://api.t.sina.com.cn/short_url/shorten.json?source=3271760578&url_long=",sourceLink));
        JSONArray obj = JSONArray.parseArray(response);
        return obj.getJSONObject(0).getString("url_short");
    }


}
