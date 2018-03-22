package com.renshihan.study.book.netty.test.chapter1;

import com.renshihan.study.book.netty.test.util.HttpTransport;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.naming.CommunicationException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * @author renshihan@winchannel.net
 * @date 2018/3/22 11:28
 */
@Slf4j
public class ServerTest {
    @Test
    public void server1(){
        HttpTransport httpTransport=new HttpTransport();
        httpTransport.setUrl("http://localhost:8888");
        try {
            String msg=httpTransport.submit(new HashMap<>());
            log.info("返回数据----"+msg);
        } catch (CommunicationException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
