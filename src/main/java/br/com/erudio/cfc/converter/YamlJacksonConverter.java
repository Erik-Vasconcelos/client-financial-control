package br.com.erudio.cfc.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-03
 * 
 */
public class YamlJacksonConverter extends AbstractJackson2HttpMessageConverter {

	public YamlJacksonConverter() {
		super(new YAMLMapper().setSerializationInclusion(
				JsonInclude.Include.NON_NULL).findAndRegisterModules(), 
				MediaType.parseMediaType("application/x-yaml")
		);
	}

}
