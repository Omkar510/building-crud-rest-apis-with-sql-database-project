package net.javaguides.buildingcrudrestapiswithsqldatabaseproject;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot REST API Documentation",
				description = "Spring Boot REST API Documentation",
				version = "v1.0",
				contact = @Contact(
					name = "Omkar",
					email = "omkarsuvare@example.com",
					url = "http://localhost/8080/contact"
				),
				license = @License(
					name = "Apache 2.0",
					url = "http://localhost/8080/license"
				)
		),
		externalDocs = @ExternalDocumentation(
			description = "Spring Boot User Management Documentation",
			url = "http://localhost/8080/external_desc"
		)
)
@SpringBootApplication
public class BuildingCrudRestApisWithSqlDatabaseProjectApplication {

    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(BuildingCrudRestApisWithSqlDatabaseProjectApplication.class, args);
	}

}
