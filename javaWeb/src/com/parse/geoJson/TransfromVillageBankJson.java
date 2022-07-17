package com.parse.geoJson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TransfromVillageBankJson {

    //市-区边界multipolygon
    @Test
    public void fileToSql() {
        JSONObject jsonObjectArea = FilePointToSQLUtil.
                fileToSql("E:\\资料\\陈洪磊交接资料\\解析GeoJson示例代码\\浙江省_area.json");
        JSONArray featuresArea = jsonObjectArea.getJSONArray("features");
        for (int f = 0; f < featuresArea.size(); f++) {
            System.out.println(featuresArea.getString(f));
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(
                        new File("D:\\geojson_sql\\" + featuresArea.getString(f) + "_multipolygon_simplify_clob_gaode.sql")));
                JSONObject jsonObject = FilePointToSQLUtil.
                        fileToSql("E:\\资料\\陈洪磊交接资料\\解析GeoJson示例代码\\" + featuresArea.getString(f) + "_simplify_gaode.json");
                JSONArray features = jsonObject.getJSONArray("features");
                for (int i = 0; i < features.size(); i++) {
                    JSONObject feature = features.getJSONObject(i);
                    JSONObject properties = feature.getJSONObject("properties");
                    AdministrativeDistrictMultiPolygon range = new AdministrativeDistrictMultiPolygon();
                    range.setId(properties.getString("adcode"));
                    range.setRangeName(properties.getString("name"));
                    range.setUpCode(properties.getJSONObject("parent").getString("adcode"));
                    range.setType("polygon");
                    range.setTag("1");
                    range.setBorderColor("#0DB482");
                    range.setStatus("0");
                    range.setProvance(properties.getString("sjdm"));
                    range.setCity(properties.getString("dsdm"));
                    range.setDistrct(properties.getString("xjdm"));
                    range.setRangeLevel("3");

                    JSONObject geometry = feature.getJSONObject("geometry");
                    JSONArray coordinates = geometry.getJSONArray("coordinates");
                    StringBuffer temp = new StringBuffer();
                    if ("叶埠桥社区".equals(properties.getString("xzqmc"))) {
                        System.out.println("叶埠桥社区");
                    }

                    for (int j = 0; j < coordinates.size(); j++) {
                        JSONArray jsonArray = coordinates.getJSONArray(j);
                        StringBuffer polygon = new StringBuffer();
                        for (int k = 0; k < jsonArray.size(); k++) {
                            JSONArray jsonArray1 = jsonArray.getJSONArray(k);
                            for (int l = 0; l < jsonArray1.size(); l++) {
                                JSONArray jsonArray2 = jsonArray1.getJSONArray(l);
                                polygon.append(jsonArray2.get(0).toString())
                                        .append(" ")
                                        .append(jsonArray2.get(1).toString())
                                        .append(",");
                            }
                            String substring = polygon.toString().substring(0, polygon.length() - 1);
                            temp.append(substring).append("|");
                            polygon = new StringBuffer();
                        }
                    }
                    range.setLocation(temp.toString().substring(0, temp.length() - 1));
                    String[] str = range.getLocation().split("\\|");
                    StringBuffer sql = new StringBuffer();
                    sql.append("INSERT INTO tt_range_district (ID,RANGE_NAME,UP_CODE,TYPE,LOCATION_CLOB,TAG,PROVANCE,CITY,DISTRCT,STATUS,BORDER_COLOR,RANGE_LEVEL)VALUES (")
                            .append("'").append(range.getId()).append("'").append(",")
                            .append("'").append(range.getRangeName()).append("'").append(",")
                            .append("'").append(range.getUpCode()).append("'").append(",")
                            .append("'").append(range.getType()).append("'").append(",")
                            .append("'(").append(str[0].split(",")[0]).append("',")
                            .append("'").append(range.getTag()).append("'").append(",")
                            .append("'").append(range.getProvance()).append("'").append(",")
                            .append("'").append(range.getCity()).append("'").append(",")
                            .append("'").append(range.getDistrct()).append("'").append(",")
                            .append("'").append(range.getStatus()).append("'").append(",")
                            .append("'").append(range.getBorderColor()).append("'").append(",")
                            .append("'").append(range.getRangeLevel()).append("'").append(")").append(";\r\n");
                    sql.append("update tt_range_district set location_clob=location_clob||'");
                    for (int k = 0; k < str.length; k++) {
                        if (str.length > 1) {
                            if (k < (str.length - 1)) {
                                if (k == 0) {
                                    String[] splitPoint = str[0].split(",");
                                    for (int l = 0; l < splitPoint.length; l++) {
                                        if (l != 0) {
                                            sql.append(",'||'").append(splitPoint[l]);
                                        }
                                    }
                                    sql.append("),(");
                                } else {
                                    sql.append(str[k]).append("),('||'");
                                }
                            } else {
                                sql.append(str[k]).append(")");
                            }
                        } else {
                            if (k == 0) {
                                String[] splitPoint = str[0].split(",");
                                for (int l = 0; l < splitPoint.length; l++) {
                                    if (l != 0) {
                                        sql.append(",'||'").append(splitPoint[l]);
                                    }
                                }
                                sql.append(")");
                            }
                        }
                    }
                    sql.append("' where id='").append(range.getId()).append("';").append("\r\n");
                    sql.append("update tt_range_district set location=db2gse.ST_MultiPolygon ('MULTIPOLYGON (('||location_clob||'))',1) where id='" + range.getId() + "';\r\n");
                    bw.write(sql.toString());
                }
                //}
                //}
                //}
                // }
                //}
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //街道边界multipolygon clob
    @Test
    public void fileToSqlVillageClob() {
        JSONObject jsonObjectArea = FilePointToSQLUtil.
                fileToSql("E:\\2020工作记录\\杭州联合银行poc\\入厂文件\\九州帆软报表\\simplify\\nbcbData-gaode\\浙江省_district.json");
        JSONArray featuresDistrict = jsonObjectArea.getJSONArray("features");
        for (int f = 0; f < featuresDistrict.size(); f++) {
            System.out.println(featuresDistrict.getString(f));
            if (featuresDistrict.getString(f).equals("温州市")) {
                System.out.println("仰义街道");
            }
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(
                        new File("E:\\2020工作记录\\杭州联合银行poc\\入厂文件\\九州帆软报表\\simplify\\nbcbData-gaode\\" + featuresDistrict.getString(f) + "_multipolygon_simplify_clob_gaode.sql")));
                JSONObject jsonObject = FilePointToSQLUtil.
                        fileToSql("E:\\2020工作记录\\杭州联合银行poc\\入厂文件\\九州帆软报表\\simplify\\nbcbData-gaode\\" + featuresDistrict.getString(f) + "_simplify_gaode.geojson");
                JSONArray features = jsonObject.getJSONArray("features");
                for (int i = 0; i < features.size(); i++) {
                    JSONObject feature = features.getJSONObject(i);
                    JSONObject properties = feature.getJSONObject("properties");
                    AdministrativeDistrictMultiPolygon range = new AdministrativeDistrictMultiPolygon();
                    range.setId(properties.getString("NAME").trim().split("_")[0]);
                    range.setRangeName(properties.getString("NAME").trim().split("_")[1]);
                    if (properties.getString("NAME").trim().split("_")[1].equals("仰义街道")) {
                        System.out.println("仰义街道");
                    }
                    range.setUpCode(properties.getString("NAME").substring(0, 6));
                    range.setType("polygon");
                    range.setTag("1");
                    range.setBorderColor("#0DB482");
                    range.setStatus("0");
                    range.setProvance(properties.getString("sjdm"));
                    range.setCity(properties.getString("dsdm"));
                    range.setDistrct(properties.getString("xjdm"));
                    range.setRangeLevel("4");

                    JSONObject geometry = feature.getJSONObject("geometry");
                    JSONArray coordinates = geometry.getJSONArray("coordinates");
                    StringBuffer temp = new StringBuffer();
                    if ("叶埠桥社区".equals(properties.getString("xzqmc"))) {
                        System.out.println("叶埠桥社区");
                    }

                    for (int j = 0; j < coordinates.size(); j++) {
                        JSONArray jsonArray = coordinates.getJSONArray(j);
                        StringBuffer polygon = new StringBuffer();
                        for (int k = 0; k < jsonArray.size(); k++) {
                            JSONArray jsonArray1 = jsonArray.getJSONArray(k);
                            for (int l = 0; l < jsonArray1.size(); l++) {
                                JSONArray jsonArray2 = jsonArray1.getJSONArray(l);
                                polygon.append(jsonArray2.get(0).toString())
                                        .append(" ")
                                        .append(jsonArray2.get(1).toString())
                                        .append(",");
                            }
                            String substring = polygon.toString().substring(0, polygon.length() - 1);
                            temp.append(substring).append("|");
                            polygon = new StringBuffer();
                        }
                    }
                    range.setLocation(temp.toString().substring(0, temp.length() - 1));
                    String[] str = range.getLocation().split("\\|");
                    StringBuffer sql = new StringBuffer();
                    sql.append("INSERT INTO tt_range_district (ID,RANGE_NAME,UP_CODE,TYPE,LOCATION_CLOB,TAG,PROVANCE,CITY,DISTRCT,STATUS,BORDER_COLOR,RANGE_LEVEL)VALUES (")
                            .append("'").append(range.getId()).append("'").append(",")
                            .append("'").append(range.getRangeName()).append("'").append(",")
                            .append("'").append(range.getUpCode()).append("'").append(",")
                            .append("'").append(range.getType()).append("'").append(",")
                            .append("'(").append(str[0].split(",")[0]).append("',")
                            .append("'").append(range.getTag()).append("'").append(",")
                            .append("'").append(range.getProvance()).append("'").append(",")
                            .append("'").append(range.getCity()).append("'").append(",")
                            .append("'").append(range.getDistrct()).append("'").append(",")
                            .append("'").append(range.getStatus()).append("'").append(",")
                            .append("'").append(range.getBorderColor()).append("'").append(",")
                            .append("'").append(range.getRangeLevel()).append("'").append(")").append(";").append("\r\n");
                    sql.append("update tt_range_district set location_clob=location_clob||'");
                    for (int k = 0; k < str.length; k++) {
                        if (str.length > 1) {
                            if (k < (str.length - 1)) {
                                if (k == 0) {
                                    String[] splitPoint = str[0].split(",");
                                    for (int l = 0; l < splitPoint.length; l++) {
                                        if (l != 0) {
                                            sql.append(",'||'").append(splitPoint[l]);
                                        }
                                    }
                                    sql.append("),('||'");
                                } else {
                                    String[] splitPoint = str[k].split(",");
                                    for (int l = 0; l < splitPoint.length; l++) {
                                        if (l != 0) {
                                            sql.append(",'||'").append(splitPoint[l]);
                                        } else {
                                            sql.append("'||'").append(splitPoint[l]);
                                        }
                                    }
                                    sql.append("),('||'");
                                    //sql.append(str[k]).append("),('||'");
                                }
                            } else {
                                String[] splitPoint = str[k].split(",");
                                for (int l = 0; l < splitPoint.length; l++) {
                                    if (l != 0) {
                                        sql.append(",'||'").append(splitPoint[l]);
                                    } else {
                                        sql.append("'||'").append(splitPoint[l]);
                                    }
                                }
                                sql.append(")");
                                //sql.append(str[k]).append(")");
                            }
                        } else {
                            if (k == 0) {
                                String[] splitPoint = str[0].split(",");
                                for (int l = 0; l < splitPoint.length; l++) {
                                    if (l != 0) {
                                        sql.append(",'||'").append(splitPoint[l]);
                                    }
                                }
                                sql.append(")");
                            }
                        }
                    }
                    sql.append("' where id='").append(range.getId()).append("';").append("\r\n");
                    sql.append("update tt_range_district set location=db2gse.ST_MultiPolygon ('MULTIPOLYGON (('||location_clob||'))',1) where id='" + range.getId() + "';\r\n");
                    bw.write(sql.toString());
                }
                //}
                //}
                //}
                // }
                //}
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //社区边界multipolygon
    @Test
    public void fileToSqlVillage() {
        JSONObject jsonObjectArea = FilePointToSQLUtil.
                fileToSql("E:\\2020工作记录\\杭州联合银行poc\\入厂文件\\九州帆软报表\\simplify\\nbcbData-gaode\\村社区网格原始\\geoJson_simplify_Village.json");
        JSONArray featuresDistrict = jsonObjectArea.getJSONArray("features");
        for (int f = 0; f < featuresDistrict.size(); f++) {
            System.out.println(featuresDistrict.getString(f));
            if (featuresDistrict.getString(f).equals("温州市")) {
                System.out.println("仰义街道");
            }
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(
                        new File("E:\\2020工作记录\\杭州联合银行poc\\入厂文件\\九州帆软报表\\simplify\\nbcbData-gaode\\村社区网格原始\\湖州_BD09_multipolygon_simplify_clob_gaode.sql")));
                JSONObject jsonObject = FilePointToSQLUtil.
                        fileToSql("E:\\2020工作记录\\杭州联合银行poc\\入厂文件\\九州帆软报表\\simplify\\nbcbData-gaode\\村社区网格原始\\湖州_BD09_simplify_gaode.json");
                JSONArray features = jsonObject.getJSONArray("features");
                for (int i = 0; i < features.size(); i++) {
                    JSONObject feature = features.getJSONObject(i);
                    JSONObject properties = feature.getJSONObject("properties");
                    AdministrativeDistrictMultiPolygon range = new AdministrativeDistrictMultiPolygon();
                    range.setId(properties.getString("xzqdm"));
                    range.setRangeName(properties.getString("xzqjc"));
                    if (properties.getString("xzqjc").equals("仰义街道")) {
                        System.out.println("仰义街道");
                    }
                    range.setUpCode(properties.getString("xzdm"));
                    range.setType("polygon");
                    range.setTag("1");
                    range.setBorderColor("#0DB482");
                    range.setStatus("0");
                    range.setProvance(properties.getString("sjdm"));
                    range.setCity(properties.getString("dsdm"));
                    range.setDistrct(properties.getString("xjdm"));
                    range.setRangeLevel("5");

                    JSONObject geometry = feature.getJSONObject("geometry");
                    JSONArray coordinates = geometry.getJSONArray("coordinates");
                    StringBuffer temp = new StringBuffer();
                    if ("叶埠桥社区".equals(properties.getString("xzqmc"))) {
                        System.out.println("叶埠桥社区");
                    }

                    for (int j = 0; j < coordinates.size(); j++) {
                        JSONArray jsonArray = coordinates.getJSONArray(j);
                        StringBuffer polygon = new StringBuffer();
                        for (int k = 0; k < jsonArray.size(); k++) {
                            JSONArray jsonArray1 = jsonArray.getJSONArray(k);
                            for (int l = 0; l < jsonArray1.size(); l++) {
                                JSONArray jsonArray2 = jsonArray1.getJSONArray(l);
                                polygon.append(jsonArray2.get(0).toString())
                                        .append(" ")
                                        .append(jsonArray2.get(1).toString())
                                        .append(",");
                            }
                            String substring = polygon.toString().substring(0, polygon.length() - 1);
                            temp.append(substring).append("|");
                            polygon = new StringBuffer();
                        }
                    }
                    range.setLocation(temp.toString().substring(0, temp.length() - 1));
                    String[] str = range.getLocation().split("\\|");
                    StringBuffer sql = new StringBuffer();
                    sql.append("INSERT INTO tt_range_district (ID,RANGE_NAME,UP_CODE,TYPE,LOCATION_CLOB,TAG,PROVANCE,CITY,DISTRCT,STATUS,BORDER_COLOR,RANGE_LEVEL)VALUES (")
                            .append("'").append(range.getId()).append("'").append(",")
                            .append("'").append(range.getRangeName()).append("'").append(",")
                            .append("'").append(range.getUpCode()).append("'").append(",")
                            .append("'").append(range.getType()).append("'").append(",")
                            .append("'(").append(str[0].split(",")[0]).append("',")
                            .append("'").append(range.getTag()).append("'").append(",")
                            .append("'").append(range.getProvance()).append("'").append(",")
                            .append("'").append(range.getCity()).append("'").append(",")
                            .append("'").append(range.getDistrct()).append("'").append(",")
                            .append("'").append(range.getStatus()).append("'").append(",")
                            .append("'").append(range.getBorderColor()).append("'").append(",")
                            .append("'").append(range.getRangeLevel()).append("'").append(")").append(";").append("\r\n");
                    sql.append("update tt_range_district set location_clob=location_clob||'");
                    for (int k = 0; k < str.length; k++) {
                        if (str.length > 1) {
                            if (k < (str.length - 1)) {
                                if (k == 0) {
                                    String[] splitPoint = str[0].split(",");
                                    for (int l = 0; l < splitPoint.length; l++) {
                                        if (l != 0) {
                                            sql.append(",'||'").append(splitPoint[l]);
                                        }
                                    }
                                    sql.append("),('||'");
                                } else {
                                    String[] splitPoint = str[k].split(",");
                                    for (int l = 0; l < splitPoint.length; l++) {
                                        if (l != 0) {
                                            sql.append(",'||'").append(splitPoint[l]);
                                        } else {
                                            sql.append("'||'").append(splitPoint[l]);
                                        }
                                    }
                                    sql.append("),('||'");
                                    //sql.append(str[k]).append("),('||'");
                                }
                            } else {
                                String[] splitPoint = str[k].split(",");
                                for (int l = 0; l < splitPoint.length; l++) {
                                    if (l != 0) {
                                        sql.append(",'||'").append(splitPoint[l]);
                                    } else {
                                        sql.append("'||'").append(splitPoint[l]);
                                    }
                                }
                                sql.append(")");
                                //sql.append(str[k]).append(")");
                            }
                        } else {
                            if (k == 0) {
                                String[] splitPoint = str[0].split(",");
                                for (int l = 0; l < splitPoint.length; l++) {
                                    if (l != 0) {
                                        sql.append(",'||'").append(splitPoint[l]);
                                    }
                                }
                                sql.append(")");
                            }
                        }
                    }
                    sql.append("' where id='").append(range.getId()).append("';").append("\r\n");
                    sql.append("update tt_range_district set location=db2gse.ST_MultiPolygon ('MULTIPOLYGON (('||location_clob||'))',1) where id='" + range.getId() + "';\r\n");
                    bw.write(sql.toString());
                }
                //}
                //}
                //}
                // }
                //}
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
