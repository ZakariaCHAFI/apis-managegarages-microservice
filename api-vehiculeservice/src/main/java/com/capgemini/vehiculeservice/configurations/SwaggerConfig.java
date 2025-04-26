package com.capgemini.vehiculeservice.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API manager vehicule")
                        .version("1.0")
                        .description("Documentation of the manage vehicule API using Swaager OpenAPI"));
    }
}
