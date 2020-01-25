package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import com.example.demo.entity.Status;
import com.example.demo.repository.StatusRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class StatusTests{

    private Validator validator;

    @Autowired
    private StatusRepository statusRepo;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b5908783_testStatusMustBeOk() {
        Status status = new Status();
        status.setStatus("นักศึกษา");

        status = statusRepo.saveAndFlush(status);

        Optional<Status> found = statusRepo.findById(status.getId());
        assertEquals("นักศึกษา",found.get().getStatus());
    }

    @Test
    void b5908783_testStatusMustNotBeNull(){
        Status status = new Status();
        status.setStatus(null);
        
        Set<ConstraintViolation<Status>> result = validator.validate(status);
        
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1,result.size());

        //Error Message ต้องถูก  field
        ConstraintViolation<Status> v = result.iterator().next();
        assertEquals("Must not be null", v.getMessage());
        assertEquals("status", v.getPropertyPath().toString());
    }
}