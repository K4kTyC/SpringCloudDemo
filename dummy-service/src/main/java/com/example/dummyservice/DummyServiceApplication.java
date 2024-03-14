package com.example.dummyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DummyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DummyServiceApplication.class, args);
	}

}
@Service
class DummyService {

	@PreAuthorize("hasAuthority('SCOPE_user.read')")
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
	String getText() {
		return dummyService.getText();
	}
}