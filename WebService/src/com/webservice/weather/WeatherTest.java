/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: WeatherTest
 * Author:   xingc
 * Date:     2020/9/14 21:46
 * Description: 测试webservice接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.webservice.weather;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈测试webservice接口〉
 * @Author xingc
 * @Date 2020/9/14
 * @since 1.0.0
 **/
public class WeatherTest {
    public static void main(String[] args) {
        WeatherWS weatherWS = new WeatherWS();
        WeatherWSSoap weatherWSSoap = weatherWS.getPort(WeatherWSSoap.class);
        ArrayOfString arrayOfString = weatherWSSoap.getWeather("杭州", "");
        System.out.println(weatherWSSoap.getRegionProvince());
        List<String> list = arrayOfString.getString();
        for(String str : list){
            System.out.println(str);
        }
    }
}