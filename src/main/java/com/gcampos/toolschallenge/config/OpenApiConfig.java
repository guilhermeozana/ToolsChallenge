package com.gcampos.toolschallenge.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("API de Pagamentos")
                        .version("v1")
                        .description("Serviço construído para resolver o ToolsChallenge")
                        .license( new License()
                                .name("ToolsChallenge")
                                .url("https://github.com/githubanotaai/new-test-backend-nodejs")));
    }
}
