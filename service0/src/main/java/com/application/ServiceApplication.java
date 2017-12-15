package com.application;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by a on 2017/11/21.
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableSwagger2
@ServletComponentScan({"com.common.filter"})
@ComponentScan("com")
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class);
    }

    @Value("${server.port}")
    String port;

    @ApiOperation(value="提示端口信息" ,notes="用于接口信息获取的测试")
    @ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String")
    @RequestMapping(value = "/hi" , method = RequestMethod.GET)
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port;
    }

    @ApiOperation(value ="进行相关计算",notes = "加法计算")
    @ApiImplicitParams({
            @ApiImplicitParam(name="numA",value = "参数A" ,required = true , dataType = "Integer"),
            @ApiImplicitParam(name="numB",value = "参数B" ,required = true , dataType = "Integer")
    })
    @RequestMapping(value = "/addNum" , method = RequestMethod.GET)
    public String addNum(@RequestParam("numA") int numA , @RequestParam("numB") int numB){
        int c = numA+numB;
        return String.valueOf(c);
    }

    @Bean
    public AlwaysSampler defaultSampler(){
        return new AlwaysSampler();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
                .termsOfServiceUrl("http://blog.didispace.com/")
                .contact("法克大魔王")
                .version("1.0")
                .build();
    }

}
