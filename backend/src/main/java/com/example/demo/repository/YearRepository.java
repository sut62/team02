package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entity.Year;

@RepositoryRestResource
public interface YearRepository extends JpaRepository<Year, Long>{
    Year findById(long yearid);
}