package com.valueservice.djs.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 短链接生成
 */
public class ShortLinkUtil {

    public static String getShortLink(String sourceLink) throws Exception{
        sourceLink = "https://dujiaoshouzhiku.com/unicorn/minigram/intoRoomCourseware/13";
        String response = HttpClientUtil.doGet(String.format("%s%s","http://api.t.sina.com.cn/short_url/shorten.json?source=3271760578&url_long=",sourceLink));
        JSONArray obj = JSONArray.parseArray(response);
        return obj.getJSONObject(0).getString("url_short");
    }


}
