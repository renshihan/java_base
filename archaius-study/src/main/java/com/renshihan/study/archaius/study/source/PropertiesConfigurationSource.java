package com.renshihan.study.archaius.study.source;

import com.netflix.config.PollResult;
import com.netflix.config.PolledConfigurationSource;
import com.renshihan.study.archaius.study.utils.PropertiesLoaderUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesConfigurationSource implements PolledConfigurationSource{
    @Override
    public PollResult poll(boolean b, Object o) throws Exception {
        Map<String,Object> map=new HashMap<>();

        PropertiesLoaderUtils propertiesLoaderUtils=new PropertiesLoaderUtils("channel/a.properties");
        Properties properties=propertiesLoaderUtils.getProperties();
        for(String key:properties.stringPropertyNames()){
            map.put(key,properties.getProperty(key));
        }
        PollResult result=PollResult.createFull(map);
        return result;
    }
}
