package com.example.demo.dataloader;

import java.util.stream.Stream;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDataLoader implements ApplicationRunner {

    @Autowired
    private EmployeeRepository empRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
            Employee empl = new Employee();
            empl.setUsername("admin");
            empl.setPassword("123456789");
            empRepo.save(empl);
    }
}