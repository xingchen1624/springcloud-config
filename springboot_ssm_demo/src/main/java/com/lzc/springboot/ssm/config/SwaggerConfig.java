/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: SwaggerConfig
 * Author:   xingc
 * Date:     2021/1/25 21:57
 * Description: Swagger的配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 〈一句话功能简述〉
 * 〈Swagger的配置类〉
 * @Author xingc
 * @Date 2021/1/25
 * @since 1.0.0
 **/
@Configuration
@EnableSwagger2  //开启swagger2
public class SwaggerConfig {

    /**
     * 配置多个分组，返回多个 Docket的bean实体类即可，
     * groupName指定分组名称
     **/
    @Bean
    public Docket docket1(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("group1")
                .select()
                .build();
    }
    
    /*
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 配置swagger的Docket实例
     * @Date 21:57 2021/1/27
     * @Param [environment] 环境参数，可以获取激活的配置文件
     * @return springfox.documentation.spring.web.plugins.Docket
     **/
    @Bean
    public Docket docket(Environment environment) {
        //设置要显示Swagger的环境，这里设置swagger只在开发dev环境有效
        Profiles profiles = Profiles.of("dev");
        //判断当前是否在设定的环境中
        boolean envFlag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .groupName("lzc_swagger")
            //是否启用swagger false：不启用,配合Environment设置swagger在特定环境启用
            .enable(envFlag)
            .select()
            /**
             * RequestHandlerSelectors 配置扫描的方式 
             * basePackage：扫描指定包
             * any:扫描全部
             * none:全部不扫描
             * withClassAnnotation:扫描类上的注解
             * withMethodAnnotation：扫描方法上的注解,可以自定义一个注解，放在方法上
             **/
            .apis(RequestHandlerSelectors.basePackage("com.lzc.springboot.ssm.controller"))//扫描该包下面的API注解
            //.apis(RequestHandlerSelectors.withClassAnnotation(Controller.class))
            //.apis(RequestHandlerSelectors.withMethodAnnotation(xxx.class))
            /**
             * paths过滤什么路径 
             **/
            .paths(PathSelectors.any())
            .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("使用Swagger2 构建RESTful APIS") //接口管理文档首页显示
            .description("Swagger使用演示")//API的描述
            .termsOfServiceUrl("http://localhost:8080/swagger-ui.html")//网站url等
            .version("1.0") //版本
            .contact(new Contact("chen","http://localhost:8080/swagger-ui.html" , "chen@163.com"))
            .build();
            
    }
    
}