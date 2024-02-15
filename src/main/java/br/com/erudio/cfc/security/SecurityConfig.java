package br.com.erudio.cfc.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.erudio.cfc.security.token.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtTokenProvider tokenProvider;

	@Bean
	PasswordEncoder getPasswordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();

		Pbkdf2PasswordEncoder pbkdf2 = new Pbkdf2PasswordEncoder("", 8, 185000,
				SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		encoders.put("pbkdf2", pbkdf2);

		DelegatingPasswordEncoder delegating = new DelegatingPasswordEncoder("pbkdf2", encoders);
		delegating.setDefaultPasswordEncoderForMatches(pbkdf2);

		return delegating;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain httpSecurityFilterChain(HttpSecurity http) throws Exception {
		JwtTokenFilter filter = new JwtTokenFilter(tokenProvider);

		//@formatter:off
		return http.httpBasic(basic -> basic.disable())
				.csrf(csrf -> csrf.disable())
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> 
						authorize.requestMatchers(
								"/auth/signin",
								"/auth/refresh/**",
								"/swagger-ui/**",
								"/v3/api-docs/**"
						).permitAll()
						.requestMatchers("/api/**").authenticated()
						.requestMatchers("/users").denyAll()
				).cors(cors -> {})
				.build();
		//@formatter:on

	}

}
