package com.valueservice.djs.service.pay;

import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.db.dao.payment.EnterprisePayDOMapper;
import com.valueservice.djs.db.entity.payment.EnterprisePayDO;
import com.valueservice.djs.util.HttpClientUtil;
import com.valueservice.djs.util.Md5Util;
import com.valueservice.djs.util.PayUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 企业付款
 * @author shawn
 * @date 2018-03-18
 */
@Service
public class EnterprisePayService {
    @Value("${appid}")
    private String appId;

    @Value("${mchid}")
    private String mchId;

    @Value("${apikey}")
    private  String apiKey;

    @Value("${cert.file.path}")
    private String certFilePath;

    @Value("${extranet.url}")
    private String extranetUrl;

    @Value("${local.ip}")
    private String localIp;

    @Resource
    EnterprisePayDOMapper enterprisePayDOMapper;

    /**
     * 向个人付款
     * @param openId 用户openId
     * @param amount 金额
     * @param desc   详情
     * @return
     */
    public BaseResult pay(String openId, Integer amount, String desc){
        BaseResult result = new BaseResult();
        String url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
        SortedMap<String,String> param = new TreeMap<String,String>();
        param.put("mch_appid",appId);
        param.put("mchid",mchId);
        param.put("nonce_str", PayUtil.getRandomString(32));
        param.put("partner_trade_no",PayUtil.getOutTradeNo());
        param.put("openid",openId);
        param.put("check_name","NO_CHECK");
        param.put("amount",amount.toString());
        param.put("desc",desc);
        param.put("spbill_create_ip",localIp);
        param.put("sign", Md5Util.encryption(PayUtil.createSignString(param,apiKey), 32, true));
        Map<String,String> resultMap = HttpClientUtil.doPostXml(url, param, true, mchId, certFilePath);

        EnterprisePayDO enterprisePayDO = new EnterprisePayDO();
        enterprisePayDO.setMchAppid(param.get("mch_appid"));
        enterprisePayDO.setMchid(param.get("mchid"));
        enterprisePayDO.setNonceStr(param.get("nonce_str"));
        enterprisePayDO.setSign(param.get("sign"));
        enterprisePayDO.setPartnerTradeNo(param.get("partner_trade_no"));
        enterprisePayDO.setOpenid(param.get("openid"));
        enterprisePayDO.setCheckName(param.get("check_name"));
        enterprisePayDO.setReUserName(param.get("re_user_name"));
        enterprisePayDO.setAmount(Integer.parseInt(param.get("amount")));
        enterprisePayDO.setDesc(param.get("desc"));
        enterprisePayDO.setSpbillCreateIp(param.get("spbill_create_ip"));
        if(resultMap != null && "SUCCESS".equals(resultMap.get("return_code"))){
            enterprisePayDO.setResultCode(resultMap.get("result_code"));
            enterprisePayDO.setErrCode(resultMap.get("err_code"));
            enterprisePayDO.setErrCodeDes(resultMap.get("err_code_des"));
            enterprisePayDO.setPaymentNo(resultMap.get("payment_no"));
            enterprisePayDO.setPaymentTime(resultMap.get("payment_time"));
        }
        enterprisePayDO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        enterprisePayDOMapper.insertSelective(enterprisePayDO);

        if("SUCCESS".equals(resultMap.get("return_code")) && "SUCCESS".equals(resultMap.get("result_code"))){
            result.setResult(true);
        }else{
            result.setMessage(resultMap.get("err_code_des"));
        }
        return result;
    }

}
