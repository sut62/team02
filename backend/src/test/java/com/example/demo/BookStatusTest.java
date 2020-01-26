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

import com.example.demo.entity.BookStatus;
import com.example.demo.repository.BookStatusRepository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class BookStatusTest {

    private Validator validator;

    @Autowired
    BookStatusRepository BookStatusRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5902200_testCreateBookStatusOK() {
        BookStatus bookStatus = new BookStatus();
        bookStatus.setBookStatusName("Booking");
		bookStatus = BookStatusRepository.saveAndFlush(bookStatus);

		Optional<BookStatus> BookStatus = BookStatusRepository.findById(bookStatus.getId());
        
        assertEquals("Booking", BookStatus.get().getBookStatusName());
    }

	@Test
    void B5902200_testBookStatusIdMustNotBeNull() {
        BookStatus bookStatus = new BookStatus();
        bookStatus.setBookStatusName("Booking");
        bookStatus.setId(null);

		Set<ConstraintViolation<BookStatus>> result = validator.validate(bookStatus);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<BookStatus> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }
    
    @Test
    void B5902200_testBookStatusNameMustNotBeNull() {
        BookStatus bookStatus = new BookStatus();
        bookStatus.setBookStatusName(null);
        bookStatus.setId(1L);

		Set<ConstraintViolation<BookStatus>> result = validator.validate(bookStatus);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<BookStatus> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("bookStatusName", v.getPropertyPath().toString());
    }

    @Test
    void B5902200_testBookStatusNameMustNotEnterSpecialCharactor() {
        BookStatus bookStatus = new BookStatus();
        bookStatus.setBookStatusName("Booking@@");
        bookStatus.setId(1L);

		Set<ConstraintViolation<BookStatus>> result = validator.validate(bookStatus);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<BookStatus> v = result.iterator().next();
        assertEquals("must match \"^[0-9A-Za-zก-์\\s]+$\"", v.getMessage());
        assertEquals("bookStatusName", v.getPropertyPath().toString());
    }

    @Test
    void B5902200_testBookStatusNameMustNotLessThan3() {
        BookStatus bookStatus = new BookStatus();
        bookStatus.setBookStatusName("BB");
        bookStatus.setId(1L);

		Set<ConstraintViolation<BookStatus>> result = validator.validate(bookStatus);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<BookStatus> v = result.iterator().next();
        assertEquals("size must be between 3 and 30", v.getMessage());
        assertEquals("bookStatusName", v.getPropertyPath().toString());
    }

    @Test
    void B5902200_testBookStatusNameMustNotMoreThan30() {
        BookStatus bookStatus = new BookStatus();
        bookStatus.setBookStatusName("Book status name must not more than 30");
        bookStatus.setId(1L);

		Set<ConstraintViolation<BookStatus>> result = validator.validate(bookStatus);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<BookStatus> v = result.iterator().next();
        assertEquals("size must be between 3 and 30", v.getMessage());
        assertEquals("bookStatusName", v.getPropertyPath().toString());
    }
}