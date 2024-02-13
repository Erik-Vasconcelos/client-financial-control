package br.com.erudio.cfc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI getOpenAPI() {
		//@formatter:off
		return new OpenAPI().info(
				new Info()
				.title("Rest API by Client Financial Control")
				.version("V1")
				.description("This is a API for managenment client financial")
				.termsOfService("https://www.erudio.com.br/blog")
				.license(
					new License()	
						.name("Apache 2")
						.url("https://www.erudio.com.br/blog")
					)
		);
		//@formatter:on
	}
}
