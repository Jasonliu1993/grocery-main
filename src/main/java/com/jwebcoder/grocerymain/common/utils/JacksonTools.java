package com.jwebcoder.grocerymain.common.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JacksonTools {

    private static final Logger logger = LoggerFactory.getLogger(JacksonTools.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T readValueForObject(String value, Class<T> clazz) {
        T object = null;
        try {
            if (StringUtils.isNotEmpty(value))
                object = mapper.readValue(value, clazz);
        } catch (IOException e) {
            logger.error("解析JSon出现问题，JSon:{}", value, e);
        }
        return object;
    }

    public static String writteObjectToValue(Object o) {
        String json = null;
        try {
            json = mapper.writeValueAsString(o);
        } catch (IOException e) {
            logger.error("转换到JSon时出现问题", e);
        }
        return json;
    }

    public static <T> List<T> readValueForList(String json, Class<T> beanClass) {
        try {

            return (List<T>) mapper.readValue(json, getCollectionType(List.class, beanClass));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map readValueForMap(String json) {
        try {

            return mapper.readValue(json, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

}
