package com.valueservice.djs.service.pay;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.db.dao.payment.EnterprisePayDOMapper;
import com.valueservice.djs.db.entity.payment.EnterprisePayDO;
import com.valueservice.djs.util.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.PublicKey;
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

    @Value("${public.key.file.path}")
    private String publicKeyFilePath;

    @Resource
    EnterprisePayDOMapper enterprisePayDOMapper;

    /**
     * 付款到余额
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

    /**
     * 提现到银行卡
     * @param realName 真实姓名
     * @param bankCode 银行代码
     * @param bankCardNo 银行卡号
     * @param amount 提现金额
     * @param desc 提现描述
     * @return
     */
    public BaseResult payToCard(String realName, String bankCode, String bankCardNo, Integer amount, String desc) throws Exception{
        BaseResult result = new BaseResult();

        //银行卡号加密
        PublicKey pub = RSAUtil.getPubKey(publicKeyFilePath,"RSA");
        byte[] estr = RSAUtil.encrypt(bankCardNo.getBytes(),pub,2048, 11,"RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING");
        String encBankNo = Base64.encode(estr);
        encBankNo = encBankNo.replaceAll("\r|\n", "");
        //真实姓名加密
        estr = RSAUtil.encrypt(realName.getBytes(),pub,2048, 11,"RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING");
        String encTrueName = Base64.encode(estr);
        encTrueName = encTrueName.replaceAll("\r|\n", "");

        String url = "https://api.mch.weixin.qq.com/mmpaysptrans/pay_bank";
        SortedMap<String,String> param = new TreeMap<String,String>();
        param.put("mch_id", mchId);
        param.put("partner_trade_no",System.currentTimeMillis()+PayUtil.getRandomString(6));
        param.put("nonce_str", PayUtil.getRandomString(30));
        param.put("enc_bank_no", encBankNo);
        param.put("enc_true_name", encTrueName);
        param.put("bank_code", bankCode);
        param.put("amount", amount.toString());
        param.put("desc", desc);
        param.put("sign", Md5Util.encryption(PayUtil.createSignString(param,apiKey), 32, true));
        Map<String,String> resultMap = HttpClientUtil.doPostXml(url, param, true, mchId, certFilePath);

        EnterprisePayDO enterprisePayDO = new EnterprisePayDO();
        enterprisePayDO.setMchid(param.get("mchid"));
        enterprisePayDO.setNonceStr(param.get("nonce_str"));
        enterprisePayDO.setSign(param.get("sign"));
        enterprisePayDO.setPartnerTradeNo(param.get("partner_trade_no"));
        enterprisePayDO.setReUserName(realName);
        enterprisePayDO.setAmount(Integer.parseInt(param.get("amount")));
        enterprisePayDO.setDesc(param.get("desc"));
        if("SUCCESS".equals(resultMap.get("return_code")) && "SUCCESS".equals(resultMap.get("result_code"))) {
            enterprisePayDO.setResultCode(resultMap.get("result_code"));
            enterprisePayDO.setErrCode(resultMap.get("err_code"));
            enterprisePayDO.setErrCodeDes(resultMap.get("err_code_des"));
            enterprisePayDO.setPaymentNo(resultMap.get("payment_no"));
            enterprisePayDO.setCmmsAmt(Integer.parseInt(resultMap.get("cmms_amt")));
            result.setResult(true);
        }else{
            result.setMessage(resultMap.get("err_code_des"));
        }
        enterprisePayDO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        enterprisePayDOMapper.insertSelective(enterprisePayDO);

        return result;
    }

    /**
     * 获取RSA加密公钥
     * <p>
     * 通过接口获取商户RSA公钥，默认是PKCS#1格式，JAVA使用的是PKCS#8格式，所以需要使用OpenSSL工具进行转换<br/>
     * PKCS#1 转 PKCS#8:<br/>
     * openssl rsa -RSAPublicKey_in -in <filename> -pubout
     * </p>
     * @return
     */
    public String getPubKey(){
        String result = null;
        String pkurl = "https://fraud.mch.weixin.qq.com/risk/getpublickey";
        SortedMap<String,String> pkparam = new TreeMap<String,String>();
        pkparam.put("mch_id", mchId);
        pkparam.put("nonce_str", PayUtil.getRandomString(30));
        pkparam.put("sign_type", "MD5");
        pkparam.put("sign", Md5Util.encryption(PayUtil.createSignString(pkparam, apiKey), 32, true));
        Map<String,String> pkResultMap = HttpClientUtil.doPostXml(pkurl, pkparam, true, mchId, certFilePath);
        if("SUCCESS".equals(pkResultMap.get("return_code")) && "SUCCESS".equals(pkResultMap.get("result_code"))){
            result = pkResultMap.get("pub_key");
        }
        return result;
    }


}
