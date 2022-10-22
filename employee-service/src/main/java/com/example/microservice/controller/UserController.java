package com.example.microservice.controller;

import com.example.microservice.entity.Employee;
import com.example.microservice.service.UserService;
import com.example.microservice.valueObject.ResponseTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public Employee saveEmployee (@RequestBody Employee employee){
        log.info("Inside save user of user controller");
        return userService.saveUser(employee);

    }

    @GetMapping("/{id}")
    @CircuitBreaker(name="GetUserBreaker")
    public ResponseTemplate getUserWithDepartment(@PathVariable("id") Long userId){
        return userService.getUserWithDepartment(userId);
    }

}
