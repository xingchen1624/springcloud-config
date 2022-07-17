/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: WeatherTest
 * Author:   xingc
 * Date:     2020/9/14 21:46
 * Description: ����webservice�ӿ�
 * History:
 * <author>          <time>          <version>          <desc>
 * ��������           �޸�ʱ��           �汾��              ����
 **/
package com.webservice.weather;

import java.util.List;

/**
 * ��һ�仰���ܼ�����
 * ������webservice�ӿڡ�
 * @Author xingc
 * @Date 2020/9/14
 * @since 1.0.0
 **/
public class WeatherTest {
    public static void main(String[] args) {
        WeatherWS weatherWS = new WeatherWS();
        WeatherWSSoap weatherWSSoap = weatherWS.getPort(WeatherWSSoap.class);
        ArrayOfString arrayOfString = weatherWSSoap.getWeather("����", "");
        System.out.println(weatherWSSoap.getRegionProvince());
        List<String> list = arrayOfString.getString();
        for(String str : list){
            System.out.println(str);
        }
    }
}