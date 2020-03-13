package com.example.demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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

@DataJpaTest //เรียก Data จาก Entity ต่างๆมารวมตัวกัน
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

        Province province = new Province();//สร้าง province เพื่อ เซ็ตตค่าให้กับ member
        province.setProvince("ชัยภูมิ");
        province = provinceRepository.saveAndFlush(province);

        Prefix prefix = new Prefix();//สร้าง prefix เพื่อ เซ็ตตค่าให้กับ member
        prefix.setPrefix("เด็กชาย");
        prefix = prefixRepository.saveAndFlush(prefix);

        Memtype memtype = new Memtype(); //สร้าง memtype เพื่อ เซ็ตตค่าให้กับ member
        memtype.setMemtype("นักศึกษา");
        memtype = memtypeRepository.saveAndFlush(memtype);

        member = new Member();//สร้าง member เพื่อ เซ็ตตค่าให้กับ borrow
        member.setIdcard("1234567890123");
        member.setName("ABCd");
        member.setProvince(province);
        member.setPrefix(prefix);
        member.setMemtype(memtype);
        member = memberRepository.saveAndFlush(member);

        bookType = new BookType(); //มีการสร้าง booktyp เพื่อใช้ในการ เซ็ตค่าให้กับ remand
        bookType.setBookTypeName("bookTypeName");
        bookType = bookTypeRepository.saveAndFlush(bookType);

        Language language = new Language();//สร้าง language เพื่อ เซ็ตตค่าให้กับ document
        language.setLanguageName("English");
        language = languageRepository.saveAndFlush(language);

        BookCategory bookCategory = new BookCategory();///สร้าง bookcategory เพื่อ เซ็ตตค่าให้กับ document
        bookCategory.setBookCategoryName("bookCategoryName");
        bookCategory = bookCategoryRepository.saveAndFlush(bookCategory);

        document = new Document();///สร้าง document เพื่อ เซ็ตตค่าให้กับ remand
        document.setAmount(1);
        document.setBookName("Head First");
        document.setWritterName("Bert Bates");
        document.setBookCategory(bookCategory);
        document.setBookType(bookType);
        document.setLanguage(language);
        document = documentRepository.saveAndFlush(document);

        Date date = new Date();
        borrow = new Borrow();//สร้าง borrow เพื่อ เซ็ตตค่าให้กับ remand
        borrow.setNumbers(1L);
        borrow.setBorrowDate(date);
        borrow.setTell("0812345678");
        borrow.setMember(member);
        borrow.setBookType(bookType);
        borrow.setDocument(document);
        borrow.setMemtype(memtype);
        borrow = borrowRepository.saveAndFlush(borrow);

        bookStatus = new BookStatus();//สร้าง bookstatus เพื่อ เซ็ตตค่าให้กับ remand
        bookStatus.setBookStatusName("bookStatusName");
        bookStatus = bookStatusRepository.saveAndFlush(bookStatus);
    }

    //พี่ทีเอให้เพิ่มเทส positive ให้ครบค่ะ โดยบอกว่าใน must be ok ของหนูยังเทสไม่ครบค่ะ หนูก็เลยทำตรงนี้เพิ่มมาค่ะ

    @Test
    void B5902200_testCreateRemandOK() {       //method test 
        Remand remand = new Remand();
        Date nowDate = new Date();
        remand.setRemanddate(nowDate);
        remand.setAmount(1);
        remand.setBookType(bookType);           //ค่าของพวกนี้ต้องใส่ เพราะเรา set เป็น @NotNull แล้ว จึงต้องมีค่าเหล่านี้ set ลงไป
        remand.setBorrow(borrow);
        remand.setDocument(document);
        remand.setBookstatus(bookStatus);
        remand = RemandRepository.saveAndFlush(remand); 

		Optional<Remand> Remand = RemandRepository.findById(remand.getId());
        
        assertEquals(nowDate, Remand.get().getRemanddate());
        assertEquals(1, Remand.get().getAmount().intValue());
        assertEquals(bookType, Remand.get().getBookType()); //พอมีการ set ค่า ก็ต้องมีการ assert ค่าเข้าไป เพื่อเช็คว่าค่าตรงกัน เช่น ชื่อคนยืม ปุยนุ่น เหมือนกัน
        assertEquals(borrow, Remand.get().getBorrow());
        assertEquals(document, Remand.get().getDocument());
    }

    //แต่ก่อนจะมี Test Positive ได้ ต้องมีการดึงค่าจาก Entity อื่นเข้ามาก่อน
    
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
		Set<ConstraintViolation<Remand>> result = validator.validate(remand);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Remand> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("bookStatus", v.getPropertyPath().toString());
    }

}