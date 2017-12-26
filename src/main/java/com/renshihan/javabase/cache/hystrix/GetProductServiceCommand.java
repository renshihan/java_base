package com.renshihan.javabase.cache.hystrix;

import com.netflix.hystrix.HystrixCommand;

/**
 * Created by admin on 2017/11/1.
 */
public class GetProductServiceCommand extends HystrixCommand<String>{
    private Long id;
    private ProductService
    @Override
    protected String run() throws Exception {
        return null;
    }

    @Override
    protected String getCacheKey() {
        return "product-"+id;

    }
}
