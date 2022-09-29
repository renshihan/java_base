package com.renshihan.settlement.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.FileOutputStream;
import java.io.InputStream;

public class ImgUtils {
    public static void main(String[] args) {
        String url="https://cdn.pixabay.com/photo/2016/11/29/05/45/astronomy-1867616_1280.jpg";
        String imgPath="/Users/bj00078ml/other/java_base/settlement/src/main/resources/img/";
        getImage(url,imgPath);

    }
    public static void getImage(String url, String filePath){
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        String[] split = url.split("/");
        String fileName =split[split.length-1];
        System.out.println("fileName="+fileName+",get请求路径：" + url);
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .addHeader("Accept", "*/*")
                    .addHeader("Content-Type", "image/jpeg")
                    .build();
            ResponseBody body = client.newCall(request).execute().body();
//            inputStream = body.byteStream();
            byte[] bytes = body.bytes();
            String result = ImageBase64Utils.bytesToBase64(bytes);
            //输出图片的路径
            String fileOutPath = filePath;
            ImageBase64Utils.base64ToImageFile(result, fileOutPath+fileName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
