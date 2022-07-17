/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: JaxbParseXml
 * Author:   xingc
 * Date:     2020/9/16 20:13
 * Description: 使用java的jaxb解析xml
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.webservice.jaxb;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

/**
 * 〈一句话功能简述〉
 * 〈使用java的jaxb解析xml〉
 * @Author xingc
 * @Date 2020/9/16
 * @since 1.0.0
 **/
public class JaxbParseXml {

    @Test
    public void parseXmlTest(){
        String xml = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "   <soap:Body>\n" +
                "      <ns1:getHrmUserInfoWithPageResponse xmlns:ns1=\"http://localhost/services/HrmService\">\n" +
                "         <ns1:out>\n" +
                "{\n" +
                "    \"totalSize\":15735,\n" +
                "    \"code\":\"1\",\n" +
                "    \"dataList\":[\n" +
                "        {\n" +
                "            \"tempresidentnumber\":\"xxxx\",\n" +
                "            \"createdate\":\"2020-04-09\",\n" +
                "            \"language\":\"\",\n" +
                "            \"subcompanyid1\":\"21\",\n" +
                "            \"subcompanyname\":\"安徽\",\n" +
                "            \"joblevel\":\"0\",\n" +
                "            \"startdate\":\"2010-08-10\",\n" +
                "            \"password\":\"B59C67BF196A4758191E42F76670CEBA\",\n" +
                "            \"subcompanycode\":\"0021\",\n" +
                "            \"jobactivitydesc\":\"xxxx\",\n" +
                "            \"bememberdate\":\"2010-08-08\",\n" +
                "            \"modified\":\"2020-06-01 16:09:36\",\n" +
                "            \"id\":\"21\",\n" +
                "            \"mobilecall\":\"123456\",\n" +
                "            \"nativeplace\":\"湖北\",\n" +
                "            \"certificatenum\":\"1111112\",\n" +
                "            \"height\":\"0\",\n" +
                "            \"loginid\":\"w1\",\n" +
                "            \"created\":\"2020-04-09 17:01:51\",\n" +
                "            \"degree\":\"xxx\",\n" +
                "            \"bepartydate\":\"2019-05-18\",\n" +
                "            \"weight\":\"110\",\n" +
                "            \"telephone\":\"123\",\n" +
                "            \"residentplace\":\"xxxx\",\n" +
                "            \"lastname\":\"张三丰\",\n" +
                "            \"healthinfo\":\"1\",\n" +
                "            \"enddate\":\"2018-08-10\",\n" +
                "            \"maritalstatus\":\"已婚\",\n" +
                "            \"departmentname\":\"质量管理部\",\n" +
                "            \"folk\":\"汉\",\n" +
                "            \"status\":\"1\",\n" +
                "            \"birthday\":\"2010-05-13\",\n" +
                "            \"accounttype\":\"0\",\n" +
                "            \"jobcall\":\"21\",\n" +
                "            \"managerid\":\"0\",\n" +
                "            \"assistantid\":\"0\",\n" +
                "            \"departmentcode\":\"0023\",\n" +
                "            \"email\":\"123@163.COM\",\n" +
                "            \"seclevel\":\"50\",\n" +
                "            \"policy\":\"团员\",\n" +
                "            \"jobtitle\":\"1034\",\n" +
                "            \"workcode\":\"fw0001\",\n" +
                "            \"sex\":\"男\",\n" +
                "            \"departmentid\":\"23\",\n" +
                "            \"homeaddress\":\"xxxx\",\n" +
                "            \"mobile\":\"5654321\",\n" +
                "            \"lastmoddate\":\"2020-05-18\",\n" +
                "            \"educationlevel\":\"3\",\n" +
                "            \"islabouunion\":\"0\",\n" +
                "            \"locationid\":\"21\",\n" +
                "            \"regresidentplace\":\"武汉\",\n" +
                "            \"dsporder\":\"21\",\n" +
                "\"field0_scopeId-1\": {\n" +
                "\t\t\t\t\"Fieldname\": \"field0\",\n" +
                "\t\t\t\t\"name\": \"文本11\",\n" +
                "\t\t\t\t\"value\": \"文本1\"\n" +
                "\t\t\t }\n" +
                "\n" +
                "        }],\n" +
                "    \"pageSize\":1,\n" +
                "    \"page\":1\n" +
                "}\n" +
                "</ns1:out>\n" +
                "      </ns1:getHrmUserInfoWithPageResponse>\n" +
                "   </soap:Body>\n" +
                "</soap:Envelope>\n";
        int startIndex = xml.indexOf("<ns1:out>");
        int endIndex = xml.indexOf("</ns1:out>");
        String subStr = xml.substring(startIndex+10,endIndex);
        System.out.println(subStr);
        JSONObject jsonObject = new JSONObject();
        JSONObject personObject = JSONObject.fromObject(subStr);
        JSONArray dataList = personObject.getJSONArray("dataList");
        System.out.println(dataList.get(0));
        System.out.println(personObject.get("totalSize"));
    }
}