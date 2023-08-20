package com.haservi.h2crud.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Simple User CRUD")
                .description("사용자 crud 생성 및 쿼리 확인용 swagger");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}