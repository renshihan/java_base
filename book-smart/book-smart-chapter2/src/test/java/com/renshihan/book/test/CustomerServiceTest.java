package com.renshihan.book.test;

import com.renshihan.book.smart.service.CustomerService;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by admin on 2018/2/5.
 */
public class CustomerServiceTest {
    private final CustomerService customerService;

    public CustomerServiceTest() {
        this.customerService = new CustomerService();
    }
    @Before
    public void init(){
        //TODO 初始化数据库
    }

}
