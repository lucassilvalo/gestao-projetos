package com.gestaoprojetos.controller;

import com.gestaoprojetos.service.AdminUpdater;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gestaoprojetos.dto.PasswordRequest;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminUpdater adminUpdater;

    /**
     * Atualiza a senha do admin.
     * Exemplo de payload JSON: {"newPassword":"novaSenha123"}
     */

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordRequest request) {
        if (request.getNewPassword() == null ||
        request.getNewPassword().isBlank()){
            return ResponseEntity.badRequest().body("Password cannot be empty");
        }

        adminUpdater.updateAdminPassword(request.getNewPassword());
            log.info("Password updated via endpoint sucessfully");

            return  ResponseEntity.ok("Admin password updated  sucessfully");
    }
}
