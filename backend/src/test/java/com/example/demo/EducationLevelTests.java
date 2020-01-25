package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.example.demo.entity.EducationLevel;
import com.example.demo.entity.RateUsagePoint;
import com.example.demo.entity.Rating;
import com.example.demo.entity.Status;
import com.example.demo.repository.EducationLevelRepo;
import com.example.demo.repository.RateUsagePointRepo;
import com.example.demo.repository.RatingRepo;
import com.example.demo.repository.StatusRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EducationLevelTests{

    @Autowired
    private EducationLevelRepo edlevelRepo;

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b5908783_testEducationLevelMustBeOk() {
        EducationLevel edlevel = new EducationLevel();
        edlevel.setEdlevel("ปริญญาเอก");

       edlevel = edlevelRepo.saveAndFlush(edlevel);

        Optional<EducationLevel> found = edlevelRepo.findById(edlevel.getId());
        assertEquals("ปริญญาเอก" ,found.get().getEdlevel());
    }

    @Test
    void b5908783_testEducationLevelMustNotBeNull() {
        EducationLevel edlevel = new EducationLevel();
        edlevel.setEdlevel(null);

        Set<ConstraintViolation<EducationLevel>> result = validator.validate(edlevel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<EducationLevel> v = result.iterator().next();
        assertEquals("Must not be null", v.getMessage());
        assertEquals("edlevel", v.getPropertyPath().toString());
    }
}
