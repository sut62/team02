package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entity.Video;

@RepositoryRestResource
public interface VideoRepository extends JpaRepository<Video, Long>{
    
}