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

import com.example.demo.entity.*;
import com.example.demo.repository.*;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class RemandTest {

    private Validator validator;
    private BookType bookType;
    private Member member;
    private Document document;
    private Borrow borrow;
    private BookStatus bookStatus;

    @Autowired
    RemandRepository RemandRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired 
    PrefixRepository prefixRepository;

    @Autowired
    MemtypeRepository memtypeRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BookTypeRepository bookTypeRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    BookCategoryRepository bookCategoryRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    BorrowRepository borrowRepository;
    
    @Autowired
    BookStatusRepository bookStatusRepository;
    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Province province = new Province();
        province.setProvince("ชัยภูมิ");
        province = provinceRepository.saveAndFlush(province);

        Prefix prefix = new Prefix();
        prefix.setPrefix("เด็กชาย");
        prefix = prefixRepository.saveAndFlush(prefix);

        Memtype memtype = new Memtype();
        memtype.setMemtype("นักศึกษา");
        memtype = memtypeRepository.saveAndFlush(memtype);

        member = new Member();
        member.setIdcard("1234567890123");
        member.setName("ABCd");
        member.setProvince(province);
        member.setPrefix(prefix);
        member.setMemtype(memtype);
        member = memberRepository.saveAndFlush(member);

        bookType = new BookType();
        bookType.setBookTypeName("bookTypeName");
        bookType = bookTypeRepository.saveAndFlush(bookType);

        Language language = new Language();
        language.setLanguageName("English");
        language = languageRepository.saveAndFlush(language);

        BookCategory bookCategory = new BookCategory();
        bookCategory.setBookCategoryName("bookCategoryName");
        bookCategory = bookCategoryRepository.saveAndFlush(bookCategory);

        document = new Document();
        document.setAmount(1);
        document.setBookName("Head First");
        document.setWritterName("Bert Bates");
        document.setBookCategory(bookCategory);
        document.setBookType(bookType);
        document.setLanguage(language);
        document = documentRepository.saveAndFlush(document);

        Date date = new Date();
        borrow = new Borrow();
        borrow.setNumbers(1L);
        borrow.setBorrowDate(date);
        borrow.setTell("0812345678");
        borrow.setMember(member);
        borrow.setBookType(bookType);
        borrow.setDocument(document);
        borrow.setMemtype(memtype);
        borrow = borrowRepository.saveAndFlush(borrow);

        bookStatus = new BookStatus();
        bookStatus.setBookStatusName("bookStatusName");
        bookStatus = bookStatusRepository.saveAndFlush(bookStatus);
    }

    @Test
    void B5902200_testCreateRemandOK() {
        Remand remand = new Remand();
        Date nowDate = new Date();
        remand.setRemanddate(nowDate);
        remand.setAmount(1);
        remand.setBookType(bookType);
        remand.setBorrow(borrow);
        remand.setDocument(document);
        remand.setMember(member);
        remand.setBookstatus(bookStatus);
		remand = RemandRepository.saveAndFlush(remand);

		Optional<Remand> Remand = RemandRepository.findById(remand.getId());
        
        assertEquals(nowDate, Remand.get().getRemanddate());
        assertEquals(1, Remand.get().getAmount().intValue());
        assertEquals(bookType, Remand.get().getBookType());
        assertEquals(borrow, Remand.get().getBorrow());
        assertEquals(document, Remand.get().getDocument());
        assertEquals(member, Remand.get().getMember());
    }

	@Test
    void B5902200_testRemandIdMustNotBeNull() {
        Remand remand = new Remand();
        Date nowDate = new Date();
        remand.setAmount(1);
        remand.setRemanddate(nowDate);
        remand.setId(null);
        remand.setBookType(bookType);
        remand.setBorrow(borrow);
        remand.setDocument(document);
        remand.setMember(member);
        remand.setBookstatus(bookStatus);
		Set<ConstraintViolation<Remand>> result = validator.validate(remand);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Remand> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }
    
    @Test
    void B5902200_testRemanddateMustNotBeNull() {
        Remand remand = new Remand();
        remand.setRemanddate(null);
        remand.setId(1L);
        remand.setAmount(1);
        remand.setBookType(bookType);
        remand.setBorrow(borrow);
        remand.setDocument(document);
        remand.setMember(member);
        remand.setBookstatus(bookStatus);
		Set<ConstraintViolation<Remand>> result = validator.validate(remand);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Remand> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("remanddate", v.getPropertyPath().toString());
    }

    @Test
    void B5902200_testAmountMustNotLessThan1() {
        Remand remand = new Remand();
        Date date = new Date();
        remand.setRemanddate(date);
        remand.setId(1L);
        remand.setAmount(0);
        remand.setBookType(bookType);
        remand.setBorrow(borrow);
        remand.setDocument(document);
        remand.setMember(member);
        remand.setBookstatus(bookStatus);
		Set<ConstraintViolation<Remand>> result = validator.validate(remand);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Remand> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }

    @Test
    void B5902200_testAmountMustNotLessThan10() {
        Remand remand = new Remand();
        Date date = new Date();
        remand.setRemanddate(date);
        remand.setId(1L);
        remand.setAmount(11);
        remand.setBookType(bookType);
        remand.setBorrow(borrow);
        remand.setDocument(document);
        remand.setBookstatus(bookStatus);
        remand.setMember(member);
		Set<ConstraintViolation<Remand>> result = validator.validate(remand);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Remand> v = result.iterator().next();
        assertEquals("must be less than or equal to 10", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }

    @Test
    void B5902200_testMustBookTypeNotBeNull() {
        Remand remand = new Remand();
        Date date = new Date();
        remand.setRemanddate(date);
        remand.setId(1L);
        remand.setAmount(1);
        remand.setBookType(null);
        remand.setBorrow(borrow);
        remand.setDocument(document);
        remand.setBookstatus(bookStatus);
        remand.setMember(member);
		Set<ConstraintViolation<Remand>> result = validator.validate(remand);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Remand> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("bookType", v.getPropertyPath().toString());
    }

    @Test
    void B5902200_testBorrowMustNotBeNull() {
        Remand remand = new Remand();
        Date date = new Date();
        remand.setRemanddate(date);
        remand.setId(1L);
        remand.setAmount(1);
        remand.setBookType(bookType);
        remand.setBorrow(null);
        remand.setDocument(document);
        remand.setBookstatus(bookStatus);
        remand.setMember(member);
		Set<ConstraintViolation<Remand>> result = validator.validate(remand);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Remand> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("borrow", v.getPropertyPath().toString());
    }

    @Test
    void B5902200_testDocumentMustNotBeNull() {
        Remand remand = new Remand();
        Date date = new Date();
        remand.setRemanddate(date);
        remand.setId(1L);
        remand.setAmount(1);
        remand.setBookType(bookType);
        remand.setBorrow(borrow);
        remand.setDocument(null);
        remand.setBookstatus(bookStatus);
        remand.setMember(member);
		Set<ConstraintViolation<Remand>> result = validator.validate(remand);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Remand> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("document", v.getPropertyPath().toString());
    }

    @Test
    void B5902200_testBookStatusMustNotBeNull() {
        Remand remand = new Remand();
        Date date = new Date();
        remand.setRemanddate(date);
        remand.setId(1L);
        remand.setAmount(1);
        remand.setBookType(bookType);
        remand.setBorrow(borrow);
        remand.setDocument(document);
        remand.setBookstatus(null);
        remand.setMember(member);
		Set<ConstraintViolation<Remand>> result = validator.validate(remand);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Remand> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("bookStatus", v.getPropertyPath().toString());
    }

    @Test
    void B5902200_testMemberMustNotBeNull() {
        Remand remand = new Remand();
        Date date = new Date();
        remand.setRemanddate(date);
        remand.setId(1L);
        remand.setAmount(1);
        remand.setBookType(bookType);
        remand.setBorrow(borrow);
        remand.setDocument(document);
        remand.setBookstatus(bookStatus);
        remand.setMember(null);
		Set<ConstraintViolation<Remand>> result = validator.validate(remand);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Remand> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("member", v.getPropertyPath().toString());
    }
}