package com.renshihan.study.book.netty.test.util;

import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zhangzw on 2015/12/1.
 */
public class RequestUtils {


    public static TreeMap<String, String> getParameterMap(HttpServletRequest request){
        TreeMap<String, String> map = new TreeMap<String, String>();
        Enumeration<String> en = request.getParameterNames();
        while (en.hasMoreElements()) {
            String param = en.nextElement();
            String value = request.getParameter(param.toString());
            map.put(param, SafeUtil.clear(value));
        }
        return map;
    }
    public static String getParameterMap(InputStream inputStream)throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();

    }

    public static Map<String, String> getParamsMap(String queryString, String enc) {
        Map<String, String> paramsMap = new TreeMap<String, String>();
        if (queryString != null && queryString.length() > 0) {
            int ampersandIndex, lastAmpersandIndex = 0, tmpIndex = 0;
            String subStr, param, value;
            do {
                ampersandIndex = queryString.indexOf('&', lastAmpersandIndex) + 1;
                if (ampersandIndex > 0) {
                    subStr = queryString.substring(lastAmpersandIndex, ampersandIndex - 1);
                    lastAmpersandIndex = ampersandIndex;
                } else {
                    subStr = queryString.substring(lastAmpersandIndex);
                }
                
                tmpIndex = subStr.indexOf('=');
                param = subStr.substring(0,tmpIndex);
                value = subStr.substring(tmpIndex+1);
                try {
                    value = URLDecoder.decode(value, enc);
                } catch (UnsupportedEncodingException ignored) {
                }
//                if (paramsMap.containsKey(param)) {
//                    values = (String[])paramsMap.get(param);
//                    int len = values.length;
//                    newValues = new String[len + 1];
//                    System.arraycopy(values, 0, newValues, 0, len);
//                    newValues[len] = value;
//                } else {
//                    newValues = new String[] { value };
//                }
                if(!"".equals(param))
                    paramsMap.put(param, SafeUtil.clear(value));
            } while (ampersandIndex > 0);
        }
        return paramsMap;
    }
    
    
    
    public static String getIpAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        if (!Strings.isNullOrEmpty(ip) && ip.contains(","))
        {
            String[] ips = ip.split(",");
            ip = ips[ips.length - 1];
        }
        //转换IP 格式
        if(!Strings.isNullOrEmpty(ip)){
            ip=ip.replace(".", "_");
        }
        return ip;
    }




}
