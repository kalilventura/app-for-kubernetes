package br.com.github.kalilventura.pizzaria;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

//@Configuration
//@EnableOpenApi
public class OpenApiConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("pizzaria")
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Pizza API")
                .description("Simple CRUD to run in Kubernetes")
                .termsOfServiceUrl("http://springfox.io")
                .contact(new Contact("Kalil Teixeira Ventura Monteiro", "", ""))
                .license("Apache License Version 2.0")
                .version("2.0")
                .build();
    }
}
