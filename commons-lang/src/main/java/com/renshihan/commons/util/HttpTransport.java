package com.renshihan.commons.util;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.CommunicationException;
import javax.net.ssl.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 使用httpClient形式发送报文
 * 
 * @HttpTransportImpl.java
 * @author zhanghongbo
 * @2012-7-5 下午5:12:06 www.gopay.com.cn Inc.All rights reserved.
 */
@SuppressWarnings("unchecked")
public class HttpTransport {
    private static Logger logger = LoggerFactory.getLogger(HttpTransport.class);

    private String url;
    /**
     * timeout.
     */
    private int timeout = 90000;
    /**
     * encoding
     */
    private String sendEncoding = "UTF-8";
    /**
     * retryConnTimes.
     */
    private int retryConnTimes = 5;
    
    /**
     * 响应日志的字符数量
     */
    private int charCountOfRespLog =0;

    private String contentType;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    private X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
		@Override
		public void verify(String host, SSLSocket ssl) throws IOException {
		}

		@Override
		public void verify(String host, X509Certificate cert)
				throws SSLException {
		}

		@Override
		public void verify(String host, String[] cns, String[] subjectAlts)
				throws SSLException {
		}

		@Override
		public boolean verify(String arg0, SSLSession arg1) {
			return true;
		}
	};
	
	private TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
	    public X509Certificate[] getAcceptedIssuers(){return null;}
	    public void checkClientTrusted(X509Certificate[] certs, String authType){}
	    public void checkServerTrusted(X509Certificate[] certs, String authType){}
	}};
    public String submit(Object obj) throws CommunicationException, IllegalArgumentException, KeyManagementException, NoSuchAlgorithmException {
        return submit(obj,getContentType());
    }
    public String submit(Object obj, String contentType) throws CommunicationException, IllegalArgumentException, KeyManagementException, NoSuchAlgorithmException {
        HttpClientBuilder httpClientBuilder= HttpClients.custom();
        if(url.contains("https")){
            SSLContext sslContext = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
            sslContext.init(null, trustAllCerts, new SecureRandom());
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            httpClientBuilder.setSSLSocketFactory(socketFactory);
        }
        CloseableHttpClient httpClient =httpClientBuilder
                .setRetryHandler(new DefaultHttpRequestRetryHandler(retryConnTimes, false))
                .build();
        logger.info("request url: \n" + url);
        HttpPost method = new HttpPost(url);

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .build();
        if (StringHelper.isNotEmpty(this.sendEncoding)) {
            method.setHeader(HTTP.CONTENT_ENCODING, sendEncoding);
        }
        method.setHeader(HTTP.USER_AGENT, "Rich Powered/1.0");
        if(null!=contentType){
            method.setHeader(HTTP.CONTENT_TYPE,contentType);
        }
        method.setConfig(requestConfig);

        if (obj != null) {
            if (obj instanceof Map) {
                Map<String, String> paraMap = (Map<String, String>) obj;
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();

                for (Iterator<String> iter = paraMap.keySet().iterator(); iter.hasNext();) {
                    String key = iter.next();
                    String value = "";
                    if (paraMap.get(key) != null) {
                        value = paraMap.get(key);
                    }

                    nvps.add(new BasicNameValuePair(key, value));
                }

                try {
                    if (StringUtils.isNotEmpty(this.sendEncoding))
                        method.setEntity(new UrlEncodedFormEntity(nvps, sendEncoding));
                    else
                        method.setEntity(new UrlEncodedFormEntity(nvps));
                } catch (UnsupportedEncodingException e) {
                    if (logger.isErrorEnabled()) {
                        logger.error("Fatal transport error while try connect to [" + url + "]");
                        logger.error("Cause: " + e.getMessage());
                    }
                    logger.error("HttpTransport Exception", e);
                }
            } else if (obj instanceof byte[]) {
                method.setEntity(new ByteArrayEntity((byte[]) obj));
                if (logger.isInfoEnabled()) {
                    logger.info("Sent Data: \n" + new String((byte[]) obj));
                }
            } else if (obj instanceof String){
                try {
                    method.setEntity(new StringEntity((String) obj));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("不支持参数错误",e);
                }
            } else {
                throw new IllegalArgumentException("submit(Object obj): obj should be Map or byte[]");
            }
        }

        int statusCode = 0;
        String result = "";
        CloseableHttpResponse response =null;
        try {
            response = httpClient.execute(method);
            statusCode = response.getStatusLine().getStatusCode();
            HttpEntity responseEntity = response.getEntity();
            result = EntityUtils.toString(responseEntity, sendEncoding);
            if (logger.isInfoEnabled()) {
                //只打印200个字符，防止商户网页源代码都输出到日志里  edit by fanghw 20160304
                logger.info("Received Data: \n" + (charCountOfRespLog>0&&result!=null&&result.length()>charCountOfRespLog?result.substring(0,charCountOfRespLog+1):result));
            }
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error("Fatal transport error while try connect to [" + url + "]");
                logger.error("Cause: " + e.getMessage());
            }
            logger.error("HttpTransport Exception", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("HttpTransport Exception", e);
                }
            }
        }
        if (statusCode != HttpStatus.SC_OK) {
            if (logger.isErrorEnabled()) {
                logger.error("Answer from [" + url + "] status code: [" + statusCode + "]");
            }
            throw new CommunicationException(String.valueOf(statusCode));
        }

        return result;
    }

    /**
     * HTTP请求
     * 
     * @param obj
     * @return
     * @throws CommunicationException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public String submit_https(Object obj) throws CommunicationException, NoSuchAlgorithmException, KeyManagementException {
    	
    	SSLContext sslContext = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
		sslContext.init(null, trustAllCerts, new SecureRandom());
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
		
		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLSocketFactory(socketFactory)
    			.setRetryHandler(new DefaultHttpRequestRetryHandler(retryConnTimes, false))
    			.build();
		
    	HttpPost method = new HttpPost(url);
		 
    	RequestConfig requestConfig = RequestConfig.custom()
    			.setSocketTimeout(timeout)
    			.setConnectTimeout(timeout)
    			.setConnectionRequestTimeout(timeout)
    			.build();
        if (StringUtils.isNotEmpty(this.sendEncoding)) {
            method.setHeader(HTTP.CONTENT_ENCODING, sendEncoding);
        }
        
        method.setHeader(HTTP.USER_AGENT, "Rich Powered/1.0");
        
        method.setConfig(requestConfig);

        if (obj != null) {
            if (obj instanceof Map) {
                Map<String, String> paraMap = (Map<String, String>) obj;
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                
                for (Iterator<String> iter = paraMap.keySet().iterator(); iter.hasNext();) {
                    String key = iter.next();
                    String value = "";
                    if (paraMap.get(key) != null) {
                        value = paraMap.get(key);
                    }

                    nvps.add(new BasicNameValuePair(key, value));
                }
                
                try {
                	if (StringUtils.isNotEmpty(this.sendEncoding))
                		method.setEntity(new UrlEncodedFormEntity(nvps, sendEncoding));
                	else
                		method.setEntity(new UrlEncodedFormEntity(nvps));
				} catch (UnsupportedEncodingException e) {
					if (logger.isErrorEnabled()) {
		                logger.error("Fatal transport error while try connect to [" + url + "]");
		                logger.error("Cause: " + e.getMessage());
		            }
					logger.error("HttpTransport Exception", e);
				}
            } else if (obj instanceof byte[]) {
                method.setEntity(new ByteArrayEntity((byte[]) obj));
                if (logger.isInfoEnabled()) {
                    logger.info("Sent Data: \n" + new String((byte[]) obj));
                }
            } else {
                throw new IllegalArgumentException("submit(Object obj): obj should be Map or byte[]");
            }
        }

        int statusCode = 0;
        String result = "";
        CloseableHttpResponse response =null;
        try {
        	response = httpClient.execute(method);
            statusCode = response.getStatusLine().getStatusCode();
            HttpEntity responseEntity = response.getEntity();
            result = EntityUtils.toString(responseEntity, sendEncoding);
            if (logger.isInfoEnabled()) {
                //只打印200个字符，防止商户网页源代码都输出到日志里  edit by fanghw 20160304
                logger.info("Received Data: \n" + (charCountOfRespLog>0&&result!=null&&result.length()>charCountOfRespLog?result.substring(0,charCountOfRespLog+1):result));
            }
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error("Fatal transport error while try connect to [" + url + "]");
                logger.error("Cause: " + e.getMessage());
            }
            logger.error("HttpTransport Exception", e);
        } finally {
            if (response != null) {
            	try {
					response.close();
				} catch (IOException e) {
					logger.error("HttpTransport Exception", e);
				}
            }
        }
        if (statusCode != HttpStatus.SC_OK) {
            if (logger.isErrorEnabled()) {
                logger.error("Answer from [" + url + "] status code: [" + statusCode + "]");
            }
            throw new CommunicationException(String.valueOf(statusCode));
        }

        return result;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
		return url;
	}

	public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setSendEncoding(String sendEncoding) {
        this.sendEncoding = sendEncoding;
    }

    public void setRetryConnTimes(int retryConnTimes) {
        this.retryConnTimes = retryConnTimes;
    }


	public int getCharCountOfRespLog() {
		return charCountOfRespLog;
	}

	public void setCharCountOfRespLog(int charCountOfRespLog) {
		this.charCountOfRespLog = charCountOfRespLog;
	}
    /**
     * 新渠道 返回参数解析
     * @return
     */
    private String analysisReq(String result){
        if(StringUtils.isEmpty(result)){
            logger.error("渠道返回参数解析失败，错误信息：{}",result);
            return "100001";
        }
        try{
            String[] respBodys = result.split("<ResponseBody>");
            String[] returnCodes = respBodys[1].split("<returnCode>");
            String[] types = returnCodes[1].split("<type>");
            String type = types[1].split("</type>")[0];
            String[] messages = returnCodes[1].split("<message>");
            String message = messages[1].split("</message>")[0];
            if(!"S".equals(type)){
                logger.error("调用渠道，发送短信错误。错误信息:{}",message);
                return "100003";
            }
        } catch(Exception e){
            logger.error("渠道返回参数解析失败，错误信息：{}",result);
            return "100002";
        }
        return "000000";
    }
}
