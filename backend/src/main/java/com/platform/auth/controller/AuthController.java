package com.platform.auth.controller;

import com.platform.auth.dto.SignupRequest;
import com.platform.auth.dto.SignupResponse;
import com.platform.user.model.User;
import com.platform.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody @Valid SignupRequest request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setMobile(request.getMobile());
        user.setEmail(request.getEmail());
        user.setCountry(request.getCountry());
        user.setCity(request.getCity());

        User saved = userRepository.save(user);

        // Token generation will be wired later; using placeholder for now.
        String fakeToken = saved.getId().toString();

        SignupResponse response = new SignupResponse(saved.getId(), saved.getFullName(), fakeToken);
        return ResponseEntity.ok(response);
    }
}

