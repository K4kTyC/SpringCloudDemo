package com.example.authserver.controller;

import com.example.authserver.dto.NewUserDto;
import com.example.authserver.facade.UserFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserFacade userFacade;


    @PostMapping("/auth/register")
    public void register(@RequestBody @Valid NewUserDto userDto, HttpServletRequest request) {
        userFacade.createUser(userDto);
        authWithHttpServletRequest(request, userDto.getUsername(), userDto.getPassword());
    }

    private void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
