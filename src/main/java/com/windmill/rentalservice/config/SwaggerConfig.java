package com.windmill.rentalservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /**
     * Creates and configures the OpenAPI bean.
     *
     * @return the OpenAPI instance
     */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("rental-service")
                .pathsToMatch("/api/**")
                .addOpenApiCustomiser(openApi -> openApi
                        .info(new Info().title("Rental Service API")
                                .description("API documentation for the Rental Service application")
                                .version("v1.0.0")
                                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                        .components(new Components()
                                .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth")))
                .build();
    }

    /**
     * Creates the OpenAPI definition bean.
     *
     * @return the OpenAPI instance
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Rental Service API")
                        .version("v1.0.0")
                        .description("API documentation for the Rental Service application")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
