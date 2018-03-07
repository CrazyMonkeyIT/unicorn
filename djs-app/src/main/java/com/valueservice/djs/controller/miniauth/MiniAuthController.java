package com.valueservice.djs.controller.miniauth;

import com.valueservice.djs.bean.EncryptUserInfo;
import com.valueservice.djs.util.WechatUserEncrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/miniauth")
public class MiniAuthController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody EncryptUserInfo getUserInfo(String code, String iv, String encryptedData){
        return WechatUserEncrypt.getEncryptUserInfo(code,iv,encryptedData);
    }
}
