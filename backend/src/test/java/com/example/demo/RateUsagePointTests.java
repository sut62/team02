package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class RateUsagePointTests{

    private Validator validator;

    @Autowired
    private RateUsagePointRepo rateUsagePointRepo;
    @Autowired
    private EducationLevelRepo educationLevelRepo;
    @Autowired
    private StatusRepository statusRepo;
    @Autowired
    private RatingRepo ratingRepo;
    
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // เทส ใส่ค่าถูกต้อง RateUsagePoint ต้องสามารถ save ได้
    @Test
    void b5908783_testRateUsagePointMustBeOk() {
        //สร้าง OBJECT เตียมไว้สำหรับใช้ set  ค่าในแต่ละ field
        RateUsagePoint rateUP = new RateUsagePoint();
        EducationLevel edd = new EducationLevel();
        Status stattt = new Status();
        Rating rating = new Rating();
        Date datee = new Date();
        
        //เตรียมข้อมูลสำหรับส่วนของ Edlevel
        edd.setEdlevel("ปริญญาตรี");
        edd = educationLevelRepo.saveAndFlush(edd);

        //เตรียมข้อมูลสำหรับส่วนของ Status
        stattt.setStatus("บุคคลทั่วไป");
        stattt = statusRepo.saveAndFlush(stattt);

        //เตรียมข้อมูลสำหรับส่วนของ Rating
        rating.setRating("ปานกลาง");
        rating = ratingRepo.saveAndFlush(rating);

        //ทำการ set แต่ละตัวแปรใน RateUsagePoint
        rateUP.setEmail("email@email.com");
        rateUP.setDate(datee);
        rateUP.setSuggestion("แย่แล้วครับ"); 
        rateUP.setStatus(stattt);
        rateUP.setEdlevel(edd);
        rateUP.setRating(rating);
        

        rateUP = rateUsagePointRepo.saveAndFlush(rateUP);
        
        //ใช้ id ในการค้นหา RateUsagePoint 
        Optional<RateUsagePoint> found = rateUsagePointRepo.findById(rateUP.getId());
        
        //เช็คว่าค่าที่ใส่เข้าไปในตารางกับค่าที่ set ไว้ตรงกันหรือไม่
        assertEquals("email@email.com",found.get().getEmail());
        assertEquals(datee,found.get().getDate());
        assertEquals("แย่แล้วครับ",found.get().getSuggestion());
        assertEquals(stattt,found.get().getStatus());
        assertEquals(edd,found.get().getEdlevel());
        assertEquals(rating,found.get().getRating());
    }

    @Test
    void b5908783_testSuggestionMustNotBeNull() {
        //สร้าง OBJECT เตียมไว้สำหรับใช้ set  ค่าในแต่ละ field
        RateUsagePoint rateUP = new RateUsagePoint();
        EducationLevel edd = new EducationLevel();
        Status stattt = new Status();
        Rating rating = new Rating();
       
        Date datee = new Date();

        //เตรียมค่าตัวแปร ก่อน set
        edd.setEdlevel("ปริญญาตรี");
        edd = educationLevelRepo.saveAndFlush(edd);

        stattt.setStatus("บุคคลทั่วไป");
        stattt = statusRepo.saveAndFlush(stattt);

        rating.setRating("ปานกลาง");
        rating = ratingRepo.saveAndFlush(rating);

        rateUP.setEmail("email@email.com");
        rateUP.setDate(datee);
        rateUP.setSuggestion(null); 
        rateUP.setStatus(stattt);
        rateUP.setEdlevel(edd);
        rateUP.setRating(rating);
        
        Set<ConstraintViolation<RateUsagePoint>> result = validator.validate(rateUP);
        
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RateUsagePoint> v = result.iterator().next();
        assertEquals("Suggestion cannot be null", v.getMessage());
        assertEquals("suggestion", v.getPropertyPath().toString());
  
    }

    @Test
    void b5908783_testEmailMustNotBeNull() {
        //สร้าง OBJECT เตียมไว้สำหรับใช้ set  ค่าในแต่ละ field
        RateUsagePoint rateUP = new RateUsagePoint();
        EducationLevel edd = new EducationLevel();
        Status stattt = new Status();
        Rating rating = new Rating();
       
        Date datee = new Date();

        //เตรียมค่าตัวแปร ก่อน set
        edd.setEdlevel("ปริญญาตรี");
        edd = educationLevelRepo.saveAndFlush(edd);

        stattt.setStatus("บุคคลทั่วไป");
        stattt = statusRepo.saveAndFlush(stattt);

        rating.setRating("ปานกลาง");
        rating = ratingRepo.saveAndFlush(rating);

        rateUP.setEmail(null);
        rateUP.setDate(datee);
        rateUP.setSuggestion("บรรยากาศดีมากครับ");
        rateUP.setStatus(stattt);
        rateUP.setEdlevel(edd);
        rateUP.setRating(rating);
        
        Set<ConstraintViolation<RateUsagePoint>> result = validator.validate(rateUP);
        
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RateUsagePoint> v = result.iterator().next();
        assertEquals("Email cannot be null", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
  
    }

    @Test
    void b5908783_testDateMustNotBeNull() {
        //สร้าง OBJECT เตียมไว้สำหรับใช้ set  ค่าในแต่ละ field
        RateUsagePoint rateUP = new RateUsagePoint();
        EducationLevel edd = new EducationLevel();
        Status stattt = new Status();
        Rating rating = new Rating();
       
        //เตรียมค่าตัวแปร ก่อน set ในตารางหลัก
        edd.setEdlevel("ปริญญาตรี");
        edd = educationLevelRepo.saveAndFlush(edd);

        stattt.setStatus("บุคคลทั่วไป");
        stattt = statusRepo.saveAndFlush(stattt);

        rating.setRating("ปานกลาง");
        rating = ratingRepo.saveAndFlush(rating);

        //set ตัวแปรในตารางหลัก
        rateUP.setEmail("Email@email.com");
        rateUP.setDate(null);
        rateUP.setSuggestion("บรรยากาศดีมากครับ");
        rateUP.setStatus(stattt);
        rateUP.setEdlevel(edd);
        rateUP.setRating(rating);
        
        Set<ConstraintViolation<RateUsagePoint>> result = validator.validate(rateUP);
        
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RateUsagePoint> v = result.iterator().next();
        assertEquals("Date cannot be null", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());
  
    }
    @Test
    void b5908783_testStatusMustNotBeNull() {
        //สร้าง OBJECT เตียมไว้สำหรับใช้ set  ค่าในแต่ละ field
        RateUsagePoint rateUP = new RateUsagePoint();
        EducationLevel edd = new EducationLevel();
        Rating rating = new Rating();
       
        Date datee = new Date();

        //เตรียมค่าตัวแปร ก่อน set ในตารางหลัก
        edd.setEdlevel("ปริญญาตรี");
        edd = educationLevelRepo.saveAndFlush(edd);

        rating.setRating("ปานกลาง");
        rating = ratingRepo.saveAndFlush(rating);

        //set ตัวแปรในตารางหลัก
        rateUP.setEmail("Email@email.com");
        rateUP.setDate(datee);
        rateUP.setSuggestion("บรรยากาศดีมากครับ");
        rateUP.setStatus(null);
        rateUP.setEdlevel(edd);
        rateUP.setRating(rating);
        
        Set<ConstraintViolation<RateUsagePoint>> result = validator.validate(rateUP);
        
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RateUsagePoint> v = result.iterator().next();
        assertEquals("Status cannot be null", v.getMessage());
        assertEquals("status", v.getPropertyPath().toString());
  
    }
    
    @Test
    void b5908783_testEdLevelMustNotBeNull() {
        //สร้าง OBJECT เตียมไว้สำหรับใช้ set  ค่าในแต่ละ field
        RateUsagePoint rateUP = new RateUsagePoint();
        Status stattt = new Status();
        Rating rating = new Rating();
        Date dateee = new Date();

        stattt.setStatus("บุคคลทั่วไป");
        stattt = statusRepo.saveAndFlush(stattt);

        rating.setRating("ปานกลาง");
        rating = ratingRepo.saveAndFlush(rating);

        //set ตัวแปรในตารางหลัก
        rateUP.setEmail("Email@email.com");
        rateUP.setDate(dateee);
        rateUP.setSuggestion("บรรยากาศดีมากครับ");
        rateUP.setStatus(stattt);
        rateUP.setEdlevel(null);
        rateUP.setRating(rating);
        
        Set<ConstraintViolation<RateUsagePoint>> result = validator.validate(rateUP);
        
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RateUsagePoint> v = result.iterator().next();
        assertEquals("Education Level cannot be null", v.getMessage());
        assertEquals("edlevel", v.getPropertyPath().toString());
  
    }
    
    @Test
    void b5908783_testRatingMustNotBeNull() {
        RateUsagePoint rateUP = new RateUsagePoint();
        EducationLevel edd = new EducationLevel();
        Status stattt = new Status();
        Date datee = new Date();
        edd.setEdlevel("ปริญญาตรี");
        edd = educationLevelRepo.saveAndFlush(edd);

        stattt.setStatus("บุคคลทั่วไป");
        stattt = statusRepo.saveAndFlush(stattt);

        //set ตัวแปรในตารางหลัก
        rateUP.setEmail("Email@email.com");
        rateUP.setDate(datee);
        rateUP.setSuggestion("บรรยากาศดีมากครับ");
        rateUP.setStatus(stattt);
        rateUP.setEdlevel(edd);
        rateUP.setRating(null);
        
        Set<ConstraintViolation<RateUsagePoint>> result = validator.validate(rateUP);
        
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RateUsagePoint> v = result.iterator().next();
        assertEquals("Rating cannot be null", v.getMessage());
        assertEquals("rating", v.getPropertyPath().toString());
  
    }

    @Test
    void b5908783_testEmailIsNotAValidEmail() {
        //สร้าง OBJECT เตียมไว้สำหรับใช้ set  ค่าในแต่ละ field
        RateUsagePoint rateUP = new RateUsagePoint();
        EducationLevel edd = new EducationLevel();
        Status stattt = new Status();
        Rating rating = new Rating();
       
        Date datee = new Date();

        //เตรียมข้อมูลสำหรับส่วนของ Edlevel
        edd.setEdlevel("ปริญญาตรี");
        edd = educationLevelRepo.saveAndFlush(edd);

        //เตรียมข้อมูลสำหรับส่วนของ Status
        stattt.setStatus("บุคคลทั่วไป");
        stattt = statusRepo.saveAndFlush(stattt);

        //เตรียมข้อมูลสำหรับส่วนของ Rating
        rating.setRating("ปานกลาง");
        rating = ratingRepo.saveAndFlush(rating);

        //ทำการ set ค่าตัวแปร
        rateUP.setEmail("Email.com");
        rateUP.setDate(datee);
        rateUP.setSuggestion("บรรยากาศดีมากครับ");
        rateUP.setStatus(stattt);
        rateUP.setEdlevel(edd);
        rateUP.setRating(rating);

        Set<ConstraintViolation<RateUsagePoint>> result = validator.validate(rateUP);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RateUsagePoint> v = result.iterator().next();
        //ดูว่ามี Error Message ตรงกันกับที่ตั้งไว้หรือไม่
        assertEquals("Email is not a valid Email", v.getMessage());
        //จุดที่เกิด Error เกิดขึ้นตรงจุดที่เช็คใช่หรือไม่
        assertEquals("email", v.getPropertyPath().toString());
    }

    @Test
    void b5908783_testSuggestionMustNotLessThan10Characters() {
        //สร้าง OBJECT เตียมไว้สำหรับใช้ set  ค่าในแต่ละ field
        RateUsagePoint rateUP = new RateUsagePoint();
        EducationLevel edd = new EducationLevel();
        Status stattt = new Status();
        Rating rating = new Rating();
       
        Date datee = new Date();

        //เตรียมข้อมูลสำหรับส่วนของ Edlevel
        edd.setEdlevel("ปริญญาตรี");
        edd = educationLevelRepo.saveAndFlush(edd);

        //เตรียมข้อมูลสำหรับส่วนของ Status
        stattt.setStatus("บุคคลทั่วไป");
        stattt = statusRepo.saveAndFlush(stattt);

        //เตรียมข้อมูลสำหรับส่วนของ Rating
        rating.setRating("ปานกลาง");
        rating = ratingRepo.saveAndFlush(rating);

        //ทำการ set ค่าตัวแปร
        rateUP.setEmail("Email@gmail.com");
        rateUP.setDate(datee);
        rateUP.setSuggestion("12345");
        rateUP.setStatus(stattt);
        rateUP.setEdlevel(edd);
        rateUP.setRating(rating);

        Set<ConstraintViolation<RateUsagePoint>> result = validator.validate(rateUP);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RateUsagePoint> v = result.iterator().next();
        //ดูว่ามี Error Message ตรงกันกับที่ตั้งไว้หรือไม่
        assertEquals("Suggestion must be between 10 and 200 characters", v.getMessage());
        //จุดที่เกิด Error เกิดขึ้นตรงจุดที่เช็คใช่หรือไม่
        assertEquals("suggestion", v.getPropertyPath().toString());
    }
    @Test
    void b5908783_testSuggestionMustNotMoreThan200Characters() {
        //สร้าง OBJECT เตียมไว้สำหรับใช้ set  ค่าในแต่ละ field
        RateUsagePoint rateUP = new RateUsagePoint();
        EducationLevel edd = new EducationLevel();
        Status stattt = new Status();
        Rating rating = new Rating();
       
        Date datee = new Date();

        //เตรียมข้อมูลสำหรับส่วนของ Edlevel
        edd.setEdlevel("ปริญญาตรี");
        edd = educationLevelRepo.saveAndFlush(edd);

        //เตรียมข้อมูลสำหรับส่วนของ Status
        stattt.setStatus("บุคคลทั่วไป");
        stattt = statusRepo.saveAndFlush(stattt);

        //เตรียมข้อมูลสำหรับส่วนของ Rating
        rating.setRating("ปานกลาง");
        rating = ratingRepo.saveAndFlush(rating);

        //ทำการ set ค่าตัวแปร
        rateUP.setEmail("Email@gmail.com");
        rateUP.setDate(datee);
        rateUP.setSuggestion("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        rateUP.setStatus(stattt);
        rateUP.setEdlevel(edd);
        rateUP.setRating(rating);

        Set<ConstraintViolation<RateUsagePoint>> result = validator.validate(rateUP);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RateUsagePoint> v = result.iterator().next();
        //ดูว่ามี Error Message ตรงกันกับที่ตั้งไว้ ใน Entity หรือไม่
        assertEquals("Suggestion must be between 10 and 200 characters", v.getMessage());
        //จุดที่เกิด Error เกิดขึ้นตรงจุดที่เช็คใช่หรือไม่
        assertEquals("suggestion", v.getPropertyPath().toString());
    }

    @Test
    void b5908783_testEmailMustBeUnique() {
        //สร้าง OBJECT เตียมไว้สำหรับใช้ set  ค่าในแต่ละ field
        RateUsagePoint rateUP = new RateUsagePoint();
        EducationLevel edd = new EducationLevel();
        Status stattt = new Status();
        Rating rating = new Rating();
       
        Date datee = new Date();

        //เตรียมข้อมูลสำหรับส่วนของ Edlevel
        edd.setEdlevel("ปริญญาตรี");
        edd = educationLevelRepo.saveAndFlush(edd);

        //เตรียมข้อมูลสำหรับส่วนของ Status
        stattt.setStatus("บุคคลทั่วไป");
        stattt = statusRepo.saveAndFlush(stattt);

        //เตรียมข้อมูลสำหรับส่วนของ Rating
        rating.setRating("ปานกลาง");
        rating = ratingRepo.saveAndFlush(rating);

        //ทำการ set ค่าตัวแปร
        rateUP.setEmail("B5908783@g.sut.ac.th");
        rateUP.setDate(datee);
        rateUP.setSuggestion("บรรยากาศดีมากครับ");
        rateUP.setStatus(stattt);
        rateUP.setEdlevel(edd);
        rateUP.setRating(rating);

        rateUsagePointRepo.saveAndFlush(rateUP);
        //DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            //สร้าง OBJECT ตัวที่ 2
            RateUsagePoint rateUP2 = new RateUsagePoint();
            EducationLevel edd2 = new EducationLevel();
            Status stattt2 = new Status();
            Rating rating2 = new Rating();
            Date datee2 = new Date();
             //เตรียมข้อมูลสำหรับส่วนของ Edlevel
        edd2.setEdlevel("ปริญญาตรี");
        edd2 = educationLevelRepo.saveAndFlush(edd2);

        //เตรียมข้อมูลสำหรับส่วนของ Status
        stattt2.setStatus("บุคคลทั่วไป");
        stattt2 = statusRepo.saveAndFlush(stattt2);

        //เตรียมข้อมูลสำหรับส่วนของ Rating
        rating2.setRating("ปานกลาง");
        rating2 = ratingRepo.saveAndFlush(rating2);

            rateUP2.setEmail("B5908783@g.sut.ac.th");
            rateUP2.setDate(datee2);
            rateUP2.setSuggestion("บรรยากาศดีมากครับ");
            rateUP2.setStatus(stattt2);
            rateUP2.setEdlevel(edd2);
            rateUP2.setRating(rating2);
            rateUsagePointRepo.saveAndFlush(rateUP2);
        });
    }
    

}