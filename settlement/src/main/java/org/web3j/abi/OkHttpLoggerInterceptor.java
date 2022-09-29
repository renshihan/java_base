package org.web3j.abi;


import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j(topic = "okhttp")
public class OkHttpLoggerInterceptor implements Interceptor {

    private boolean printDetail;

    public OkHttpLoggerInterceptor(boolean printDetail) {
        this.printDetail = printDetail;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        long startTime = System.currentTimeMillis();
        Request request = chain.request();
        StringBuilder stringBuilder = new StringBuilder();
        if (printDetail) {
            printRequestMessage(request, stringBuilder);
        }
        Response response = chain.proceed(request);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        if (printDetail) {
            printResponseMessage(response, stringBuilder);
            stringBuilder.append(String.format("%s:%d", "cost", duration));
            log.info(stringBuilder.toString());
        }

        return response;
    }

    /**
     * 打印请求消息
     *
     * @param request 请求的对象
     */
    private void printRequestMessage(Request request, StringBuilder sb) {
        if (request == null) {
            return;
        }
        sb.append(String.format("%s:%s", "url", request.url().url().toString())).append("|");
        RequestBody requestBody = request.body();
        if (requestBody == null) {
            return;
        } else {
            try {
                Buffer bufferedSink = new Buffer();
                request.body().writeTo(bufferedSink);
                Charset charset = requestBody.contentType().charset();
                charset = charset == null ? Charset.forName("utf-8") : charset;
                sb.append(String.format("%s:%s", "body", bufferedSink.readString(charset))).append("|");
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }

    /**
     * 打印返回消息
     *
     * @param response 返回的对象
     */
    private void printResponseMessage(Response response, StringBuilder sb) {
        sb.append(String.format("%s:%b", "ok", response != null && response.isSuccessful())).append("|");
    }
}
