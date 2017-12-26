package com.renshihan.javabase.ssl.client;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

/**
 * Created by admin on 2017/7/14.
 */
public class SSLClient {
    private SSLSocket sslSocket=null;

    public SSLClient()throws IOException {
        SSLSocketFactory socketFactory=(SSLSocketFactory) SSLSocketFactory.getDefault();
        sslSocket=(SSLSocket) socketFactory.createSocket("localhost",8999);
    }
    public void connect(){
        try {
            //获取客户端套接字刘
            PrintWriter output=new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            //将用户名和密码通过输出流发送到服务器端
            String username="renshihan";
            String password="123456";
            output.println(username);
            output.println(password);
            output.flush();
            //获取客户端套接字输入流
            BufferedReader input=new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            //从输入流中读取服务器传过来的数据内容，并答应出来
            String response=input.readLine();
            response+="\n" +input.readLine();
            System.out.println("resonpse--->"+response);
            //关闭流
            output.close();
            input.close();
            sslSocket.close();

        }catch (IOException e){
            e.printStackTrace();

        }finally {
            System.exit(0);
        }
    }

    public static void main(String[] args)throws IOException {
        new SSLClient().connect();
    }
}
