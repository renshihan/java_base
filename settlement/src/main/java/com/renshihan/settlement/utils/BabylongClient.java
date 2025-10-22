package com.renshihan.settlement.utils;

import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Var;

import java.util.Map;

public interface BabylongClient {

    @Post(value = "{url}/",contentType="application/json")
    String call(@Var("url")String url,@Body Map<String, Object> body);

}
