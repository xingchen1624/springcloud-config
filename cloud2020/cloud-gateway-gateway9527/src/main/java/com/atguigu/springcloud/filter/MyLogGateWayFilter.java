package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @auther zzyy
 * @create 2020-02-21 16:40
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter,Ordered
{

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 自定义过滤规则，注意ServerWebExchange是spring5以后的特性
     * @Date 17:50 2021/4/5
     * @Param [exchange, chain]
     * @return reactor.core.publisher.Mono<java.lang.Void>
     **/
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        log.info("***********come in MyLogGateWayFilter:  "+new Date());

        String uname = exchange.getRequest().getQueryParams().getFirst("uname");

        if(uname == null)
        {
            log.info("*******用户名为null，非法用户，o(╥﹏╥)o");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    /***
     * 加载过滤器的顺序，数字越小，优先级越高
     **/
    @Override
    public int getOrder()
    {
        return 0;
    }
}
