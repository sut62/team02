package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.example.demo.entity.BookType;
import com.example.demo.entity.Borrow;
import com.example.demo.entity.Document;
import com.example.demo.repository.BookTypeRepository;
import com.example.demo.repository.BorrowRepository;
import com.example.demo.repository.DocumentRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @DataJpaTest
@SpringBootTest
@RunWith(SpringRunner.class)
public class BorrowTests {
    private Validator validator;
    @Autowired
    private BorrowRepository borrowRepo;

    @Autowired
    private BookTypeRepository bookTypeRepository;

    @Autowired
    private DocumentRepository documentRepository;


    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void b5923403_testNotnull() {
       

        Borrow borrow = Borrow.builder().numbers(null).Descripton("abcdefghijklmnopqrstuvwxy")
        .borrowDate(new Date())
        
                .tell("0812345678").build();

        Set<ConstraintViolation<Borrow>> result = validator.validate(borrow);
        assertEquals(1, result.size());
        ConstraintViolation<Borrow> violation = result.iterator().next();
        assertEquals("Notnull", violation.getMessage());
        assertEquals("numbers", violation.getPropertyPath().toString());
    }

    @Test
    public void b5923403_testSize() {
        
        Borrow borrow = Borrow.builder().numbers(1L).Descripton("abcdefghijklmnopqrstuvwxyz").borrowDate(new Date())
                .tell("0812345678").build();
        Set<ConstraintViolation<Borrow>> result = validator.validate(borrow);
        assertEquals(1, result.size());
        ConstraintViolation<Borrow> violation = result.iterator().next();
        assertEquals("Joe", violation.getMessage());
        assertEquals("Descripton", violation.getPropertyPath().toString());
    }

    @Test
    public void b5923403_testPattern() {
        
        Borrow borrow = Borrow.builder().numbers(1L).Descripton("abcdefghijklmnopqrstuvwxy").borrowDate(new Date())
                .tell("01234").build();
        Set<ConstraintViolation<Borrow>> result = validator.validate(borrow);
        assertEquals(1, result.size());
        ConstraintViolation<Borrow> violation = result.iterator().next();
        assertEquals("error", violation.getMessage());
        assertEquals("tell", violation.getPropertyPath().toString());
    }

}
