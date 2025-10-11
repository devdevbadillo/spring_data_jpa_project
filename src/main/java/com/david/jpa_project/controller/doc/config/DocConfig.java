package com.david.jpa_project.controller.doc.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(
        info =  @Info(
                title = "JPA Project",
                version = "1.0",
                description = "JPA Project with Spring Boot",
                contact = @Contact(
                        name = "David Rodriguez Badillo" ,
                        email = "david@example.com"
                )
        )
)
public class DocConfig {
}
