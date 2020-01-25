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

import com.example.demo.entity.BookCategory;
import com.example.demo.repository.BookCategoryRepository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class BookCategoryTest {

    private Validator validator;

    @Autowired
    BookCategoryRepository BookCategoryRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5906222_testCreateBookCategoryOK() {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setBookCategoryName("Fantasy");
		bookCategory = BookCategoryRepository.saveAndFlush(bookCategory);

		Optional<BookCategory> BookCategory = BookCategoryRepository.findById(bookCategory.getId());
        
        assertEquals("Fantasy", BookCategory.get().getBookCategoryName());
    }

	@Test
    void B5906222_testBookCategoryIdMustNotBeNull() {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setBookCategoryName("Fantasy");
        bookCategory.setId(null);

		Set<ConstraintViolation<BookCategory>> result = validator.validate(bookCategory);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<BookCategory> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }
    
    @Test
    void B5906222_testBookCategoryNameMustNotBeNull() {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setBookCategoryName(null);
        bookCategory.setId(1L);

		Set<ConstraintViolation<BookCategory>> result = validator.validate(bookCategory);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<BookCategory> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("bookCategoryName", v.getPropertyPath().toString());
    }
}