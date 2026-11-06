package com.ecohub.ecohubbac.config;

import com.ecohub.ecohubbac.entity.Role;
import com.ecohub.ecohubbac.entity.RoleName;
import com.ecohub.ecohubbac.entity.User;
import com.ecohub.ecohubbac.repository.RoleRepository;
import com.ecohub.ecohubbac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.default.username}")
    private String adminUsername;

    @Value("${admin.default.password}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(RoleName.ROLE_ADMIN);
                    return roleRepository.save(role);
                });

        if (userRepository.findByUsername(adminUsername).isEmpty()) {
            User admin = new User();
            admin.setUsername(adminUsername);
            admin.setPassword(passwordEncoder.encode(adminPassword)); // Şifrələnir
            admin.setRoles(Collections.singleton(adminRole));

            userRepository.save(admin);
            System.out.println("✅ İlkin Admin uğurla yaradıldı!");
        }
    }
}
