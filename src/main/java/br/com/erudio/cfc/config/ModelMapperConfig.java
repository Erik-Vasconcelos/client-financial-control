package br.com.erudio.cfc.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erudio.cfc.dto.v2.AddressDTO;
import br.com.erudio.cfc.model.Address;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-02
 * 
 */

@Configuration
public class ModelMapperConfig {

	@Bean
	ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.createTypeMap(Address.class, AddressDTO.class);

		return modelMapper;
	}

}
