package org.web3j.abi;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.jctools.maps.NonBlockingHashMap;
import org.reflections.Reflections;
import org.springframework.cglib.core.ReflectUtils;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.primitive.PrimitiveType;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Slf4j
public class TypeHelper {

    static NonBlockingHashMap<Object, Class> classMap = new NonBlockingHashMap();
    static NonBlockingHashMap<Object, Object> defaultValueMap = new NonBlockingHashMap();

    public static <T extends Type> T newTypeInstance(String typeName, Object value) {
        try {

            TypeReference typeReference = TypeReference.makeTypeReference(typeName);
            if (typeReference.getSubTypeReference() != null) {
                Class subClazz = typeReference.getSubTypeReference().getClassType();
                return (T) new DynamicArray(subClazz, Utils.typeMap((List) value, subClazz));
            } else {
                Class<? extends Type> type = AbiTypes.getType(typeName, false);
                if (IntType.class.isAssignableFrom(type)) {
                    value = new BigInteger(value.toString());
                }
                return (T) ReflectUtils.newInstance(type, new Class[]{value.getClass()}, new Object[]{value});
            }
        } catch (Exception e) {
            log.error("fail to create instance for type:{},value:{}", typeName, value, e);
            return null;
        }
    }

    static {
        Reflections reflections = new Reflections("org.web3j.abi");
        Set<Class<? extends Type>> modules = reflections.getSubTypesOf(Type.class);
        modules.forEach(x -> {
            if (NumericType.class.isAssignableFrom(x)) {
                classMap.put(x, BigInteger.class);
                defaultValueMap.put(x, BigInteger.ZERO);
            } else if (BytesType.class.isAssignableFrom(x)) {
                classMap.put(x, byte[].class);
                defaultValueMap.put(x, new byte[0]);
            } else if (StaticArray.class.isAssignableFrom(x) || Array.class.isAssignableFrom(x)) {
                classMap.put(x, List.class);
                defaultValueMap.put(x, Lists.newArrayList());
            } else if (Bool.class.isAssignableFrom(x)) {
                classMap.put(x, Boolean.class);
                defaultValueMap.put(x, false);
            } else if (PrimitiveType.class.isAssignableFrom(x)) {
                classMap.put(x, Number.class);
                defaultValueMap.put(x, false);
            } else {
                classMap.put(x, String.class);
                defaultValueMap.put(x, "");
            }
        });
    }

    public static Class detectRvClass(String typeName) {
        Class<? extends Type> type = AbiTypes.getType(typeName, false);
        return classMap.getOrDefault(type, String.class);
    }

    public static Object defaultValue(String typeName) {
        try {
            Class<? extends Type> type = null;
            TypeReference typeReference = TypeReference.makeTypeReference(typeName);
            if (typeReference.getSubTypeReference() != null) {
                type = typeReference.getSubTypeReference().getClassType();
                return Lists.newArrayList(defaultValueMap.getOrDefault(type, null));
            } else {
                type = AbiTypes.getType(typeName, false);
                return defaultValueMap.getOrDefault(type, null);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static Object defaultValue(List<String> typeNames) {
        if (typeNames == null || typeNames.size() == 0) {
            return null;
        } else if (typeNames.size() == 1) {
            return defaultValue(typeNames.get(0));
        } else {
            Object[] result = new Object[typeNames.size()];
            for (int i = 0; i < typeNames.size(); i++) {
                result[i] = defaultValue(typeNames.get(i));
            }
            return result;
        }
    }

    public static void main(String[] args) {
        String typename = "string";
        Object value = Lists.newArrayList("0xC387E156B51a1733BebFd6a1bcc16f986976D9b9", "0xd2050719ea37325bdb6c18a85f6c442221811fac");
        System.out.println(TypeHelper.newTypeInstance("uint256", 1).toString());
        System.out.println(TypeHelper.defaultValue("short").toString());
        System.out.println(TypeHelper.defaultValue("uint256").toString());
        System.out.println(TypeHelper.defaultValue("address").toString());
        System.out.println(TypeHelper.defaultValue("address[]").toString());
        System.out.println(TypeHelper.defaultValue("uint256[]").toString());

    }


}
