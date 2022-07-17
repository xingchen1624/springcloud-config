package com.atguigu.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

/**
 * @auther zzyy
 * @create 2020-02-25 15:32
 * 自定义的限流处理类
 */
public class CustomerBlockHandler
{
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 自定义兜底方法1
     * @Date 21:41 2021/5/10
     * @Param [exception]
     * @return com.atguigu.springcloud.entities.CommonResult
     **/
    public static CommonResult handlerException(BlockException exception)
    {
        return new CommonResult(4444,"按客戶自定义,global handlerException----1");
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 自定义兜底方法2
     * @Date 21:41 2021/5/10
     * @Param [exception]
     * @return com.atguigu.springcloud.entities.CommonResult
     **/
    public static CommonResult handlerException2(BlockException exception)
    {
        return new CommonResult(4444,"按客戶自定义,global handlerException----2");
    }
}
