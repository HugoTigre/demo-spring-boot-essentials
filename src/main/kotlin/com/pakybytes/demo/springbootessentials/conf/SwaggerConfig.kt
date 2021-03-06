package com.pakybytes.demo.springbootessentials.conf

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket


@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun apiDocket(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.pakybytes"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData())
    }

    private fun metaData(): ApiInfo {
        return ApiInfoBuilder()
                .title("Spring Boot Essentials REST API")
                .description("Spring Boot Essentials REST API Documentation")
                .version("1.0.0")
                .contact(Contact(null, null, "hugo.tigre@gmail.com"))
                .license("License: MIT")
                .licenseUrl("https://github.com/HugoTigre/demo-spring-boot-essentials/blob/master/LICENSE")
                .build()
    }
}