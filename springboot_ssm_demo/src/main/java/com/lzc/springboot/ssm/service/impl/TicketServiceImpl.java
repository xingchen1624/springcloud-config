/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: TicketServiceImpl
 * Author:   xingc
 * Date:     2021/2/3 21:56
 * Description: 服务提供者的实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.service.impl;

import com.lzc.springboot.ssm.service.TicketService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉
 * 〈服务提供者的实现类〉
 * @author xingc
 * @date 2021/2/3
 * @since 1.0.0
 **/
@Service //注意这里的service是dubbo下的，而不是spring下的。该注解让该服务在项目启动就注册到注册中心
@Component //使用了dubbo后，尽量不用service注解，防止dubbo和spring的service注解冲突，据说dubbo新版本已经解决该问题
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "入场券";
    }
}