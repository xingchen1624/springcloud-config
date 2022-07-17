/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: RedisConfig
 * Author:   xingc
 * Date:     2021/1/31 21:54
 * Description: redis的配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/**
 * 〈一句话功能简述〉
 * 〈redis的配置类〉
 * @author xingc
 * @date 2021/1/31
 * @since 1.0.0
 **/
@Configuration
public class RedisConfig {
    /**
     * 编写自己的RedisTemplate，固定模板，项目中可以直接使用
     **/
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        //为了开发方便一般直接使用<String,Object>，自动配置中默认使用的是<Object,Object>
        RedisTemplate<String, Object> template = new RedisTemplate<String,Object>();
        template.setConnectionFactory(redisConnectionFactory);
        //json序列化配置
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //springboot2.2.x版本 下面的enableDefaultTyping方法过时了,改为使用activateDefaultTyping
        //om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance ,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        
        //String类型序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        //hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        //value的序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //hash的value也采用String的序列化方式
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        //将属性设置进template
        template.afterPropertiesSet();
        return template;
    }
}