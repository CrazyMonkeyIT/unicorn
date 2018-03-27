package com.valueservice.djs.controller.minigram;

import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.bean.EncryptUserInfo;
import com.valueservice.djs.db.entity.mini.MiniUserDO;
import com.valueservice.djs.service.mini.MiniUserService;
import com.valueservice.djs.util.WechatUserEncrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Objects;

@Controller
@RequestMapping("/minigram")
public class MiniGramController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MiniGramController.class);

    @Resource
    private MiniUserService miniUserService;

    @RequestMapping(value = "/getauth", method = RequestMethod.GET)
    public @ResponseBody EncryptUserInfo getUserInfo(String code, String iv, String encryptedData){
        return WechatUserEncrypt.getEncryptUserInfo(code,iv,encryptedData);
    }


    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public @ResponseBody BaseResult saveMiniUser(@RequestBody MiniUserDO miniUserDO){
        BaseResult result = new BaseResult();
        try {
            MiniUserDO record = miniUserService.saveOrUpdate(miniUserDO);
            if (!Objects.isNull(record)) {
                result.setResult(true);
                result.setObj(record);
                result.setMessage("小程序用户:" + miniUserDO.getOpenId() + "保存成功~~");
            }
        }catch (Exception ex){
            LOGGER.error("保存小程序用户异常",ex);
        }
        return result;
    }
}
