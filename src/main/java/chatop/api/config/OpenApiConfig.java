package chatop.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Chatop",
                        url = "https://github.com/BihelCharly/charlybihel_3_24042024"
                ),
                description = "Documentation OpenAPI pour l'API Chatop",
                title = "OpenApi - Chatop",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:3001/api/"
                )
        }
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {
}
