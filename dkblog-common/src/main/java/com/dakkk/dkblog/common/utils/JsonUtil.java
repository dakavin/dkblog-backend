package com.dakkk.dkblog.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: JsonUtil
 * Package: com.dakkk.dkblog.common.utils
 *
 * @Create 2024/4/21 19:50
 * @Author dakkk
 * Description:
 */
@Slf4j
public class JsonUtil {
    /**
     * jackson主要对象，用于操作对象和json字符串
     */
    public static final ObjectMapper INSTANCE = new ObjectMapper();

    /**
     * 将obj转换为json字符串
     * @param obj obj对象
     * @return json字符串
     */
    public static String toJsonString(Object obj){
        try {
            return INSTANCE.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return obj.toString();
        }
    }
}
