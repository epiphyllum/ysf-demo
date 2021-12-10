package com.koolyun.ysf.ysfdemo.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisUtil {

    @Autowired
    StringRedisTemplate stringRedisTemplate;  // RedisTemplate<String, String>  ---- k  v  只能是  String

    @Autowired
    ObjectMapper  objectMapper;

    public <T>  T get(String key, Class<T> classType) {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        String s = stringStringValueOperations.get(key);
        if ( s == null) {
            return null;
        }
        try {
            return objectMapper.readValue(s, classType);
        } catch (Exception e) {
            log.error("读取redis 错误 key = {}, ex = {}", key, e);
            return null;
        }
    }

    public <T>  T get(String key, TypeReference<T> typeReference) {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        String s = stringStringValueOperations.get(key);
        if ( s == null) {
            return null;
        }
        try {
            return objectMapper.readValue(s, typeReference);
        } catch (Exception e) {
        }
        return null;
    }

    public <T>  void set(String key, T value) {
        String str = null;
        try {
            str = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("序列华失败");
        }
        stringRedisTemplate.opsForValue().set(key, str);
    }


    /**
     * 指定超时时间
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public <T> boolean set(final String key, T value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(key, objectMapper.writeValueAsString(value));
            stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            log.error("保存出错: key {}, value {},expireTime {}",key,value,expireTime,e);
        }
        return result;
    }


    /**
     * 是否存在key
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * remove single key
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            stringRedisTemplate.delete(key);
        }
    }

}
