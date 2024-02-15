package br.com.erudio.cfc.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.erudio.cfc.exception.InvalidTokenAuthenticationException;
import br.com.erudio.cfc.security.token.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Erik Vasconcelos
 * @since 2024-02-14
 */
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider provider;
	
	public JwtTokenFilter(JwtTokenProvider provider) {
		this.provider = provider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			String token = provider.resolveToken(request);

			if (token != null && provider.validatetoken(token)) {
				Authentication auth = provider.getAuthentication(token);

				if (auth != null) {
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
		} catch (InvalidTokenAuthenticationException e) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write(e.getMessage());
			response.getWriter().flush();
			return;
		}

		filterChain.doFilter(request, response);
	}

}
