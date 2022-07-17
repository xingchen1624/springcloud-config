package com.parse.geoJson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class FilePointToSQLUtil {


    /**
     * geojson str转JSONObject
     *
     * @param filePath
     */
    public static JSONObject fileToSql(String filePath) {
        JSONObject jsonObject= null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
            StringBuffer line = new StringBuffer();
            String temp = "";
            while (null != (temp = br.readLine())) {
                line.append(temp);
            }
            jsonObject = JSONObject.parseObject(line.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static void main(String[] args) {

        StringBuffer sb = new StringBuffer();
        sb.append("123456");
        String substring = sb.substring(0, sb.length() - 1);
        System.out.println(substring);
//        JSONObject jsonObject = FilePointToSQLUtil.fileToSql("D:\\Desktop\\HangZHou_BD.json");
//        JSONArray features = jsonObject.getJSONArray("features");
//        for (int i = 0; i< features.size(); i++) {
//            JSONObject feature = features.getJSONObject(i);
//            JSONObject properties = feature.getJSONObject("properties");
//
//
//
//            JSONObject geometry = feature.getJSONObject("geometry");
//            JSONArray coordinates = geometry.getJSONArray("coordinates");
//            for (int j =0; j < coordinates.size();j++) {
//                JSONArray jsonArray = coordinates.getJSONArray(j);
//                for (int k =0; k < jsonArray.size();k++) {
//                    JSONArray jsonArray1 = jsonArray.getJSONArray(k);
//                    for (int l =0; l < jsonArray1.size();l++) {
//                        JSONArray jsonArray2 = jsonArray1.getJSONArray(l);
//                        System.out.print(jsonArray2.get(0));
//                        System.out.print(" ");
//                        System.out.println(jsonArray2.get(1));
//                    }
//                }
//            }
//
//
//        }
    }


}
