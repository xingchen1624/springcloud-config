package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther zzyy
 * @create 2020-02-21 11:42
 */
@Configuration
public class GateWayConfig
{
    /***
     * 配置路由，可以在yml配置文件中配置，也可以在java代码中，通过如下方式编码配置
     * 其中 r -> r.path是用的java8新特性lamda表达式
     **/
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder)
    {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
    
        routes.route("path_route_atguigu",
                r -> r.path("/guonei")
                        .uri("http://news.baidu.com/guonei"));
    
        return routes.build();
    }
}
