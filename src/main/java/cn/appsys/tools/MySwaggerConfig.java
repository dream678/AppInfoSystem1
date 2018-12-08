package cn.appsys.tools;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created with IntelliJ IDEA.
 * User:admin
 * Date:2018/12/8
 * Time:14:29
 * Desc:
 */
@Configuration
@EnableSwagger2
public class MySwaggerConfig {
    @Bean
    public Docket userApi() {
return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()) .build();

 }
 @Bean
 public RequestMappingInfoHandlerMapping requestMapping(){
 return new RequestMappingHandlerMapping();
 }
}