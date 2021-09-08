package com.HIT.reactintegration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(
		title = "Records API",
		description = "This is an API for chore list recording.",
		version = "0.1",
		contact = @Contact(name = "Me", email = "mimail@email.com", url = "www.misitio.com")),
		security = @SecurityRequirement(name = "None", scopes = "All")
)
public class ReactintegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactintegrationApplication.class, args);
	}

}
