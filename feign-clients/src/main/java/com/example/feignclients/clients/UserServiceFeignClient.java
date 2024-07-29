package com.example.feignclients.clients;

import com.example.feignclients.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserServiceFeignClient {

    @GetMapping("/api/users/")
    UserDto getUserByUsername(@PathVariable("username") String username);
}
