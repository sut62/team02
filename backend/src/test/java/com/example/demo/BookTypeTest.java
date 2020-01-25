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

import com.example.demo.entity.BookType;
import com.example.demo.repository.BookTypeRepository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class BookTypeTest {

    private Validator validator;

    @Autowired
    BookTypeRepository BookTypeRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCreateBookTypeOK() {
        BookType bookType = new BookType();
        bookType.setBookTypeName("Math");
		bookType = BookTypeRepository.saveAndFlush(bookType);

		Optional<BookType> BookType = BookTypeRepository.findById(bookType.getId());
        
        assertEquals("Math", BookType.get().getBookTypeName());
    }

	@Test
    void testBookTypeIdMustNotBeNull() {
        BookType bookType = new BookType();
        bookType.setBookTypeName("Math");
        bookType.setId(null);

		Set<ConstraintViolation<BookType>> result = validator.validate(bookType);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<BookType> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }
    
    @Test
    void testBookTypeNameMustNotBeNull() {
        BookType bookType = new BookType();
        bookType.setBookTypeName(null);
        bookType.setId(1L);

		Set<ConstraintViolation<BookType>> result = validator.validate(bookType);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<BookType> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("bookTypeName", v.getPropertyPath().toString());
    }
}