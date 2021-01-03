package com.suleymangunduz.casestudyapp.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonUtils {

    private static ObjectMapper ourInstance;

    private JacksonUtils() {
    }

    private static ObjectMapper getInstance() {
        if (ourInstance == null) {
            ourInstance = new ObjectMapper();
            ourInstance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ourInstance.enable(SerializationFeature.INDENT_OUTPUT);
        }
        return ourInstance;
    }

    public static <T> T readValue(String value, Class<T> clazz) {
        try {
            return getInstance().readValue(value, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T readValue(String value, TypeReference<T> typeReference) {
        try {
            return getInstance().readValue(value, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String writeValueAsString(Object object) {
        try {
            return getInstance().writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}