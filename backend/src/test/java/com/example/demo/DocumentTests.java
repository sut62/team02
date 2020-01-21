package com.example.demo;

import com.example.demo.entity.Document;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;




@DataJpaTest
public class DocumentTests {

    private Validator validator;

    @Autowired
    private DocumentRepository documentRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    //ของ field amount

    @Test
    void B5906222_testAmountMustNotBeNull(){
        Document e1 = new Document();
        e1.setBookName("DIY");
        e1.setWritterName("Makee");

        Set<ConstraintViolation<Document>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }

    @Test
    void B5906222_testAmountMustLessThen1(){
        Document e1 = new Document();
        e1.setAmount(0);
        e1.setBookName("DIY");
        e1.setWritterName("Makee");

        Set<ConstraintViolation<Document>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }

    @Test
    void B5915064_testAmountMustMoreThen20(){
        Document e1 = new Document();
        e1.setAmount(21);
        e1.setBookName("DIY");
        e1.setWritterName("Makee");

        Set<ConstraintViolation<Document>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("must be less than or equal to 20", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }

       // Test ของ field bookName
       
    @Test
    void B5906222_testBookNameMustNotBeNull(){
        Document e1 = new Document();
        e1.setAmount(10);
        e1.setWritterName("Makee");

        Set<ConstraintViolation<Document>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("size must be between 2 and 10", v.getMessage());
        assertEquals("bookName", v.getPropertyPath().toString());
    }

    @Test
    void B5906222_testBookNameMustLessThen2(){
        Document e1 = new Document();
        e1.setAmount(10);
        e1.setBookName("D");
        e1.setWritterName("Makee");

        Set<ConstraintViolation<Document>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("size must be between 2 and 10", v.getMessage());
        assertEquals("bookName", v.getPropertyPath().toString());
    }

    @Test
    void B5906222_testBookNameMustMoreThen10(){
        Document e1 = new Document();
        e1.setAmount(10);
        e1.setBookName("DIYVERYGOOD");
        e1.setWritterName("Makee");

        Set<ConstraintViolation<Document>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("size must be between 2 and 10", v.getMessage());
        assertEquals("bookName", v.getPropertyPath().toString());
    }
    // Test ของ field writterName
    @Test
    void B5906222_testWritterMustNotBeNull(){
        Document e1 = new Document();
        e1.setAmount(10);
        e1.setBookName("DIY");
       

        Set<ConstraintViolation<Document>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("size must be between 2 and 20", v.getMessage());
        assertEquals("writterName", v.getPropertyPath().toString());
    }

    @Test
    void B5906222_testWritterNameMustLessThen2(){
        Document e1 = new Document();
        e1.setAmount(10);
        e1.setBookName("DIY");
        e1.setWritterName("M");

        Set<ConstraintViolation<Document>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("size must be between 2 and 20", v.getMessage());
        assertEquals("writterName", v.getPropertyPath().toString());
    }

    @Test
    void B5906222_testWritterNameMustMoreThen20(){
        Document e1 = new Document();
        e1.setAmount(10);
        e1.setBookName("DIY");
        e1.setWritterName("MakeedodomaMakeedodom");

        Set<ConstraintViolation<Document>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<Document> v = result.iterator().next();
        assertEquals("size must be between 2 and 20", v.getMessage());
        assertEquals("writterName", v.getPropertyPath().toString());
    }



   /* @Test
    void B5915064_testPressureMustNotBeNull(){
        ExaminationSystem e1 = new ExaminationSystem();
        e1.setPulse(90);
        e1.setSymptom("Something");

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("size must be between 2 and 7", v.getMessage());
        assertEquals("pressure", v.getPropertyPath().toString());
    }

    @Test
    void B5915064_testPressureMustLessthen2(){
        ExaminationSystem e1 = new ExaminationSystem();
        e1.setPulse(90);
        e1.setPressure("9");
        e1.setSymptom("Something");

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("size must be between 2 and 7", v.getMessage());
        assertEquals("pressure", v.getPropertyPath().toString());
    }

    @Test
    void B5915064_testPressureMustLessthen7(){
        ExaminationSystem e1 = new ExaminationSystem();
        e1.setPulse(90);
        e1.setPressure("100/1200");
        e1.setSymptom("Something");

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("size must be between 2 and 7", v.getMessage());
        assertEquals("pressure", v.getPropertyPath().toString());
    }*/



}






