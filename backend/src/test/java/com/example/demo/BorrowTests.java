package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import javax.persistence.UniqueConstraint;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.example.demo.entity.BookCategory;
import com.example.demo.entity.BookType;
import com.example.demo.entity.Borrow;
import com.example.demo.entity.Document;
import com.example.demo.entity.Language;
import com.example.demo.entity.Member;
import com.example.demo.entity.Memtype;
import com.example.demo.entity.Prefix;
import com.example.demo.entity.Province;
import com.example.demo.repository.BookCategoryRepository;
import com.example.demo.repository.BookTypeRepository;
import com.example.demo.repository.BorrowRepository;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.LanguageRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemtypeRepository;
import com.example.demo.repository.PrefixRepository;
import com.example.demo.repository.ProvinceRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
// @SpringBootTest
// @RunWith(SpringRunner.class)
public class BorrowTests {
    private Validator validator;
    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookTypeRepository bookTypeRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private MemtypeRepository memTypeRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private PrefixRepository prefixRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private MemberRepository memberRepository;

    private BookType bookType;
    private Document document;
    private Member member;
    private Memtype memType;
    private Language language;
    private BookCategory bookCategory;
    private Prefix prefix;
    private Province province;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        bookType = BookType.builder().bookTypeName("Erotic").build();
        bookType = bookTypeRepository.saveAndFlush(bookType);

        language = Language.builder().languageName("Japan").build();
        language = languageRepository.saveAndFlush(language);

        bookCategory = BookCategory.builder().bookCategoryName("Dojin").build();
        bookCategory = bookCategoryRepository.saveAndFlush(bookCategory);

        prefix = Prefix.builder().prefix("Dr.").build();
        prefix = prefixRepository.saveAndFlush(prefix);

        province = Province.builder().province("Bangkok").build();
        province = provinceRepository.saveAndFlush(province);

        memType = Memtype.builder().memtype("Government").build();
        memType = memTypeRepository.saveAndFlush(memType);

<<<<<<< HEAD
        member = Member.builder().idcard("1234567890123").memtype(memType).name("Paknahee").prefix(prefix).province(province)
=======
        member = Member.builder().idcard("ASD123").memtype(memType).name("Paknahee").prefix(prefix).province(province)
>>>>>>> 6d3bb658ec929f796b3933d974bdcb01a7645421
                .build();
        member = memberRepository.saveAndFlush(member);

        document = Document.builder().bookName("Doujin").amount(2).bookType(bookType).writterName("Joe Kim").language(language)
                .bookCategory(bookCategory).build();

