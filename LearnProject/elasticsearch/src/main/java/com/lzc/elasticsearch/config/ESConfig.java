/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: ESConfig
 * Author:   xingc
 * Date:     2020/8/2 13:26
 * Description: elasticsearch的属性配置文件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉
 * 〈elasticsearch的属性配置文件〉- springboot的配置类方式
 * @Author xingc
 * @Date 2020/8/2
 * @since 1.0.0
 **/
@Configuration
public class ESConfig {
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO
     * @Date 13:29 2020/8/2
     * @Param []
     * @return org.elasticsearch.client.RestHighLevelClient
     **/
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http")));
        return restHighLevelClient;
    }
}