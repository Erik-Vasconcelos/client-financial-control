package br.com.erudio.cfc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;

/**
 * @author Erik Vasconcelos
 * @since 2024-01-31
 */

@SpringBootApplication
public class ClientFinancialControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientFinancialControlApplication.class, args);
		
		
		Map<String, PasswordEncoder> encoders = new HashMap<>();

		Pbkdf2PasswordEncoder pbkdf2 = new Pbkdf2PasswordEncoder("", 8, 185000,
				SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		encoders.put("pbkdf2", pbkdf2);

		DelegatingPasswordEncoder delegating = new DelegatingPasswordEncoder("pbkdf2", encoders);
		delegating.setDefaultPasswordEncoderForMatches(pbkdf2);
		
		System.out.println(delegating.encode("admin123"));
	}

}
