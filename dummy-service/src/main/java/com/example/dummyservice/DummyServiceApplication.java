package com.example.dummyservice;

import com.example.common.TextDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DummyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DummyServiceApplication.class, args);
	}

}
@Service
class DummyService {

	public String getText() {
		return "kek";
	}
}

@RestController
class DummyController {

	private final DummyService dummyService;

    DummyController(DummyService dummyService) {
        this.dummyService = dummyService;
    }

	@GetMapping("/")
	TextDto getText() {
		return new TextDto(dummyService.getText());
	}

	@GetMapping("/api/token")
	//@PreAuthorize("hasAuthority('roles.read')")
	Map<String, Object> getRoles(Authentication authentication) {
		Map<String, Object> result = new HashMap<>();
		Jwt jwt = (Jwt) authentication.getPrincipal();
		result.putAll(jwt.getHeaders());
	    result.putAll(jwt.getClaims());
		return result;
	}
}