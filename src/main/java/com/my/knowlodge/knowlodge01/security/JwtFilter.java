package com.my.knowlodge.knowlodge01.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final static Logger log = LoggerFactory.getLogger(JwtFilter.class);
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;
    @Value("${app.security.token_prefix}")
    private String TOKEN_PREFIX;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            log.info("Token from frontend started");
            if (token(request).isPresent()) {
                String finalToken = token(request).get();
                String user = this.jwtService.getUsername(finalToken);
                if (this.jwtService.isValid(user, finalToken)) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(user);
                    var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            log.error("Token error", e);
        }
        filterChain.doFilter(request, response);
    }

    public Optional<String> token(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith(TOKEN_PREFIX)) {
            return Optional.of(token.replace(TOKEN_PREFIX, ""));
        }
        return Optional.empty();
    }
}
