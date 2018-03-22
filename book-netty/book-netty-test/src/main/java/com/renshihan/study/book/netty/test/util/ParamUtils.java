package com.renshihan.study.book.netty.test.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.TreeMap;

public class ParamUtils {
	/**
     * 解析request param到map中
     * @param request
     * @return
     */
    public static TreeMap<String, String> getParamMap(HttpServletRequest request){
          TreeMap<String, String> transMap = new TreeMap<String, String>();
          Enumeration<String> enu = request.getParameterNames();
          String t = null;
          while(enu.hasMoreElements()){
               t = enu.nextElement();
               transMap.put(t, request.getParameter(t));
          }
          return transMap;
    }

}
