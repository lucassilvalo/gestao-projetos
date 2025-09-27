package com.gestaoprojetos.config;

import com.gestaoprojetos.model.User;
import com.gestaoprojetos.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = User.builder()
                    .name("Admin")
                    .email("admin@gestaoprojetos.com")
                    .password("123456")
                    .role(User.Role.ADMIN)
                    .build();
            userRepository.save(admin);
            System.out.println("Admin user created!");
        }
    }

}
