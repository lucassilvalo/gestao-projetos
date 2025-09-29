package com.gestaoprojetos.config;

import com.gestaoprojetos.model.User;
import com.gestaoprojetos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminInitializer  implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) { // Só cria se não existir nenhum usuário
            String rawPassword = generateSecurePassword(12); // Senha aleatória de 12 caracteres
            User admin = User.builder()
                    .name("Admin")
                    .email("admin@gestaoprojetos.com")
                    .password(passwordEncoder.encode(rawPassword))
                    .role(User.Role.ADMIN)
                    .build();
            userRepository.save(admin);

            log.info("Admin user created");
            log.info("Email: {}", "admin@gestaoprojetos.com");
            log.info("Password (change immediately!): {}", rawPassword);
        }
    }

    private String generateSecurePassword(int length) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes).substring(0, length);
    }
}
