package com.github.cadedi.admin.config;

import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Swagger配置类
 * http://localhost:8080/swagger-ui/index.html#/
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket getDocket(){
        // 添加请求参数，把token作为请求头部参数传入后端(配置spring security后必需)
        RequestParameterBuilder parameterBuilder = new RequestParameterBuilder();
        List<RequestParameter> parameters = new ArrayList<RequestParameter>();
        //spring security
        // parameterBuilder.name("token").description("令牌")
        //         .modelRef(new ModelRef("string")).parameterType(ParameterType.HEADER).required(false).build();
        // parameters.add(parameterBuilder.build());
        parameterBuilder.name("token").description("令牌").in(ParameterType.HEADER).required(false).build();
        parameters.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                // .apis(RequestHandlerSelectors.basePackage("com.github.cadedi.controller"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(parameters);
    }
    public ApiInfo getApiInfo(){
        Contact contact = new Contact("Fox", "https://hugesoft.com", "fox@inlook.com");
        return new ApiInfo(
                "API文档",
                "Mango管理平台API文档",
                "v1.0",
                "urn:tod",
                contact,
                "Apache 2.0.0.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}
