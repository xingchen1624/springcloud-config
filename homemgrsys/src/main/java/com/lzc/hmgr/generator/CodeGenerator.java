
package com.lzc.hmgr.generator;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈mybatis-plus的代码生成器〉
 *
 * @author xingc
 * @date 2022/7/16
 * @since 1.0.0
 **/
public class CodeGenerator {
    public static void main(String[] args) {
        List<String> tables = new ArrayList<>();
        tables.add("home_user");

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/localtestdb?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC", "root", "root")
                .globalConfig(builder -> {
                    //作者
                    builder.author("lzc")
                            //输出路径(写到java目录)
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")
                            //开启swagger
                            .enableSwagger()          
                            .commentDate("yyyy-MM-dd")
                            //开启覆盖之前生成的文件
                            .fileOverride();            

                }).packageConfig(builder -> {
                    builder.parent("com.lzc")
                            .moduleName("hmgr")
                            .entity("bo")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "\\src\\main\\resources\\mapper"));
                }).strategyConfig(builder -> {
                    builder.addInclude(tables)
                            .addTablePrefix("t_")
                            .serviceBuilder()
                            .formatServiceFileName("I%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableLombok()
                            //.logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle()
                            .mapperBuilder()
                            .enableBaseResultMap()  //生成通用的resultMap
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                    // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                }).templateEngine(new FreemarkerTemplateEngine()).execute();
    }
}