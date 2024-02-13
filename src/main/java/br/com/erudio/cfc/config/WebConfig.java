package br.com.erudio.cfc.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.erudio.cfc.converter.YamlJacksonConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${cors.origenPatterns:default}")
	private String origenPatterns = "";

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	converters.add(new YamlJacksonConverter());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		var allowedOrigens = origenPatterns.split(",");
		
		registry.addMapping("/**")
			.allowedMethods("*")
			.allowedOrigins(allowedOrigens)
			.allowCredentials(true);
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorParameter(false)
				.ignoreAcceptHeader(false)
				.useRegisteredExtensionsOnly(false)
				.defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("json", MediaType.APPLICATION_JSON)
				.mediaType("xml", MediaType.APPLICATION_XML)
				.mediaType("yaml", MediaType.parseMediaType("application/x-yaml"));
	}

}
