package com.renshihan.settlement.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.renshihan.settlement.config.HttpConfig;
import com.renshihan.settlement.enums.MetadataCode;
import com.renshihan.settlement.exception.BizException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.*;

@Slf4j
public class HttpHostUtil {
    public static final String SENTINEL_CONTRACT_MAIN_TOKEN = "contract_main_token";

    public enum Type {
        IPFS,
        HTTP;
    }

    private HttpHostUtil() {
    }

    /**
     * 服务支持的域名
     */
    private static final Map<String, Set<String>> SERVICE_HOST_MAP = Maps.newHashMap();
    /**
     * 服务的HTTP Client
     */
    private static final Map<String, OkHttpClient> SERVICE_CLIENT = Maps.newHashMap();


    static {
        OkHttpClient httpClient = new HttpConfig().createHttp(false);
        SERVICE_CLIENT.put(Type.HTTP.name().toLowerCase(),httpClient);
    }
    @Setter
    private static int retryCount = 3;
    @Setter
    private static boolean enableRetry;


    public static void putHostClient(String type, String hostUrl, OkHttpClient okHttpClient) {
        type = type.toLowerCase(Locale.ROOT);
        if("*".equals(hostUrl)){
            //允许任何url
            SERVICE_CLIENT.put(type, okHttpClient);
            return;
        }
        //如果之前有，则继续使用之前
        if (SERVICE_HOST_MAP.containsKey(type)) {
            SERVICE_HOST_MAP.get(type).add(hostUrl);
            return;
        }
        Set<String> urls = Sets.newHashSet();
        urls.add(hostUrl);
        SERVICE_HOST_MAP.put(type, urls);
        SERVICE_CLIENT.put(type, okHttpClient);
    }



    public static <T> T get(Type type, String uri, Class<T> tClass) {
        if(Type.HTTP.equals(type)){
            return getByUrl(Type.HTTP, uri, tClass);
        }

        List<String> hosts = new ArrayList(SERVICE_HOST_MAP.get(type.name().toLowerCase(Locale.ROOT)));

        T supplier = null;
        for (int i = 0; i < retryCount; i++) {
            // 随机选取一个host
            String host = hosts.get(CommonUtils.randomInt(0,hosts.size()));
            hosts.remove(host);
            Request request = new Request.Builder().url(host + uri).get().build();
            supplier = httpFunction(type, request, tClass);
            if(!enableRetry){
                return supplier;
            }
            if(null!=supplier){
                return supplier;
            }
        }
        return supplier;
    }

    public static String getRandomHost(Type type){
        if(Type.HTTP.equals(type)){
            return "";
        }

        List<String> hosts = new ArrayList(SERVICE_HOST_MAP.get(type.name().toLowerCase(Locale.ROOT)));
        return hosts.get(CommonUtils.randomInt(0,hosts.size()));
    }

    public static <T> T getByUrl(Type type, String url, Class<T> tClass) {
        T supplier = null;
        for (int i = 0; i < retryCount; i++) {
            // 随机选取一个host
            Request request = new Request.Builder().url(url).get().build();
            supplier = httpFunction(type, request, tClass);
            if(!enableRetry){
                return supplier;
            }
            if(null!=supplier){
                return supplier;
            }
        }
        return supplier;
    }


    public static <T> T httpFunction(Type type, Request request,Class<T> tClass) {
        try {
            OkHttpClient okHttpClient = getHttpClient(type);
            try (Response response = okHttpClient.newCall(request).execute()) {
                String respBody = response.body().string();
                if (response.code() != MetadataCode.SUCCESS.getCode()) {
                    String message = String.format("http invoke fail, code:%d, url:%s result:%s", response.code(), request.url(), respBody);
                    throw new BizException(MetadataCode.SYSTEM_ERROR, message);
                }
                if(tClass == String.class){
                    return (T) respBody;
                }
                System.out.println(respBody);
                // 转换对象
                return JSONObject.parseObject(respBody,tClass);
            }
        } catch (Exception e) {
            log.warn("contract http exception, httpType={}, url={}", type, request.url(), e);
            throw new BizException(MetadataCode.SYSTEM_ERROR, e.getMessage() + ", url=" + request.url(), e);
        }
    }

    private static OkHttpClient getHttpClient(Type httpType) {
        String type = httpType.name().toLowerCase(Locale.ROOT);
        return SERVICE_CLIENT.get(type);
    }

}
