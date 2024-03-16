package io.edwinjmunoz.crud.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${io.edwinjmunoz.crud.dev-url}")
    private String devUrl;

    @Value("${io.edwinjmunoz.crud.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI crudOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("edwinjmunoz@gmail.com");
        contact.setName("Edwin J. Munoz");
        contact.setUrl("https://www.edwinjmunoz.io");

        License myLicense = new License().name("API - License").url("https://en.wikipedia.org/wiki/Free-software_license");

        Info info = new Info()
                .title("CRUD Service API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to CRUD operations.")
                .termsOfService("https://termify.io/")
                .license(myLicense);

        //return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
        return new OpenAPI().info(info);
    }

}
