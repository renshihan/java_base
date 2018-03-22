package com.renshihan.study.book.netty.test.util;

import com.ielpm.payutil.http.Httpz;
import com.ielpm.payutil.serialized.Serializeds;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 报文工具类
 * 
 * @author FYW
 */
public class MsgUtil {

	private final static Logger logger = Logger.getLogger(MsgUtil.class);
	
	
	
	/**
	 * 反序列化数据
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getReqMsg(HttpServletRequest request){
		// 获取交易数据
		String tranData = request.getParameter("tranData").replaceAll(" ", "+");
	    Map<String, String> tranDataMap = null;
		try {
			tranDataMap = new Serializeds().toObject(tranData);
		} catch (IOException e) {
			logger.error("序列化报文异常", e);
		}
		logger.debug("商户请求数据序列值：" + tranData);
	    logger.info("商户请求数据：" + tranDataMap);
	    return tranDataMap;
	}
	
	/**
	 * 序列化数据
	 * 
	 * @param tranDataMap
	 * @return
	 */
	public static String serializeds(Map<String, String> tranDataMap){
		String tranData = null;
		try {
			tranData = new Serializeds().toString(tranDataMap);
		} catch (IOException e) {
			logger.error("序列化报文异常", e);
		}
		return tranData;
	}

	
	/**
	 * 往渠道发送数据
	 * 
	 * @param url
	 *            通讯地址
	 * @param tranDataMap
	 *            发送参数
	 * @return 应答消息
	 */
	public static String sendMsg(String url, Map<String, String> tranDataMap) {
		try {
			Httpz handler = new Httpz("UTF-8",30000,30000);
			String respStr = handler.post(url, tranDataMap);
			return respStr;
		} catch (Exception e) {
			logger.error("发送数据异常", e);
		}
		return null;
	}
	
	public String unzip(String filename) throws IOException {
		String result = null;
		File file = new File(filename);
		if(file.exists()){
			Hex hex = new Hex();
			InputStream in = new FileInputStream(file);
			result = hex.encodeHexString(IOUtils.toByteArray(new FileInputStream(file)));
		}
        return result;
	}
	
	public static Map<String, String> jsonToMap(String jsonStr){
		Map<String, String> map = new HashMap<String, String>();
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		Iterator keys = jsonObject.keys();
		while(keys.hasNext()){
			String key = keys.next().toString();
			String value = jsonObject.getString(key);
			map.put(key, value);
		}
		String body = map.get("body");
		if(null != body && !"".equals(body)){
			JSONObject bodyJsonObject = JSONObject.fromObject(body);
			Iterator bodyKey = bodyJsonObject.keys();
			while(bodyKey.hasNext()){
				String key = bodyKey.next().toString();
				String value = bodyJsonObject.getString(key);
				map.put(key, value);
			}
		}
		return map;
	}
	
	public static String getAmount(String amount) {
		if(null == amount || "".equals(amount)) return "";
		if(amount.length() > 2) {
			amount = amount.substring(0, amount.length() -2) + "." + amount.substring(amount.length() -2, amount.length());
		}
		if(amount.length() == 2) {
			amount = "0." + amount;
		}
		if(amount.length() == 1) {
			amount = "0.0" + amount;
		}
		return amount;
	}
}
