package com.lzc.reptile.reptile_jd_data.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IContentService {
    /**
     * 解析数据放到es中
     *
     * @Author lzc
     * @Description //TODO 解析数据放到es中
     * @Date 21:40 2020/8/4
     * @Param [keywords]
     * @return java.lang.Boolean
     **/
    public Boolean parseContent(String keywords) throws Exception;

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 获取这些数据实现搜索功能
     * @Date 22:11 2020/8/4
     * @Param [keyword, pageNo, pageSize]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    public List<Map<String,Object>> searchPage(String keyword, int pageNo, int pageSize) throws IOException;

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 获取这些数据实现搜索功能 - 高亮显示
     * @Date 22:11 2020/8/4
     * @Param [keyword, pageNo, pageSize]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    public List<Map<String,Object>> searchPageHighLighter(String keyword, int pageNo, int pageSize) throws IOException;
}
