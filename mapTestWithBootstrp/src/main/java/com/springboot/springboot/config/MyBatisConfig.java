package com.springboot.springboot.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * 功能描述: <br>
 * @Author lzc
 * @Description //TODO 自定义mybatis的一些配置，使用springboot没有mybatis配置
 * 文件后，使用如下这种方式来修改springboot的默认mybatis配置
 * @Date 22:39 2019/12/11
 * @Param
 * @return
 **/
//类名冲突所以全类名
@org.springframework.context.annotation.Configuration
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer(){

        //开启驼峰命名，开启驼峰命名后，数据库字段名和对应bean的属性名
        //不一致，但是满足驼峰命名规则即可自动匹配
        // eg: 字段名 user_name，属性名userName
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
