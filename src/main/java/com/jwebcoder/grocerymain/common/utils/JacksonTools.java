package com.jwebcoder.grocerymain.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JacksonTools {

    private static final Logger logger = LoggerFactory.getLogger(JacksonTools.class);

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            logger.error("解析JSon出现问题，json:{}", json, e);
            return null;
        }
    }

    public static <T> T readValue(String json, TypeReference<T> typeReference) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            logger.error("解析JSon出现问题，json:{}", json, e);
            return null;
        }
    }

    public static String writteObjectToValue(Object o) {
        String json = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(o);
        } catch (IOException e) {
            logger.error("转换到JSon时出现问题", e);
        }
        return json;
    }

}
