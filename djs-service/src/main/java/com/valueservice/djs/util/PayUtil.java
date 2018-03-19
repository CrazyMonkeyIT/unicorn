package com.valueservice.djs.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.*;

/**
 * 微信支付帮助类
 */
public class PayUtil {

    private static final Logger logger = LoggerFactory.getLogger(PayUtil.class);
    /**
     * 生成随机字符串
     * @param length 生成字符串的长度
     * @return
     */
    public static String getRandomString(int length) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成唯一订单号
     */
    public static String getOutTradeNo(){
        Long currTime = System.currentTimeMillis();
        Random random = new Random();
        Integer num = random.nextInt(5);
        return currTime.toString().concat(num.toString());
    }

    /**
     * 创建PKCS12证书的KeyStore
     * @param mchId 商户id 证书密码默认为您的商户ID
     * @param keyPath 证书在服务器上的位置
     * @return
     */
    public static KeyStore getKeyStore(String mchId, String keyPath){
        FileInputStream instream = null;
        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance("PKCS12");
            instream = new FileInputStream(new File(keyPath));
            try {
                keyStore.load(instream, mchId.toCharArray());
            } finally {
                instream.close();
            }
        } catch (KeyStoreException e) {
            logger.error("创建 PKCS12 keyStore失败：", e);
        } catch (FileNotFoundException e) {
            logger.error("没有找到证书文件：" + keyPath ,e);
        } catch (Exception e){
            logger.error("创建 PKCS12 keyStore失败：", e);
        }
        return keyStore;
    }

    /**
     * 将map转为XML
     *
     * @param map
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String parseMapXML(Map<String, String> map) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = map.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v)) {
                sb.append("<" + k + ">" + v + "</" + k + ">\n");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 解析发来的请求（XML）
     *
     * @param
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseStrXml(String xml) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        Document document = DocumentHelper.parseText(xml);
        // 得到xml根元素
        Element root = document.getRootElement();
        map.put(root.getName(),root.getStringValue());
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        return map;
    }

    /**
     * 拼接出要进行加密的字符串
     *
     * @param map
     * @param key
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String createSignString(SortedMap<String, String> map, String key) {
        StringBuffer sb = new StringBuffer();
        Set es = map.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            sb.append(k + "=" + v + "&");
            //要采用URLENCODER的原始值！
        }
        String params = sb.toString() + "key=" + key;
        return params;
    }
}
