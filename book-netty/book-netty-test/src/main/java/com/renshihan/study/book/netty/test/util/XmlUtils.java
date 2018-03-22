/**
 * Project Name:pay-protocol
 * File Name:Xml.java
 * Package Name:cn.swiftpass.pay.protocol
 * Date:2014-8-10下午10:48:21
 *
*/

package com.renshihan.study.book.netty.test.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * ClassName:Xml
 * Function: XML的工具方法
 * Date:     2014-8-10 下午10:48:21 
 * @author    
 */
public class XmlUtils {
    
    /** <一句话功能简述>
     * <功能详细描述>request转字符串
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String parseRequst(HttpServletRequest request){
        String body = "";
        try {
            ServletInputStream inputStream = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while(true){
                String info = br.readLine();
                if(info == null){
                    break;
                }
                if(body == null || "".equals(body)){
                    body = info;
                }else{
                    body += info;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }            
        return body;
    }
    
    public static String parseXML(Map parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if (null != v && !"".equals(v) && !"appkey".equals(k)) {
                sb.append("<" + k + ">" + parameters.get(k) + "</" + k + ">\n");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 从request中获得参数Map，并返回可读的Map
     * 
     * @param request
     * @return
     */
    public static SortedMap getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        SortedMap returnMap = new TreeMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value.trim());
        }
        return returnMap;
    }
    
    /**
     * 转XMLmap
     * @author  
     * @param xmlBytes
     * @param charset
     * @return
     * @throws Exception
     */
    public static Map<String, String> toMap(byte[] xmlBytes, String charset) throws Exception {
        SAXReader reader = new SAXReader(false);
        InputSource source = new InputSource(new ByteArrayInputStream(xmlBytes));
        source.setEncoding(charset);
        Document doc = reader.read(source);
        Map<String, String> params = XmlUtils.toMap(doc.getRootElement());
        return params;
    }
    
    /**
     * 转MAP
     * @author  
     * @param element
     * @return
     */
    public static Map<String, String> toMap(Element element){
        Map<String, String> rest = new HashMap<String, String>();
        List<Element> els = element.elements();
        for(Element el : els){
            rest.put(el.getName().toLowerCase(), el.getTextTrim());
        }
        return rest;
    }
    
    public static String toXml(Map<String, String> params){
        StringBuilder buf = new StringBuilder();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        buf.append("<xml>");
        for(String key : keys){
            buf.append("<").append(key).append(">");
            buf.append("<![CDATA[").append(params.get(key)).append("]]>");
            buf.append("</").append(key).append(">\n");
        }
        buf.append("</xml>");
        return buf.toString();
    }
    /**
     * 拼测试用的HTML页面
     * */
    public static String unionCreatHtml(String requestUrl,
                                        Map<String, String> requestMap) {
        // TODO Auto-generated method stub
        StringBuffer sf = new StringBuffer();
        sf.append("<html><head></head><body>");
        sf.append("<form id = 'pay_form' action='" + requestUrl
                + "' method='post'>");
        if (null != requestMap && 0 != requestMap.size()) {
            Set<Map.Entry<String, String>> set = requestMap.entrySet();
            Iterator<Map.Entry<String, String>> it = set.iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> ey = it.next();
                String key = ey.getKey();
                String value = ey.getValue();
                sf.append("<input type='hidden' name='" + key + "' id='" + key
                        + "' value='" + value + "'/>");
            }
        }
        sf.append("</form>");
        sf.append("</body>");
        sf.append("<script type='text/javascript'>");
        sf.append("document.all.pay_form.submit();");
        sf.append("</script>");
        sf.append("</html>");
        return sf.toString();
    }
    public static void main(String[] args)throws Exception {
        String msg="<html><head></head><body><form id = 'pay_form' action='https://cashier.hefupal.com//paygate/v1/web/pay' method='post'><input type='hidden' name='YUL1' id='YUL1' value=''/><input type='hidden' name='amount' id='amount' value='5000'/><input type='hidden' name='bankId' id='bankId' value='01000000'/><input type='hidden' name='bizType' id='bizType' value='10101'/><input type='hidden' name='buyerId' id='buyerId' value='007'/><input type='hidden' name='buyerName' id='buyerName' value='rVM30hkQwKB1EFfDg2Aeh71qhGKRyNmPflR3Kfy6jOSCQJqIrySco/OtlyhQliyF5a1/bxaPUwVxIRUrBEjr3t4sr5ES0K49aA51EzJHxOlKc8W/XvtzzxJFfBtlzrUjr0wK9JBTiPBEUfnRa5tWCF3g8wjN+eOiWDWrWne9U5E='/><input type='hidden' name='cardType' id='cardType' value='01'/><input type='hidden' name='channelNo' id='channelNo' value='03'/><input type='hidden' name='contact' id='contact' value='rBU0KLKygbdmL6yvx17+IsRR/TwaIeh9vX9huWvdIMpVmPz+3py0XsXJef3vnwl7Xp9BKO46igvgSBm9B3xhUqmIkaWHxBpsGb3HF7zb8M2PVaqljCHrDIIFxinCjGepwPS/dVUGZW8lTWP6+/Uw7uII65tAxn7gsFve0Fgw3oQ='/><input type='hidden' name='currency' id='currency' value='CNY'/><input type='hidden' name='goodsInfo' id='goodsInfo' value='商品信息'/><input type='hidden' name='goodsName' id='goodsName' value='shopNames'/><input type='hidden' name='goodsNum' id='goodsNum' value='1'/><input type='hidden' name='ip' id='ip' value='172.1.1.1'/><input type='hidden' name='merchantNo' id='merchantNo' value='S20170830011671'/><input type='hidden' name='notifyUrl' id='notifyUrl' value='http://59.110.152.80:8102/webdemo/notify.do'/><input type='hidden' name='referer' id='referer' value='https://cashier.hefupal.com/paygate/v1/web/pay'/><input type='hidden' name='remark' id='remark' value='测试机哦remark'/><input type='hidden' name='returnUrl' id='returnUrl' value='http://59.110.152.80:8102/webdemo/qnotify.do'/><input type='hidden' name='sign' id='sign' value='bs0GuZkrEsQWg91z4zk8KPXpnvyNAl5+zG0vXZ7BBRaIvYBR1C4WJ15dYWQFKx+4By3GATpJyrtVVTLX/W5eea4XOAxsMn/SkcxU+PN6pLBPTOAbCM/BwLAN04nCgrM9wE110A3pH3VlrDofnw76MErk6faU3KgISi8MFO22JyY='/><input type='hidden' name='tranSerialNum' id='tranSerialNum' value='20180106111645254'/><input type='hidden' name='tranTime' id='tranTime' value='20180106111645'/><input type='hidden' name='valid' id='valid' value=''/><input type='hidden' name='version' id='version' value='v1'/></form></body><script type='text/javascript'>document.all.pay_form.submit();</script></html>\n";
        Map<String,String> xml=XmlUtils.toMap(msg.getBytes(),"UTF-8");
        System.out.println("---"+xml);
    }
}

