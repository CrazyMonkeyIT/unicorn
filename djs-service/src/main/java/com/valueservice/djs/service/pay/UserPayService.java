package com.valueservice.djs.service.pay;

import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.db.dao.payment.PayResultDOMapper;
import com.valueservice.djs.db.dao.payment.UnifiedOrderDOMapper;
import com.valueservice.djs.db.entity.payment.PayResultDO;
import com.valueservice.djs.db.entity.payment.UnifiedOrderDO;
import com.valueservice.djs.util.HttpClientUtil;
import com.valueservice.djs.util.Md5Util;
import com.valueservice.djs.util.PayUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 用户付款接口
 * @author shawn
 * @date 2018-03-18
 */
@Service
public class UserPayService {

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
    UnifiedOrderDOMapper unifiedOrderDOMapper;

    @Resource
    PayResultDOMapper payResultDOMapper;
    /**
     * 获取用户支付信息
     * @param productDetail
     * @param totalFee
     * @param openId
     * @return
     */
    public BaseResult unifiedOrder(String productDetail,Integer totalFee, String openId){
        BaseResult result = new BaseResult();
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

        SortedMap<String,String> param = new TreeMap<String,String>();
        param.put("appid",appId);
        param.put("mch_id",mchId);
        param.put("nonce_str",PayUtil.getRandomString(32));
        param.put("body",productDetail);
        param.put("out_trade_no",PayUtil.getOutTradeNo());
        param.put("total_fee",totalFee.toString());
        param.put("spbill_create_ip",localIp);
        param.put("notify_url",extranetUrl+"/pay/receive");
        param.put("trade_type","JSAPI");
        param.put("openid",openId);
        param.put("sign", Md5Util.encryption(PayUtil.createSignString(param, apiKey), 32, true));

        Map<String,String> resultMap = HttpClientUtil.doPostXml(url,param,false,null,null);

        UnifiedOrderDO uo = new UnifiedOrderDO();
        uo.setAppid(appId);
        uo.setMchId(mchId);
        uo.setNonceStr(param.get("nonce_str"));
        uo.setSign(param.get("sign"));
        uo.setBody(param.get("body"));
        uo.setOutTradeNo(param.get("out_trade_no"));
        uo.setTotalFee(Integer.parseInt(param.get("total_fee")));
        uo.setSpbillCreateIp(param.get("spbill_create_ip"));
        uo.setNotifyUrl(param.get("notify_url"));
        uo.setTradeType(param.get("trade_type"));
        uo.setOpenid(openId);
        if(resultMap != null && resultMap.size() > 0) {
            uo.setReturnCode(resultMap.get("return_code"));
            uo.setReturnMsg(resultMap.get("return_msg"));
            if("SUCCESS".equals(resultMap.get("return_code"))){
                uo.setResultCode(resultMap.get("result_code"));
                uo.setErrCode(resultMap.get("err_code"));
                uo.setErrCodeDes(resultMap.get("err_code_des"));
                uo.setPrepayId(resultMap.get("prepay_id"));
            }
        }
        uo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        unifiedOrderDOMapper.insertSelective(uo);

        if(uo.getPrepayId() != null){
            result.setResult(true);
            SortedMap<String,String> payPackage = new TreeMap<String,String>();
            payPackage.put("appId",appId);
            payPackage.put("timeStamp",System.currentTimeMillis()+"");
            payPackage.put("nonceStr",PayUtil.getRandomString(32));
            payPackage.put("package","prepay_id="+uo.getPrepayId());
            payPackage.put("signType","MD5");
            payPackage.put("paySign",PayUtil.createSignString(payPackage,apiKey));
            payPackage.remove("appId");
            result.setObj(payPackage);
        }
        return result;
    }

    /**
     * 保存支付结果
     * @param map
     */
    public synchronized String savePayResult(Map<String,String> map){
        Map<String,String> resultMap = new HashMap<>();
        //查询统一下单记录
        UnifiedOrderDO uoRecord = unifiedOrderDOMapper.selectByOutTradeNo(map.get("out_trade_no"));
        if(uoRecord != null) {
            //查询支付结果记录
            PayResultDO payResult = payResultDOMapper.selectByUnifiedInfoId(uoRecord.getId());
            if(payResult == null) {
                payResult = new PayResultDO();
                payResult.setUnifiedInfoId(uoRecord.getId());
                if("SUCCESS".equals(map.get("return_code"))) {
                    payResult.setNonceStr(map.get("nonce_str"));
                    payResult.setSign(map.get("sign"));
                    payResult.setResultCode(map.get("result_code"));
                    payResult.setErrCode(map.get("err_code"));
                    payResult.setErrCodeDes(map.get("err_code_des"));
                    payResult.setOpenid(map.get("openid"));
                    payResult.setTradeType(map.get("trade_type"));
                    payResult.setBankType(map.get("bank_type"));
                    payResult.setTotalFee(map.get("total_fee"));
                    payResult.setCashFee(map.get("cash_fee"));
                    payResult.setTransactionId(map.get("transaction_id"));
                    payResult.setOutTradeNo(map.get("out_trade_no"));
                    payResult.setTimeEnd(map.get("time_end"));
                }else{
                    payResult.setResultCode(map.get("return_code"));
                    payResult.setErrCodeDes(map.get("return_msg"));
                }
                payResultDOMapper.insertSelective(payResult);
            }
            if(uoRecord.getTotalFee().equals(map.get("total_fee"))) {
                resultMap.put("return_code", "SUCCESS");
                resultMap.put("return_msg", "");
            }else{
                resultMap.put("return_code", "FAIL");
                resultMap.put("return_msg", "订单金额与统一下单不一致");
            }
        }else{
            resultMap.put("return_code","FAIL");
            resultMap.put("return_msg","未找到对应统一下单记录");
        }
        return PayUtil.parseMapXML(resultMap);
    }

}
