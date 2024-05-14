package br.com.projetointegradorgr3.estoqueglp.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "basicAuth",
        scheme = "basic")
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .summary("Plataforma para controle de estoque para revendedores de gás")
                        .description("Plataforma para controle de estoque para revendedores de gás")
                        .contact(new Contact().url("https://github.com/projeto-integrador-gr3/estoqueGLP"))
                        .title("Estoque GLP"));
    }
}
