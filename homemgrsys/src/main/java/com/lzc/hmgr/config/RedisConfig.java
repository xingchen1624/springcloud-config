/**
 * Copyright (C), 2012-2023, by xavier chen
 * FileName: RedisConfig
 * Author:   xingc
 * Date:     2023/3/11 14:04
 * Description: redis
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.hmgr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.DigestUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 〈一句话功能简述〉
 * 〈redis配置类〉
 * @author xingc
 * @date 2023/3/11
 * @since 1.0.0
 **/

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 自定义生成redis-key
     *
     * @return KeyGenerator
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            // 缓存redis key 命名规则：  项目名<cacheName会自动拼接>:类名:MD5(方法名:参数)
            StringBuilder redisKey = new StringBuilder();
            // 拼接类名
            redisKey.append(o.getClass().getSimpleName());

            // 为避免redis key 太长，后续信息用MD5 加密
            StringBuilder keyStr = new StringBuilder();
            // 拼接方法名
            keyStr.append(method.getName());
            // 拼接参数
            for (Object obj : objects) {
                //拼接实体请求参数
                keyStr.append(":").append(obj.toString());
            }
            // MD5加密 (方法名:参数)
            String md5Hex = DigestUtils.md5DigestAsHex(keyStr.toString().getBytes());

            redisKey.append(":").append(md5Hex);
            return redisKey.toString();
        };
    }

    // 配置redisTemplate
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        // 设置key的序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // value的序列化
        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 配置缓存管理器
     * @param factory 线程连接工厂
     * @return 缓存管理器
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory factory){
        //设置缓存名称，多个用集合
        Set<String> cacheNames = new HashSet<>();
        cacheNames.add("user");
        cacheNames.add("role");

        LettuceConnectionFactory jedisConnectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        //指定dbindex
        jedisConnectionFactory.setDatabase(0);      
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        jedisConnectionFactory.resetConnection();

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                //默认没有特殊指定的缓存，设置缓存过期时间1分钟
                .entryTtl(Duration.ofMinutes(10L))
                //设置缓存前缀
                .prefixCacheNameWith("redis:cache:")
                // 覆盖默认的构造key，否则会多出一个冒号 原规则：cacheName::key  现规则：cacheName:key
                .computePrefixWith(name -> name + ":")
                //禁止缓存null值 -#是否缓存空值，一般要运行缓存空值，防止缓存穿透
                .disableCachingNullValues()
                //添加键值序列化配置
                .serializeKeysWith(keyPair())
                .serializeValuesWith(valuePair());

        // 针对不同cacheName，设置不同的失效时间，map的key是缓存名称（注解设定的value/cacheNames），value是缓存的失效配置
        Map<String, RedisCacheConfiguration> initialCacheConfiguration = new HashMap<String, RedisCacheConfiguration>(8);
        // 设定失效时间为1小时
        initialCacheConfiguration.put("user", getDefaultSimpleConfiguration().entryTtl(Duration.ofHours(1)));
        // 设定失效时间为10分钟
        initialCacheConfiguration.put("role", getDefaultSimpleConfiguration().entryTtl(Duration.ofMinutes(10)));

        return RedisCacheManager.builder(factory).initialCacheNames(cacheNames)
                .cacheDefaults(config)
                // 不同不同cacheName的个性化配置
                .withInitialCacheConfigurations(initialCacheConfiguration)
                .build();
    }

    /**
     * 覆盖默认的构造key[默认拼接的时候是两个冒号（::）]，否则会多出一个冒号
     *
     * @return 返回缓存配置信息
     */
    private RedisCacheConfiguration getDefaultSimpleConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig().computePrefixWith(cacheName -> cacheName + ":");
    }

    /**
     * 配置键序列化
     * @return
     */
    @Bean
    RedisSerializationContext.SerializationPair<String> keyPair(){
        return RedisSerializationContext.SerializationPair.fromSerializer( new StringRedisSerializer());
    }

    /**
     * 配置值序列化
     * @return
     */
    @Bean
    RedisSerializationContext.SerializationPair<Object> valuePair(){
        return RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());
    }
    
}