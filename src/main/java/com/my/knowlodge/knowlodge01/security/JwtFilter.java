package com.my.knowlodge.knowlodge01.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;
    @Value("${app.security.token_prefix}")
    private String TOKEN_PREFIX;
    @Value("${app.security.cookie_name}")
    private String COOKIE_NAME;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
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
            System.out.println(e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    public void createCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(COOKIE_NAME, TOKEN_PREFIX.concat(token));
        cookie.setMaxAge(360);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public Optional<String> token(HttpServletRequest request) {
        String token = getCookie(request);
        if (StringUtils.hasText(token) && token.startsWith(TOKEN_PREFIX)) {
            return Optional.of(token.replace(TOKEN_PREFIX, ""));
        }
        return Optional.empty();
    }
}
