package com.renshihan.settlement.config;

import lombok.Data;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.web3j.abi.OkHttpLoggerInterceptor;

import javax.net.ssl.X509TrustManager;
import java.util.concurrent.TimeUnit;

@Data
public class HttpConfig {

    private int callTimeoutMillis = 100000;

    private int connectTimeoutMillis = 100000;

    private int pingIntervalMillis = 200000;

    private int readTimeoutMillis = 100000;

    private int writeTimeoutMillis = 100000;

    private int maxIdleConnections = 10000;

    private int keepAliveSeconds = 1000;

    private int maxRequests = 20000;

    private int maxRequestsPerHost = 2000;

    public OkHttpClient createHttp(boolean printDetail) {
        return createHttp(printDetail, true);
    }
    public OkHttpClient createHttp(boolean printDetail, boolean monitor) {
        X509TrustManager manager = SSLSocketClientUtil.getX509TrustManager();
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .callTimeout(callTimeoutMillis, TimeUnit.MILLISECONDS)
                .connectTimeout(connectTimeoutMillis, TimeUnit.MILLISECONDS)
                .sslSocketFactory(SSLSocketClientUtil.getSocketFactory(manager),manager)    //忽略校验
                .hostnameVerifier(SSLSocketClientUtil.getHostnameVerifier())    //忽略校验
                .pingInterval(pingIntervalMillis, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeoutMillis, TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeoutMillis, TimeUnit.MILLISECONDS)
                .connectionPool(new ConnectionPool(maxIdleConnections, keepAliveSeconds, TimeUnit.SECONDS))
                .retryOnConnectionFailure(true)
                .followRedirects(true);
        if (monitor) {
            builder.addInterceptor(new OkHttpLoggerInterceptor(printDetail));
        }
        OkHttpClient client = new OkHttpClient(builder);
        client.dispatcher().setMaxRequests(maxRequests);
        client.dispatcher().setMaxRequestsPerHost(maxRequestsPerHost);
        return client;
    }



}
