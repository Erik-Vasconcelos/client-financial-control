package br.com.erudio.cfc.security.token;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import br.com.erudio.cfc.dto.TokenDTO;
import br.com.erudio.cfc.exception.InvalidTokenAuthenticationException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key:secret}")
	private String secret = "";

	@Value("${security.jwt.token.expired-lenght:3600000}")
	private Long validityInMilesseconds = 0L;

	@Autowired
	private UserDetailsService userService;

	private Algorithm algorithm;

	@PostConstruct
	private void init() {
		secret = Base64.getEncoder().encodeToString(secret.getBytes());
		algorithm = Algorithm.HMAC256(secret.getBytes());
	}

	public TokenDTO createAccessToken(String username, List<String> roles) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime validity = now.plusSeconds(validityInMilesseconds / 1000);

		var accessToken = getAccessToken(username, roles, now, validity);
		var refreshToken = getRefreshToken(username, roles, now);

		return new TokenDTO(username, true, now, validity, accessToken, refreshToken);
	}
	
	public TokenDTO createRefreshToken(String refresToken) {
		if(refresToken.contains("Bearer ")) refresToken = refresToken.substring("Bearer ".length());	
		
		DecodedJWT  decodedJWT = decodeToken(refresToken);

		String username = decodedJWT.getSubject();
		List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
		
		return createAccessToken(username, roles);
	}

	private String getAccessToken(String username, List<String> roles, LocalDateTime now, LocalDateTime validity) {
		String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toString();
		Date nowDate = convertLocalDateTimeToDate(now);
		Date validityDate = convertLocalDateTimeToDate(validity);

		//@formatter:off
		return JWT.create()
				.withClaim("roles", roles)
				.withIssuedAt(nowDate)
				.withExpiresAt(validityDate)
				.withSubject(username)
				.withIssuer(issuerUrl)
				.sign(algorithm)
				.strip();
		//@formatter:on
	}

	private String getRefreshToken(String username, List<String> roles, LocalDateTime now) {
		Date nowDate = convertLocalDateTimeToDate(now);
		Date validityDate = convertLocalDateTimeToDate(now.plusSeconds((validityInMilesseconds / 1000) * 3));

		//@formatter:off
		return JWT.create()
				.withClaim("roles", roles)
				.withIssuedAt(nowDate)
				.withExpiresAt(validityDate)
				.withSubject(username)
				.sign(algorithm)
				.strip();
		//@formatter:on
	}

	private Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public Authentication getAuthentication(String token) {
		DecodedJWT decoded = decodeToken(token);

		UserDetails user = userService.loadUserByUsername(decoded.getSubject());
		return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
	}

	private DecodedJWT decodeToken(String token) {
		try {
			Algorithm alg = Algorithm.HMAC256(secret.getBytes());
			JWTVerifier verifier = JWT.require(alg).build();
			DecodedJWT decoded = verifier.verify(token);

			return decoded;
		} catch (Exception e) {
			throw new InvalidTokenAuthenticationException("Invalid token");
		}
	}

	public String resolveToken(HttpServletRequest request) {
		String bearer = request.getHeader("Authorization");

		if (bearer != null && bearer.startsWith("Bearer ")) {
			return bearer.substring("Bearer ".length());
		}

		return null;
	}

	public boolean validatetoken(String token) {
		try {
			DecodedJWT decoded = decodeToken(token);

			if (decoded.getExpiresAt().before(new Date())) {
				return false;
			}

			return true;
		} catch (Exception e) {
			throw new InvalidTokenAuthenticationException("Expired or invalid token");
		}
	}
}
