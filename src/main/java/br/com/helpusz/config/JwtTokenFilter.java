package br.com.helpusz.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;

	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

			String token = getTokenFromRequest(request);	

        if(token != null && jwtTokenProvider.validateToken(token)) {
					String username = jwtTokenProvider.getUsernameFromToken(token);
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, null);
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}

			filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
			String bearerToken = request.getHeader("Authorization");
			if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
				return bearerToken.substring(7);
			}
			return null;
    }
}

