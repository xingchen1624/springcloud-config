package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @auther zzyy
 * @create 2020-02-24 16:26
 */
@RestController
@Slf4j
public class FlowLimitController
{
    @GetMapping("/testA")
    public String testA()
    {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB()
    {
        log.info(Thread.currentThread().getName()+"\t"+"...testB");
        return "------testB";
    }

    /**
     * 测试sentinel的降级策略 
     **/
    @GetMapping("/testD")
    public String testD()
    {
        //测试RT导致降级
//        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
//        log.info("testD 测试RT");

        //异常比例降级
        log.info("testD 异常比例");
        int age = 10/0;
        return "------testD";
    }

    /**
     * 异常数导致降级
     **/
    @GetMapping("/testE")
    public String testE()
    {
        log.info("testE 测试异常数");
        int age = 10/0;
        return "------testE 测试异常数";
    }

    /**
     * 热点限流规则
     **/
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")  //这里的value对应 Sentinel dashboard中的热点规则的 ‘资源名’
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2)
    {
        //int age = 10/0;
        return "------testHotKey";
    }
    
    /**
     * 违背了sentinel的热点限流规则后的兜底方法，在上面的方法中通过 blockHandler配置
     **/
    public String deal_testHotKey (String p1, String p2, BlockException exception)
    {
        return "------deal_testHotKey,o(╥﹏╥)o";  //sentinel系统默认的提示：Blocked by Sentinel (flow limiting)
    }

}
