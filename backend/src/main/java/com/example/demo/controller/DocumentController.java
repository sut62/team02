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
import com.example.demo.entity.BookType;
import com.example.demo.entity.Document;
import com.example.demo.entity.Language;
import com.example.demo.repository.BookCategoryRepository;
import com.example.demo.repository.BookTypeRepository;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.LanguageRepository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class DocumentTest {

    private Validator validator;

    @Autowired
    DocumentRepository DocumentRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCreateDocumentOK() {
        Document document = new Document();
        document.setAmount(1);
        document.setId(1L);
        document.setBookName("Head First");
        document.setWritterName("Bert Bates");
		document = DocumentRepository.saveAndFlush(document);

		Optional<Document> Document = DocumentRepository.findById(document.getId());
        
        assertEquals(1L, Document.get().getId());
        assertEquals(1, Document.get().getAmount());
        assertEquals("Head First", Document.get().getBookName());
        assertEquals("Bert Bates", Document.get().getWritterName());
    }

	@Test
    void testDocumentIdMustNotBeNull() {
        Document document = new Document();
        document.setAmount(1);
        document.setId(null);
        document.setBookName("Head First");
        document.setWritterName("Bert Bates");

		Set<ConstraintViolation<Document>> result = validator.validate(document);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }

    @Test
    void testWritterNameMustNotBeNull() {
        Document document = new Document();
        document.setAmount(1);
        document.setId(1L);
        document.setBookName("Head First");
        document.setWritterName(null);

		Set<ConstraintViolation<Document>> result = validator.validate(document);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("writterName", v.getPropertyPath().toString());
    }

    @Test
    void testBookNameMustNotBeNull() {
        Document document = new Document();
        document.setAmount(1);
        document.setId(1L);
        document.setBookName(null);
        document.setWritterName("Bert Bates");

		Set<ConstraintViolation<Document>> result = validator.validate(document);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("bookName", v.getPropertyPath().toString());
    }

    @Test
    void testAmountMustNotBeNull() {
        Document document = new Document();
        document.setAmount(null);
        document.setId(1L);
        document.setBookName("Head First");
        document.setWritterName("Bert Bates");

		Set<ConstraintViolation<Document>> result = validator.validate(document);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }
    

    @Test
    void testWritterNameNotLessThan2() {
        Document document = new Document();
        document.setAmount(1);
        document.setId(1L);
        document.setBookName("Head First");
        document.setWritterName("B");

		Set<ConstraintViolation<Document>> result = validator.validate(document);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("size must be between 2 and 20", v.getMessage());
        assertEquals("writterName", v.getPropertyPath().toString());
    }

    @Test
    void testWritterNameNotMoreThan20() {
        Document document = new Document();
        document.setAmount(1);
        document.setId(1L);
        document.setBookName("Head First");
        document.setWritterName("Bert Bates Writter Of this book");

        Set<ConstraintViolation<Document>> result = validator.validate(document);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("size must be between 2 and 20", v.getMessage());
        assertEquals("writterName", v.getPropertyPath().toString());
    }

    @Test
    void testWritterNameNotEnterSpecialCharactor() {
        Document document = new Document();
        document.setAmount(1);
        document.setId(1L);
        document.setBookName("Head First");
        document.setWritterName("Bert !#%@%");

		Set<ConstraintViolation<Document>> result = validator.validate(document);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("must match \"^[A-Za-zก-์\\s]+$\"", v.getMessage());
        assertEquals("writterName", v.getPropertyPath().toString());
    }

    @Test
    void testBookNameNotLessThan2() {
        Document document = new Document();
        document.setAmount(1);
        document.setId(1L);
        document.setBookName("H");
        document.setWritterName("Bert Bates");

		Set<ConstraintViolation<Document>> result = validator.validate(document);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("size must be between 2 and 10", v.getMessage());
        assertEquals("bookName", v.getPropertyPath().toString());
    }

    @Test
    void testBookNameNotMoreThan10() {
        Document document = new Document();
        document.setAmount(1);
        document.setId(1L);
        document.setBookName("Head Firsts");
        document.setWritterName("Bert Bates");

		Set<ConstraintViolation<Document>> result = validator.validate(document);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("size must be between 2 and 10", v.getMessage());
        assertEquals("bookName", v.getPropertyPath().toString());
    }

    @Test
    void testBookNameNotEnterSpecialCharactor() {
        Document document = new Document();
        document.setAmount(1);
        document.setId(1L);
        document.setBookName("Head @$#@");
        document.setWritterName("Bert Bates");

		Set<ConstraintViolation<Document>> result = validator.validate(document);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("must match \"^[A-Za-zก-์\\s]+$\"", v.getMessage());
        assertEquals("bookName", v.getPropertyPath().toString());
    }

    @Test
    void testAmountNotLessThan1() {
        Document document = new Document();
        document.setAmount(0);
        document.setId(1L);
        document.setBookName("Head First");
        document.setWritterName("Bert Bates");

		Set<ConstraintViolation<Document>> result = validator.validate(document);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }

    @Test
    void testAmountNotMoreThan20() {
        Document document = new Document();
        document.setAmount(21);
        document.setId(1L);
        document.setBookName("Head First");
        document.setWritterName("Bert Bates");

		Set<ConstraintViolation<Document>> result = validator.validate(document);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("must be less than or equal to 20", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }
}