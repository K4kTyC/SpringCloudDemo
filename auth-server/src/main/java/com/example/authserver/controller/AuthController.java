package com.example.authserver.controller;

import com.example.authserver.dto.NewUserDto;
import com.example.authserver.dto.UserInfoDto;
import com.example.authserver.facade.UserFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/userinfo")
    public ResponseEntity<UserInfoDto> currentUserInfo(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        String role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(a -> a.startsWith("ROLE_"))
                .findFirst().orElse("");
        return ResponseEntity.ok(new UserInfoDto(username, role));
    }

}
