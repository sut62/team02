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

import com.example.demo.entity.Language;
import com.example.demo.repository.LanguageRepository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class LanguageTest {

    private Validator validator;

    @Autowired
    LanguageRepository LanguageRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5906222_testCreateLanguageOK() {
        Language language = new Language();
        language.setLanguageName("English");
		language = LanguageRepository.saveAndFlush(language);

		Optional<Language> Language = LanguageRepository.findById(language.getId());
        
        assertEquals("English", Language.get().getLanguageName());
    }

	@Test
    void B5906222_testLanguageIdMustNotBeNull() {
        Language language = new Language();
        language.setLanguageName("English");
        language.setId(null);

		Set<ConstraintViolation<Language>> result = validator.validate(language);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Language> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }
    
    @Test
    void B5906222_testLanguageNameMustNotBeNull() {
        Language language = new Language();
        language.setLanguageName(null);
        language.setId(1L);

		Set<ConstraintViolation<Language>> result = validator.validate(language);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Language> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("languageName", v.getPropertyPath().toString());
    }
}