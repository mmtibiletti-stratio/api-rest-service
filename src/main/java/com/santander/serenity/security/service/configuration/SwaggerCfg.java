package com.santander.serenity.security.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.ant;

/**
 * Configuration beans for swagger api docs.
 */
@Configuration
@EnableSwagger2
public class SwaggerCfg {
    @Bean
    public Docket ServiceApi() {
        // @formatter:off
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .paths(
                        or(
                                // Add service endpoints
                                // ant("/nonce/**")
                        )
                )
                .build();
        // @formatter:on
    }

    private ApiInfo apiInfo() {
        // @formatter:off
        return new ApiInfoBuilder()
                // Add service info
                .title("ServiceName API")
                .description("Public API for ServiceName")
                .contact(new Contact(
                        "Serenity Security Services team",
                        "https://www.yammer.com/santander.com/#/threads/inGroup?type=in_group&feedId=7443776",
                        ""
                ))
                .license("Banco Santander S.A.")
                .build();
        // @formatter:on
    }
}
