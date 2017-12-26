package com.renshihan.javabase.base.map;

import java.util.*;

/**
 * Created by admin on 2017/8/18.
 * 扩展HashMap对象
 */
public abstract class BaseDto extends HashMap<String,Object> implements Dto{

    public BaseDto() {
        super();
    }

    public BaseDto(int i, float v) {
        super(i, v);
    }

    public BaseDto(int i) {
        super(i);
    }

    public BaseDto(Map<? extends String, ?> map) {
        super(map);
    }
    @Override
    public String getString(String key) {
        return Objects.toString(key);
    }

    public <E> List<E> getList(String key) {
        Object o=this.get(key);
        if(o instanceof List){
            return (List<E>) o;
        }
        return new ArrayList<E>();
    }
}
