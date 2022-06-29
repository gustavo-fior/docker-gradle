package co.sban.missaojavacomgradle.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {

        return new OpenAPI()
                .info(new Info().title("Crud Usuário API")
                        .description("Apresentação dos endpoints da API do projeto de CRUD de Usuário").version("v0.0.1")
                        .license(new License().name("SBanco").url("https://sbcredito.com.br")));
    }


}
