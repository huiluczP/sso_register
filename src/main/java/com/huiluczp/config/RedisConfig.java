package com.huiluczp.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        // jackson序列化设置
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        // template初始化
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // key序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // value序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // Hash key序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // Hash value序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
