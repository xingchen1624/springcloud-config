/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: MyCacheConfig
 * Author:   chen
 * Date:     2019/12/18 22:37
 * Description: 自定义缓存生成器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.cache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 〈一句话功能简述〉
 * 〈自定义缓存的key生成器〉
 * @Author chen
 * @Date 2019/12/18
 * @since 1.0.0
 **/
@Configuration
public class MyCacheConfig {

    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                return method.getName()+"["+ Arrays.asList(objects).toString()+"]";
            }
        };
    }
}