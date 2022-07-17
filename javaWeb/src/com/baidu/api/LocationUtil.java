/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: LocationUtil
 * Author:   xingc
 * Date:     2020/10/11 15:44
 * Description: 调用百度web服务api测试例子
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.baidu.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈调用百度web服务api测试例子〉
 *
 * @Author xingc
 * @Date 2020/10/11
 * @since 1.0.0
 **/
public class LocationUtil {
    //开发者key
    private static final String BAIDU_APP_KEY = "Ht3VBDG0aUNRPkHs8ztO9xwu94W58qNF";

    /**
     * 返回输入地址的经纬度坐标 key lng(经度),lat(纬度)
     */
    public static Map<String, String> getLatitude(String address){
        BufferedReader in = null;
        try {
            // 将地址转换成utf-8的16进制
            address = URLEncoder.encode(address, "UTF-8");
            // 如果有代理，要设置代理，没代理可注释
            // System.setProperty("http.proxyHost","192.168.172.23");
            // System.setProperty("http.proxyPort","3209");

            URL resjson = new URL("http://api.map.baidu.com/geocoder?address="
                    + address + "&output=json&key=" + BAIDU_APP_KEY);
            in = new BufferedReader(new InputStreamReader(resjson.openStream()));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }

            String str = sb.toString();
            System.out.println("return json:" + str);
            if (str != null && !str.equals("")) {
                Map<String, String> map = null;
                int lngStart = str.indexOf("lng\":");
                int lngEnd = str.indexOf(",\"lat");
                int latEnd = str.indexOf("},\"precise");
                if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
                    String lng = str.substring(lngStart + 5, lngEnd);
                    String lat = str.substring(lngEnd + 7, latEnd);
                    map = new HashMap<String, String>();
                    map.put("lng", lng);
                    map.put("lat", lat);
                    return map;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /*
     * unicode编码转中文
     */
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    public static void main(String args[]) {
        Map<String, String> map = LocationUtil.getLatitude("山东省莱芜市里辛镇黄金兰村");
        if (null != map) {
            System.out.println(map.get("lng"));
            System.out.println(map.get("lat"));
        }
    }
}