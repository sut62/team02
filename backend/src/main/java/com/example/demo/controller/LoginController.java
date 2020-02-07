package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.text.ParseException;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class LoginController {

    @Autowired
    private final LoginRepository loginRepository;
    @Autowired
    private final EmployeeRepository employeeRepository;

    public LoginController(final LoginRepository loginRepository, EmployeeRepository employeeRepository) {
        this.loginRepository = loginRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employee")
    public Collection<Employee> employee() {
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/check/{username}/{passoword}")
    public Collection<Employee> getCheck(@PathVariable("username") String username,
            @PathVariable("passoword") String password) {
        return employeeRepository.findCheck(username, password);
    }

    @PostMapping("/login/{username}")
    public Login newLogin(Login newLogin, @PathVariable final String username) throws ParseException {

        newLogin.setEmployee(username);
       

        return loginRepository.save(newLogin);
    }
}