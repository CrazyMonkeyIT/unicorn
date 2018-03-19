package com.valueservice.djs.controller.payment;

import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.service.pay.UserPayService;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付类
 * @author shawn
 * @date 2018-03-18
 */
@Controller
@RequestMapping("/pay")
public class PayController {
    private static final Logger log = LoggerFactory.getLogger(PayController.class);

    @Resource
    UserPayService userPayService;
    /**
     * 接收微信服务器的消息
     * @param request
     * @param response
     */
    @RequestMapping("/receive")
    public void receive(HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String,String> requestMap = parseXmlToMap(request);
            String responseXml = userPayService.savePayResult(requestMap);
            response.getWriter().print(responseXml);
        }catch (Exception ex){
            log.error("处理微信服务器消息异常",ex);
        }
    }

    /**
     * 创建支付订单
     * @param productDetail 商品介绍
     * @param totalFee 金额
     * @param openId 用户openid
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public BaseResult createPay(String productDetail, Integer totalFee, String openId){
        BaseResult result = new BaseResult(false);
        try {
            result = userPayService.unifiedOrder(productDetail, totalFee, openId);
        }catch (Exception ex){
            log.error("创建支付订单异常",ex);
        }
        return result;
    }

    /**
     * 将xml消息解析为map
     * @param request
     * @return
     * @throws Exception
     */
    public Map<String,String> parseXmlToMap(HttpServletRequest request) throws Exception{
        Map<String,String> map = new HashMap<String,String>();
        InputStream inputStream = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        inputStream.close();
        return map;
    }
}
