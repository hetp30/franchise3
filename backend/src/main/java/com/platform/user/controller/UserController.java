package com.platform.user.controller;

import com.platform.user.dto.RoleSelectionRequest;
import com.platform.user.model.User;
import com.platform.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping("/{userId}/roles")
    public ResponseEntity<Void> updateRoles(
            @PathVariable UUID userId,
            @RequestBody @Valid RoleSelectionRequest request
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setRoles(request.getRoles());
        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }
}

