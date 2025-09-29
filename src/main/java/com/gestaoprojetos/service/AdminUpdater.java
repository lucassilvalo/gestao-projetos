package com.gestaoprojetos.service;

import com.gestaoprojetos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class AdminUpdater {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void updateAdminPassword(String newPassword) {
        userRepository.findByEmail("admin@gestaoprojetos.com").ifPresent(admin -> {
            admin.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(admin);
            log.info("Admin password updated sucessfully!");
        });
    }

}
