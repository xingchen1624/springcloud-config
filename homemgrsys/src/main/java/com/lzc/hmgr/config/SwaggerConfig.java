package com.lzc.hmgr.config;

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
 * @author xingc
 * @date 2021/1/25
 * @since 1.0.0
 * --@EnableSwagger2 开启swagger2
 **/
@Configuration
@EnableSwagger2  
public class SwaggerConfig {
    /**
     * 配置多个分组，返回多个 Docket的bean实体类即可，
     * groupName指定分组名称
     **/
    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("测试接口")
                .select()
                .build();
    }
    
    /**
     * 功能描述: <br>
     * @author lzc
     * 配置swagger的Docket实例
     * @date 21:57 2021/1/27
     * @param environment 环境参数，可以获取激活的配置文件
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
            .groupName("家庭管理系统接口")
            //是否启用swagger false：不启用,配合Environment设置swagger在特定环境启用
            .enable(envFlag)
            .select()
            /*
             * RequestHandlerSelectors 配置扫描的方式 
             * basePackage：扫描指定包
             * any:扫描全部
             * none:全部不扫描
             * withClassAnnotation:扫描类上的注解
             * withMethodAnnotation：扫描方法上的注解,可以自定义一个注解，放在方法上
             */
            //扫描该包下面的API注解
            .apis(RequestHandlerSelectors.basePackage("com.lzc.hmgr.controller"))
            //.apis(RequestHandlerSelectors.withClassAnnotation(Controller.class))
            //.apis(RequestHandlerSelectors.withMethodAnnotation(xxx.class))
                // paths过滤什么路径 
            .paths(PathSelectors.any())
            .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //接口管理文档首页显示
            .title("家庭管理系统API接口文档")
                //API的描述
            .description("家庭管理系统API接口文档")
                //网站url等
            .termsOfServiceUrl("http://localhost/swagger-ui.html")
                //版本
            .version("1.0")
            .contact(new Contact("chen","http://localhost/swagger-ui.html" , "848719131@qq.com"))
            .build();
            
    }
    
}