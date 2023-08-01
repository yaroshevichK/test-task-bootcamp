package it.bootcamp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

import static it.bootcamp.Constants.NAME_API;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Katerina",
                        email = "kvyaroshevich@gmail.com"
                ),
                description = "Open API documentation",
                title = "Open API specification",
                version = "1.0",
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = NAME_API,
                        url = "http://localhost:8081"
                )
        }
)
public class OpenApiConfig {
}
