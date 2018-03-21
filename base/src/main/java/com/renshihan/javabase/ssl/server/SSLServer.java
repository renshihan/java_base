package com.renshihan.javabase.ssl.server;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;

/**
 * Created by admin on 2017/7/14.
 */
public class SSLServer {
    //服务器端授权的用户名和密码
    private static final String USER_NAME="renshihan";
    private static final String PASSWORD="123456";
    //服务器端保密内容
    private static final String CONTENT=
            "This is confidential content from client X, for your eye!";
    private SSLServerSocket sslServerSocket=null;
    public SSLServer()throws IOException{
        //通过套接字
        SSLServerSocketFactory socketFactory=(SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
        sslServerSocket=(SSLServerSocket)socketFactory.createServerSocket(8999);
    }
    private void runServer(){
        while (true){
            try {
                System.out.println("等待链接./.......");
                //服务器端套接字进入等待，等待客户端连接
                SSLSocket sslSocket=(SSLSocket) sslServerSocket.accept();
                //获取服务器套接字输入流
//                Buffered
            }catch (IOException e){

            }
        }
    }

}