        document = documentRepository.saveAndFlush(document);
    }

    @AfterEach
    public void destroy() {
        bookTypeRepository.deleteAll();
        languageRepository.deleteAll();
        bookCategoryRepository.deleteAll();
        prefixRepository.deleteAll();
        provinceRepository.deleteAll();
        memTypeRepository.deleteAll();
        memberRepository.deleteAll();
        documentRepository.deleteAll();
    }

    @Test
    public void B5923403_borrowShouldBeOK() {
        Borrow borrow = Borrow.builder().bookType(bookType).borrowDate(new Date())
                .document(document).member(member).memtype(memType).numbers(1L).tell("0987451150").build();
        borrow = borrowRepository.saveAndFlush(borrow);

        Optional<Borrow> found = borrowRepository.findById(borrow.getBorrowId());
        
        assertEquals("0987451150", found.get().getTell());
        assertEquals("Erotic", found.get().getBookType().getBookTypeName());
        assertEquals("Japan", found.get().getDocument().getLanguage().getLanguageName());
        assertEquals("Dojin", found.get().getDocument().getBookCategory().getBookCategoryName());
        assertEquals("1", found.get().getNumbers().toString());
        assertEquals("Dr.", found.get().getMember().getPrefix().getPrefix());
        assertEquals("Bangkok", found.get().getMember().getProvince().getProvince());
        assertEquals("Government", found.get().getMemtype().getMemtype());
<<<<<<< HEAD
        assertEquals("1234567890123", found.get().getMember().getIdcard());
=======
        assertEquals("ASD123", found.get().getMember().getIdcard());
>>>>>>> 6d3bb658ec929f796b3933d974bdcb01a7645421
        assertEquals("Paknahee", found.get().getMember().getName());
        assertEquals("Doujin", found.get().getDocument().getBookName());
        assertEquals("Joe Kim", found.get().getDocument().getWritterName());
    }

    @Test
    public void B5923403_memberMustBeNotNull() {
        Borrow borrow = Borrow.builder().bookType(bookType).borrowDate(new Date())
                .document(document).member(null).memtype(memType).numbers(1L).tell("0987451150").build();

        Set<ConstraintViolation<Borrow>> result = validator.validate(borrow);
        assertEquals(1, result.size());
        ConstraintViolation<Borrow> violation = result.iterator().next();
        assertEquals("member must be not null", violation.getMessage());
        assertEquals("member", violation.getPropertyPath().toString());
    }

    @Test
    public void B5923403_testdateNotNull(){
        Borrow borrow = Borrow.builder().bookType(bookType).borrowDate(null)
                .document(document).member(member).memtype(memType).numbers(1L).tell("0987451150").build();

        Set<ConstraintViolation<Borrow>> result = validator.validate(borrow);
        assertEquals(1, result.size());
        ConstraintViolation<Borrow> violation = result.iterator().next();
        assertEquals("borrow date must be not null", violation.getMessage());
        assertEquals("borrowDate", violation.getPropertyPath().toString());
    }


    @Test
    public void B5923403_tellMustBeNumber() {
        Borrow borrow = Borrow.builder().bookType(bookType).borrowDate(new Date())
                .document(document).member(member).memtype(memType).numbers(1L).tell("asdfghjklm").build();

        Set<ConstraintViolation<Borrow>> result = validator.validate(borrow);
        assertEquals(1, result.size());
        ConstraintViolation<Borrow> violation = result.iterator().next();
        assertEquals("tell must be digit and 10 characters", violation.getMessage());
        assertEquals("tell", violation.getPropertyPath().toString());
    }

    @Test
    public void B5923403_tellMustNot11Characters() {
        Borrow borrow = Borrow.builder().bookType(bookType).borrowDate(new Date())
                .document(document).member(member).memtype(memType).numbers(1L).tell("09632587412").build();

        Set<ConstraintViolation<Borrow>> result = validator.validate(borrow);
        assertEquals(1, result.size());
        ConstraintViolation<Borrow> violation = result.iterator().next();
        assertEquals("tell must be digit and 10 characters", violation.getMessage());
        assertEquals("tell", violation.getPropertyPath().toString());
    }


    @Test
    public void B5923403_tellMustNot9Characters() {
        Borrow borrow = Borrow.builder().bookType(bookType).borrowDate(new Date())
                .document(document).member(member).memtype(memType).numbers(1L).tell("096325874").build();

        Set<ConstraintViolation<Borrow>> result = validator.validate(borrow);
        assertEquals(1, result.size());
        ConstraintViolation<Borrow> violation = result.iterator().next();
        assertEquals("tell must be digit and 10 characters", violation.getMessage());
        assertEquals("tell", violation.getPropertyPath().toString());
    }

    @Test
    public void B5923403_numberMustNotBeOver100() {
        Borrow borrow = Borrow.builder().bookType(bookType).borrowDate(new Date())
                .document(document).member(member).memtype(memType).numbers(101L).tell("0963258742").build();

        Set<ConstraintViolation<Borrow>> result = validator.validate(borrow);
        assertEquals(1, result.size());
        ConstraintViolation<Borrow> violation = result.iterator().next();
        assertEquals("number must not be greater than 100", violation.getMessage());
        assertEquals("numbers", violation.getPropertyPath().toString());
    }

    
    @Test
    public void B5923403_numberMustNotBeLess1() {
        Borrow borrow = Borrow.builder().bookType(bookType).borrowDate(new Date())
                .document(document).member(member).memtype(memType).numbers(0L).tell("0963258742").build();

        Set<ConstraintViolation<Borrow>> result = validator.validate(borrow);
        assertEquals(1, result.size());
        ConstraintViolation<Borrow> violation = result.iterator().next();
        assertEquals("number must not be less than 1", violation.getMessage());
        assertEquals("numbers", violation.getPropertyPath().toString());
    }

      
    


}
