/**
 * Copyright (C), 2012-2022, by xavier chen
 * FileName: TimeHandler
 * Author:   xingc
 * Date:     2022/7/17 12:04
 * Description: 处理时间自动填充的处理器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.hmgr.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉
 * 〈处理时间自动填充的处理器〉
 * @author xingc
 * @date 2022/7/17
 * @since 1.0.0
 **/
@Component
public class TimeHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "isDelete", String.class, "0");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}