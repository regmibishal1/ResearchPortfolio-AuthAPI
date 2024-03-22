package com.researchportfolio.api.user;

import com.researchportfolio.api.auth.ChangePasswordRequest;
import com.researchportfolio.api.token.TokenType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {

    private final UserService service;

    @GetMapping
    @Operation(security = { @SecurityRequirement(name = "BEARER") })
    public ResponseEntity<?> getUserInfo(Principal connectedUser) {
        User userData = service.getUserInfo(connectedUser);
        return ResponseEntity.ok(userData);
    }

    @PatchMapping
    @Operation(security = { @SecurityRequirement(name = "BEARER") })
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

}
