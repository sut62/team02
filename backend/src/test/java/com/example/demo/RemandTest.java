package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.example.demo.entity.*;
import com.example.demo.repository.*;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class RemandTest {

    private Validator validator;
    @Autowired
    RemandRepository RemandRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5902200_testCreateRemandOK() {
        Remand remand = new Remand();
        Date nowDate = new Date();
        remand.setRemanddate(nowDate);
        remand.setAmount(1);
		remand = RemandRepository.saveAndFlush(remand);

		Optional<Remand> Remand = RemandRepository.findById(remand.getId());
        
        assertEquals(nowDate, Remand.get().getRemanddate());
    }

	@Test
    void B5902200_testRemandIdMustNotBeNull() {
        Remand remand = new Remand();
        Date nowDate = new Date();
        remand.setAmount(1);
        remand.setRemanddate(nowDate);
        remand.setId(null);

		Set<ConstraintViolation<Remand>> result = validator.validate(remand);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Remand> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }
    
    @Test
    void B5902200_testRemanddateMustNotBeNull() {
        Remand remand = new Remand();
        remand.setRemanddate(null);
        remand.setId(1L);
        remand.setAmount(1);

		Set<ConstraintViolation<Remand>> result = validator.validate(remand);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Remand> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("remanddate", v.getPropertyPath().toString());
    }

    @Test
    void B5902200_testAmountMustNotLessThan1() {
        Remand remand = new Remand();
        Date date = new Date();
        remand.setRemanddate(date);
        remand.setId(1L);
        remand.setAmount(0);

		Set<ConstraintViolation<Remand>> result = validator.validate(remand);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Remand> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }

    @Test
    void B5902200_testAmountMustNotLessThan10() {
        Remand remand = new Remand();
        Date date = new Date();
        remand.setRemanddate(date);
        remand.setId(1L);
        remand.setAmount(11);

		Set<ConstraintViolation<Remand>> result = validator.validate(remand);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Remand> v = result.iterator().next();
        assertEquals("must be less than or equal to 10", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }
}