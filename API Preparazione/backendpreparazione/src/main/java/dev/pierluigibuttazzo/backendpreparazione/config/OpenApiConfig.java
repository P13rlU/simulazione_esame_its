package dev.pierluigibuttazzo.backendpreparazione.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Gestione Corsi e Iscrizioni")
                        .version("0.9.0.0.7.8.6.0.1")
                        .description("API per la gestione dei corsi formativi e delle iscrizioni dei partecipanti")
                        .contact(new Contact()
                                .name("Pierluigi Buttazzo")
                                .email("pbuttazzostudenti@outlook.it"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Server di sviluppo locale")));
    }
}
