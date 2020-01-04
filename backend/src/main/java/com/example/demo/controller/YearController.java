package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entity.Year;
import com.example.demo.repository.YearRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class YearController {

    @Autowired
    private final YearRepository yearRepository;

    public YearController(YearRepository yearRepository) {
        this.yearRepository = yearRepository;
    }

    @GetMapping("/year")
    public Collection<Year> year() {
        return yearRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/year/{yearid}")
    public Optional<Year> Year(@PathVariable Long id) {
        Optional<Year> year = yearRepository.findById(id);
        return year;
    }
}