/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: HtmlParseUtil
 * Author:   xingc
 * Date:     2020/8/3 21:41
 * Description: html解析工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.reptile.reptile_jd_data.utils;

import com.lzc.reptile.reptile_jd_data.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈html解析工具类〉
 * @Author xingc
 * @Date 2020/8/3
 * @since 1.0.0
 **/
@Component
public class HtmlParseUtil {
    public static void main(String[] args) throws Exception {
        //调用解析方法
        parseJd("java").forEach(System.out::println);
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 解析html
     * @Date 22:10 2020/8/3
     * @Param [keywords]
     * @return java.util.List<java.lang.Object>
     **/
    public static List<Content> parseJd(String keywords) throws Exception{
        //获得请求地址  https://search.jd.com/Search?keyword=keywords
        //前提联网
        String url = "https://search.jd.com/Search?keyword="+keywords;
        //解析网页(parse返回一个Document对象，该对象就是浏览器或者说js中的document对象)
        Document document = Jsoup.parse(new URL(url), 30000);
        //所有js中可以使用的方法在这里都可以用
        Element j_goodsList = document.getElementById("J_goodsList");
        System.out.println(j_goodsList.html());
        //获取所有的li元素
        Elements lis = j_goodsList.getElementsByTag("li");
        //获取元素中的内容
        List<Content> contents = new ArrayList<Content>();
        for (Element element : lis) {
            //img有时获取不到，是因为京东网站采取了懒加载的策略，防止图片过多加载过慢
            String img = element.getElementsByTag("img").eq(0).attr("src");
            String price = element.getElementsByClass("p-price").eq(0).text();
            String title = element.getElementsByClass("p-name").eq(0).text();

            Content content = new Content(title, img, price);
            contents.add(content);

        }
        return contents;
    }


}