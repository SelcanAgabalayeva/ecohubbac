package com.ecohub.ecohubbac.controller;

import com.ecohub.ecohubbac.dto.AuthRequestDTO;
import com.ecohub.ecohubbac.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/admin")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequestDTO requestDTO) {
        try {
            System.out.println("Login cəhdi edilir: " + requestDTO.getUsername());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword())
            );

            System.out.println("Uğurlu authentication!");
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(jwt);

        } catch (BadCredentialsException e) {
            System.out.println("❌ Şifrə və ya istifadəçi adı səhvdir!");
            return ResponseEntity.status(401).body("Şifrə və ya istifadəçi adı səhvdir!");
        } catch (Exception e) {
            System.out.println("❌ Gözlənilməyən xəta: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Xəta baş verdi: " + e.getMessage());
        }
    }
}