package com.example.managingfoodreservation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.example.managingfoodreservation.utils.Constants.APP_ROOT;


@EnableSwagger2
@EnableWebMvc
@Configuration
public class Swagger2Config {
    @Bean
    public Docket api (){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .description("Managing food reservation documentation")
                                .title("Managing food reservation REST API")
                                .build()

                )
                .groupName("Rest API V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.managingfoodreservation"))
                .paths(PathSelectors.ant(APP_ROOT+"/**"))
                .build();
    }
}
