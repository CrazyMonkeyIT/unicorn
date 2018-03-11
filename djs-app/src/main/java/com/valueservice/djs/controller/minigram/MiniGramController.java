package com.valueservice.djs.controller.minigram;

import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.bean.EncryptUserInfo;
import com.valueservice.djs.db.entity.mini.MiniUserDO;
import com.valueservice.djs.service.mini.MiniUserService;
import com.valueservice.djs.util.WechatUserEncrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/minigram")
public class MiniGramController {

    @Resource
    private MiniUserService miniUserService;

    @RequestMapping(value = "/getauth", method = RequestMethod.GET)
    public @ResponseBody EncryptUserInfo getUserInfo(String code, String iv, String encryptedData){
        return WechatUserEncrypt.getEncryptUserInfo(code,iv,encryptedData);
    }


    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public @ResponseBody BaseResult saveMiniUser(@RequestBody MiniUserDO miniUserDO){
        int resultInt = miniUserService.saveOrUpdate(miniUserDO);
        BaseResult result = new BaseResult();
        if (resultInt == 1){
            result.setSuccess(true);
            result.setMessage("小程序用户:" + miniUserDO.getOpenid() + "保存成功~~");
        }
        return result;
    }
}
