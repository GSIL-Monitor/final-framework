package com.ilikly.finalframework.redis.autoconfigure;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ilikly.finalframework.coding.plugins.spring.annotation.AutoConfiguration;
import com.ilikly.finalframework.json.jackson.JavaTimeModule;
import com.ilikly.finalframework.redis.RedisRegistry;
import com.ilikly.finalframework.redis.serializer.Object2StringRedisSerializer;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author likly
 * @version 1.0
 * @date 2018-11-22 14:10:58
 * @since 1.0
 */
@AutoConfiguration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisAutoConfiguration implements ApplicationContextAware {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private RedisProperties redisProperties;
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        redisTemplate.setKeySerializer(applicationContext.getBean(redisProperties.getKeySerializer()));
        redisTemplate.setValueSerializer(applicationContext.getBean(redisProperties.getValueSerializer()));
        redisTemplate.setHashKeySerializer(applicationContext.getBean(redisProperties.getHashKeySerializer()));
        redisTemplate.setHashValueSerializer(applicationContext.getBean(redisProperties.getHashValueSerializer()));
        RedisRegistry.getInstance().setRedisTemplate(redisTemplate);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public Object2StringRedisSerializer object2StringRedisSerializer() {
        return Object2StringRedisSerializer.UTF_8;
    }

    @Bean
    public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
//        objectMapper.registerModule(new SimpleModule());
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }
}
