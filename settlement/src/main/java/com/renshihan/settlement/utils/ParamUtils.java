package com.renshihan.settlement.utils;


import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class ParamUtils {

    public static boolean isEmpty(Object obj) {
        if (Objects.isNull(obj)) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).length() == 0;
        }

        if(obj instanceof Collection){
            return ((Collection) obj).isEmpty();
        }

        if(obj instanceof Long){
            return !NumberUtils.gtZero((Long)obj);
        }

        if(obj instanceof Map){
            return ((Map<?, ?>) obj).isEmpty();
        }

        if(obj instanceof Integer){
            return !NumberUtils.gtZero((Integer) obj);
        }

        return true;
    }


    public static boolean isNotEmpty(Object obj){
        return !isEmpty(obj);
    }

}
