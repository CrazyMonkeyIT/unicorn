package com.valueservice.djs.controller.system;

import com.valueservice.djs.util.ShortLinkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortLinkController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShortLinkController.class);

    /**
     * 生成短链接
     * @param longLink
     * @return
     */
    @RequestMapping("/minigram/getShortLink")
    public String getShortLink(String longLink){
        String link = null;
        try{
            link = ShortLinkUtil.getShortLink(longLink);
        }catch (Exception ex){
            LOGGER.error("生成短链接异常",ex);
        }
        return link;
    }
}
