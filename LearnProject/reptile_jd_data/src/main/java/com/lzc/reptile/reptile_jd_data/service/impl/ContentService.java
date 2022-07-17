/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: ContentService
 * Author:   xingc
 * Date:     2020/8/3 22:25
 * Description: 业务类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.reptile.reptile_jd_data.service.impl;

import com.alibaba.fastjson.JSON;
import com.lzc.reptile.reptile_jd_data.pojo.Content;
import com.lzc.reptile.reptile_jd_data.service.IContentService;
import com.lzc.reptile.reptile_jd_data.utils.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉
 * 〈业务类〉
 * @Author xingc
 * @Date 2020/8/3
 * @since 1.0.0
 **/
@Service
public class ContentService implements IContentService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private HtmlParseUtil htmlParseUtil;

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 解析数据放到es中
     * @Date 21:30 2020/8/4
     * @Param
     * @return
     **/
    @Override
    public Boolean parseContent(String keywords) throws Exception {
        //获取解析到的数据
        List<Content> contents = HtmlParseUtil.parseJd(keywords);
        //把查询出的数据插入es
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        for (int i = 0; i < contents.size(); i++) {
            bulkRequest.add(new IndexRequest("jd_data").id(""+(i+1))
                    .source(JSON.toJSONString(contents.get(i)), XContentType.JSON));
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulk.hasFailures();
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 获取这些数据实现搜索功能
     * @Date 21:58 2020/8/4
     * @Param [keyword, pageNo, pageSize]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Override
    public List<Map<String,Object>> searchPage(String keyword,int pageNo,int pageSize) throws IOException {
        if(pageNo <= 1){
            pageNo = 1;
        }

        //条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_data");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);

        //精确匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //解析结果
        List<Map<String,Object>> list = new ArrayList<>();
        for (SearchHit searchHit: searchResponse.getHits().getHits()) {
            list.add(searchHit.getSourceAsMap());
        }

        return list;
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 获取这些数据实现搜索功能
     * @Date 21:58 2020/8/4
     * @Param [keyword, pageNo, pageSize]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Override
    public List<Map<String,Object>> searchPageHighLighter(String keyword,int pageNo,int pageSize) throws IOException {
        if(pageNo <= 1){
            pageNo = 1;
        }

        //条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_data");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);

        //精确匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //构建高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        //多个高亮显示
        highlightBuilder.requireFieldMatch(false);
        searchSourceBuilder.highlighter(highlightBuilder);

        //执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //解析结果
        List<Map<String,Object>> list = new ArrayList<>();
        for (SearchHit searchHit: searchResponse.getHits().getHits()) {
            //解析高亮的字段
            Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
            //打印高亮显示内容
            for (Map.Entry<String, HighlightField> entry : highlightFields.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }

            HighlightField title = highlightFields.get("title");
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            for (Map.Entry<String, Object> entry : sourceAsMap.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
            //如果高亮字段存在,将原来的字段替换为我们高亮的字段
            if(title != null){
                Text[] fragments = title.fragments();
                String new_title = "";
                for (Text text : fragments) {
                    System.out.println(text);
                    new_title += text;
                }
                sourceAsMap.put("title", new_title);  //高亮字段替换掉原来的字段

            }
            list.add(sourceAsMap);
        }

        return list;
    }

}