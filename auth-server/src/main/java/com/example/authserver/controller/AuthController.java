package com.example.authserver.controller;

import com.example.authserver.dto.NewUserDto;
import com.example.authserver.facade.UserFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserFacade userFacade;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid NewUserDto userDto, HttpServletRequest request, Authentication authentication) throws ServletException {
        if (authentication != null && authentication.isAuthenticated()) {
            return new ResponseEntity<>("Already authenticated", HttpStatus.FORBIDDEN);
        }

        userFacade.createUser(userDto);
        request.login(userDto.getUsername(), userDto.getPassword());
        return ResponseEntity.noContent().build();
    }


}
