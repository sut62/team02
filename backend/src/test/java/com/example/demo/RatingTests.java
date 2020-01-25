package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.example.demo.entity.Rating;
import com.example.demo.repository.RatingRepo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RatingTests{

    private Validator validator;

    @Autowired
    private RatingRepo ratingRepo;
    
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void b5908783_testRatingMustBeOk() {
        Rating rating = new Rating();
        rating.setRating("มากที่สุด");

        rating = ratingRepo.saveAndFlush(rating);

        Optional<Rating> found = ratingRepo.findById(rating.getId());
        assertEquals("มากที่สุด" ,found.get().getRating());
    }

    @Test
    void b5908783_testRatingMustNotBeNull(){
        Rating rating = new Rating();
        rating.setRating(null);
        
        Set<ConstraintViolation<Rating>> result = validator.validate(rating);
        
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1,result.size());

        //Error Message ต้องถูก  field
        ConstraintViolation<Rating> v = result.iterator().next();
        assertEquals("Must not be null", v.getMessage());
        assertEquals("rating", v.getPropertyPath().toString());
    }
}
