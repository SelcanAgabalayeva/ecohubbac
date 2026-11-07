package com.ecohub.ecohubbac.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
    @Component
    @RequiredArgsConstructor
    public class JwtAuthenticationFilter extends OncePerRequestFilter {

        private final JwtUtils jwtUtils;
        private final UserDetailsService userDetailsService;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {

            String headerAuth = request.getHeader("Authorization");
            String token = null;
            String username = null;

            if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
                token = headerAuth.substring(7);
                try {
                    username = jwtUtils.getUsernameFromToken(token);
                    System.out.println("🔍 [Filter] Token tapıldı, User: " + username);
                } catch (Exception e) {
                    System.out.println("❌ [Filter] Token-dən username oxunmadı: " + e.getMessage());
                }
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                System.out.println("🔍 [Filter] Bazadan yüklənən User authorities: " + userDetails.getAuthorities());

                // BURADA YOXLAYIRIQ:
                boolean isValid = jwtUtils.validateToken(token);
                System.out.println("🔍 [Filter] validateToken nəticəsi: " + isValid);

                if (isValid) {
                    System.out.println("✅ [Filter] Token etibarlıdır, SecurityContext-ə yazılır.");
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    System.out.println("❌ [Filter] jwtUtils.validateToken(token) FALSE qaytardı! Token keçərsizdir və ya vaxtı bitib.");
                }
            }

            filterChain.doFilter(request, response);
        }
    }