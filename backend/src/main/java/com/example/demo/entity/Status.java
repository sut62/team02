package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table
public class Status{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="STATUS_SEQ")  
    private Long id;

    @NotNull(message = "Must not be null")
    private String status;

}